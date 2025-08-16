import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * VersionCatalogTest
 *
 * Notes:
 * - Testing framework: JUnit Jupiter (JUnit 5).
 * - Purpose: Validate critical structure and key entries of the Gradle version catalog (libs.versions.toml),
 *   focusing on the contents provided in the PR diff.
 * - These tests avoid external TOML parsers by using simple, robust text checks and minimal regex parsing
 *   where reasonable (especially for the [versions] block).
 */
public class VersionCatalogTest {

    private static Path catalogPath;
    private static List<String> lines;
    private static String fileText;

    @BeforeAll
    static void setUp() throws IOException {
        catalogPath = resolveCatalogPath();
        assertNotNull(catalogPath, "Could not resolve libs.versions.toml path. Expected at gradle/libs.versions.toml or discovered by fallback search.");
        assertTrue(Files.exists(catalogPath), "Version catalog file does not exist at: " + catalogPath);

        lines = Files.readAllLines(catalogPath, StandardCharsets.UTF_8);
        fileText = String.join("\n", lines);
    }

    @AfterAll
    static void tearDown() {
        // No resources to clean up
    }

    private static Path resolveCatalogPath() throws IOException {
        // First try the canonical Gradle location
        Path canonical = Paths.get("gradle", "libs.versions.toml");
        if (Files.exists(canonical)) {
            return canonical;
        }
        // Fallback: search common paths
        List<Path> candidates = new ArrayList<>();
        try (var stream = Files.walk(Paths.get("."), 5)) {
            stream.filter(p -> p.getFileName() != null && p.getFileName().toString().equals("libs.versions.toml"))
                    .forEach(candidates::add);
        }
        if (!candidates.isEmpty()) {
            // Prefer a path under gradle/ if found; otherwise the first candidate
            Optional<Path> gradleCandidate = candidates.stream().filter(p -> p.toString().contains("gradle")).findFirst();
            return gradleCandidate.orElse(candidates.get(0));
        }
        return null;
    }

    private static int indexOfSection(String sectionHeader) {
        String target = "[" + sectionHeader + "]";
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).trim().equals(target)) {
                return i;
            }
        }
        return -1;
    }

    private static List<String> sectionLines(String sectionHeader) {
        int start = indexOfSection(sectionHeader);
        if (start < 0) return List.of();
        List<String> result = new ArrayList<>();
        for (int i = start + 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String trimmed = line.trim();
            if (trimmed.startsWith("[") && trimmed.endsWith("]")) {
                break; // next section reached
            }
            result.add(line);
        }
        return result;
    }

    private static Map<String, String> parseSimpleKeyValueBlock(List<String> blockLines) {
        // Parses lines of the form: key = "value" (ignores comments/empties/complex objects)
        Pattern p = Pattern.compile("^\\s*([A-Za-z0-9_.-]+)\\s*=\\s*\"([^\"]*)\"\\s*$");
        Map<String, String> out = new LinkedHashMap<>();
        for (String raw : blockLines) {
            String line = raw.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;
            Matcher m = p.matcher(line);
            if (m.matches()) {
                out.put(m.group(1), m.group(2));
            }
        }
        return out;
    }

    private static List<String> collectKeysInBlock(List<String> blockLines) {
        // Collect keys on lines formatted like: key = ...
        Pattern p = Pattern.compile("^\\s*([A-Za-z0-9_.-]+)\\s*=");
        List<String> keys = new ArrayList<>();
        for (String raw : blockLines) {
            String line = raw.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;
            Matcher m = p.matcher(line);
            if (m.find()) {
                keys.add(m.group(1));
            }
        }
        return keys;
    }

    private static long countKeyOccurrences(String key) {
        Pattern p = Pattern.compile("(^|\\s)" + Pattern.quote(key) + "\\s*=");
        return lines.stream()
                .map(String::trim)
                .filter(l -> !l.startsWith("#"))
                .filter(l -> p.matcher(l).find())
                .count();
    }

    @Test
    @DisplayName("Catalog file exists and contains critical sections")
    void catalogContainsCriticalSections() {
        assertTrue(fileText.contains("[versions]"), "Missing [versions] section");
        assertTrue(fileText.contains("[plugins]"), "Missing [plugins] section");
        assertTrue(fileText.contains("[libraries]"), "Missing [libraries] section");
        assertTrue(fileText.contains("[bundles]"), "Missing [bundles] section");
    }

    @Nested
    class VersionsBlockTests {

        @Test
        @DisplayName("[versions] block has expected keys and values from PR diff")
        void versionsBlockHasExpectedEntries() {
            List<String> block = sectionLines("versions");
            assertFalse(block.isEmpty(), "Expected [versions] block to be present and non-empty");
            Map<String, String> kv = parseSimpleKeyValueBlock(block);

            // Basic presence checks for representative keys
            assertEquals("9.0.0", kv.get("agp"), "agp version mismatch");
            assertEquals("8.13.0-alpha04", kv.get("gradle"), "gradle version mismatch");
            assertEquals("2.2.20", kv.get("kotlin"), "kotlin version mismatch");
            assertEquals("36", kv.get("compileSdk"), "compileSdk mismatch");
            assertEquals("33", kv.get("minSdk"), "minSdk mismatch");
            assertEquals("36", kv.get("targetSdk"), "targetSdk mismatch");

            // Compose & UI
            assertEquals("2025.08.00", kv.get("composeBom"), "composeBom version mismatch");

            // Networking & Coroutines
            assertEquals("3.0.0", kv.get("retrofit"), "retrofit version mismatch");
            assertEquals("5.1.0", kv.get("okhttp"), "okhttp version mismatch");
            assertEquals("1.9.0", kv.get("kotlinxSerialization"), "kotlinxSerialization version mismatch");
            assertEquals("1.10.2", kv.get("kotlinxCoroutines"), "kotlinxCoroutines version mismatch");

            // Testing
            assertEquals("5.13.4", kv.get("junitJupiter"), "junitJupiter version mismatch");
            assertEquals("1.14.5", kv.get("mockk"), "mockk version mismatch");
            assertEquals("1.2.1", kv.get("turbine"), "turbine version mismatch");
        }

        @Test
        @DisplayName("[versions] block should not contain duplicate keys")
        void versionsBlockNoDuplicateKeys() {
            List<String> block = sectionLines("versions");
            List<String> keys = collectKeysInBlock(block);
            Map<String, Long> freq = keys.stream().collect(Collectors.groupingBy(k -> k, LinkedHashMap::new, Collectors.counting()));
            List<String> dups = freq.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
            assertTrue(dups.isEmpty(), "Duplicate keys found in [versions]: " + dups);
        }
    }

    @Nested
    class LibrariesBlockTests {

        @Test
        @DisplayName("[libraries] contains Compose BOM entry")
        void librariesContainsComposeBom() {
            // Validate that androidx-compose-bom is declared at least once
            // and not mistakenly embedded in a comment line.
            long occurrences = countKeyOccurrences("androidx-compose-bom");
            assertTrue(occurrences >= 1, "Expected at least one androidx-compose-bom entry in [libraries]");

            // Optional guard against malformed comment+entry on same line
            // Ensure there's at least one clean line strictly defining the key.
            boolean hasCleanDefinition = lines.stream()
                    .map(String::trim)
                    .anyMatch(l -> l.startsWith("androidx-compose-bom = {") || l.equals("androidx-compose-bom = { group = \"androidx.compose\", name = \"compose-bom\", version.ref = \"composeBom\" }"));
            assertTrue(hasCleanDefinition, "Expected a clean definition line for androidx-compose-bom in [libraries]");
        }

        @Test
        @DisplayName("[libraries] includes representative dependencies with expected coordinates")
        void librariesHasRepresentativeEntries() {
            var libs = sectionLines("libraries");
            assertFalse(libs.isEmpty(), "Expected [libraries] block not to be empty");

            // Basic string containment checks for a variety of entries from the diff.
            assertTrue(fileText.contains("androidx-core-ktx = { group = \"androidx.core\", name = \"core-ktx\", version.ref = \"coreKtx\" }"),
                    "Missing androidx-core-ktx library definition");
            assertTrue(fileText.contains("androidx-material = { group = \"com.google.android.material\", name = \"material\", version.ref = \"material\" }"),
                    "Missing androidx-material definition");
            assertTrue(fileText.contains("retrofit = { group = \"com.squareup.retrofit2\", name = \"retrofit\", version.ref = \"retrofit\" }"),
                    "Missing retrofit library definition");
            assertTrue(fileText.contains("okhttp3-logging-interceptor = { group = \"com.squareup.okhttp3\", name = \"logging-interceptor\", version.ref = \"okhttp\" }"),
                    "Missing okhttp3-logging-interceptor definition");
            assertTrue(fileText.contains("kotlinx-serialization-json = { group = \"org.jetbrains.kotlinx\", name = \"kotlinx-serialization-json\", version.ref = \"kotlinxSerialization\" }"),
                    "Missing kotlinx-serialization-json definition");
        }

        @Test
        @DisplayName("[libraries] block should not contain duplicate keys")
        void librariesNoDuplicateKeys() {
            List<String> block = sectionLines("libraries");
            List<String> keys = collectKeysInBlock(block);
            Map<String, Long> freq = keys.stream().collect(Collectors.groupingBy(k -> k, LinkedHashMap::new, Collectors.counting()));
            List<String> dups = freq.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
            // If "androidx-compose-bom" was accidentally duplicated, this will flag it.
            assertTrue(dups.isEmpty(), "Duplicate keys found in [libraries]: " + dups);
        }
    }

    @Nested
    class PluginsBlockTests {

        @Test
        @DisplayName("[plugins] contains representative plugin ids with version refs")
        void pluginsContainRepresentativeEntries() {
            var plugins = sectionLines("plugins");
            assertFalse(plugins.isEmpty(), "Expected [plugins] block not to be empty");

            // Use substring checks to avoid brittle formatting constraints
            assertTrue(fileText.contains("android-application = { id = \"com.android.application\", version.ref = \"agp\" }"),
                    "Missing android-application plugin mapping");
            assertTrue(fileText.contains("kotlin-android = { id = \"org.jetbrains.kotlin.android\", version.ref = \"kotlin\" }"),
                    "Missing kotlin-android plugin mapping");
            assertTrue(fileText.contains("ksp = { id = \"com.google.devtools.ksp\", version.ref = \"ksp\" }"),
                    "Missing ksp plugin mapping");
            assertTrue(fileText.contains("hilt-android = { id = \"com.google.dagger.hilt.android\", version.ref = \"hilt\" }"),
                    "Missing hilt-android plugin mapping");
        }

        @Test
        @DisplayName("[plugins] block should not contain duplicate keys")
        void pluginsNoDuplicateKeys() {
            List<String> block = sectionLines("plugins");
            List<String> keys = collectKeysInBlock(block);
            Map<String, Long> freq = keys.stream().collect(Collectors.groupingBy(k -> k, LinkedHashMap::new, Collectors.counting()));
            List<String> dups = freq.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
            assertTrue(dups.isEmpty(), "Duplicate keys found in [plugins]: " + dups);
        }
    }

    @Nested
    class BundlesBlockTests {

        @Test
        @DisplayName("[bundles] contains key groups like compose, coroutines, network, firebase, xposed, testing, androidx-core, utilities, room, security")
        void bundlesContainExpectedGroups() {
            var bundles = sectionLines("bundles");
            assertFalse(bundles.isEmpty(), "Expected [bundles] block not to be empty");

            // Ensure all bundle headers are present
            assertTrue(fileText.contains("compose = ["), "Missing compose bundle");
            assertTrue(fileText.contains("coroutines = ["), "Missing coroutines bundle");
            assertTrue(fileText.contains("network = ["), "Missing network bundle");
            assertTrue(fileText.contains("firebase = ["), "Missing firebase bundle");
            assertTrue(fileText.contains("xposed = ["), "Missing xposed bundle");
            assertTrue(fileText.contains("testing = ["), "Missing testing bundle");
            assertTrue(fileText.contains("androidx-core = ["), "Missing androidx-core bundle");
            assertTrue(fileText.contains("utilities = ["), "Missing utilities bundle");
            assertTrue(fileText.contains("room = ["), "Missing room bundle");
            assertTrue(fileText.contains("security = ["), "Missing security bundle");
        }

        @Test
        @DisplayName("[bundles] entries reference known library keys")
        void bundlesReferenceKnownLibraries() {
            // Verify that some referenced libraries are indeed defined in [libraries]
            Set<String> libraryKeys = new LinkedHashSet<>();
            for (String k : collectKeysInBlock(sectionLines("libraries"))) {
                libraryKeys.add(k);
            }

            // Representative checks
            String[] expectedInCompose = {"androidx-compose-ui", "androidx-compose-ui-graphics", "androidx-compose-ui-tooling-preview", "androidx-compose-material3"};
            for (String key : expectedInCompose) {
                assertTrue(libraryKeys.contains(key), "compose bundle references undefined library key: " + key);
            }

            String[] expectedInNetwork = {"retrofit", "retrofit-converter-kotlinx-serialization", "okhttp3-logging-interceptor", "kotlinx-serialization-json", "kotlinx-coroutines-core", "kotlinx-coroutines-android"};
            for (String key : expectedInNetwork) {
                assertTrue(libraryKeys.contains(key), "network bundle references undefined library key: " + key);
            }

            String[] expectedInTesting = {"junit-jupiter", "mockk", "turbine", "androidx-core-testing", "kotlinx-coroutines-test"};
            for (String key : expectedInTesting) {
                assertTrue(libraryKeys.contains(key), "testing bundle references undefined library key: " + key);
            }
        }
    }
}