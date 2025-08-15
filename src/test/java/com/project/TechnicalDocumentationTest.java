package com.project;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests that validate the "Genesis OS - Technical Documentation".
 * These tests assert the presence and consistency of key sections, commands, and version values.
 *
 * Test framework: JUnit 5 (org.junit.jupiter.api).
 *
 * The tests attempt to locate a documentation file by searching common locations:
 * - README.md
 * - TECHNICAL_DOCUMENTATION.md
 * - docs/TechnicalDocumentation.md
 * - docs/GenesisOS_TechnicalDocumentation.md
 * - Any *.md containing the expected title line.
 *
 * If no documentation file is found, tests will fail with a clear message to ensure
 * documentation remains discoverable and present.
 */
public class TechnicalDocumentationTest {

    private static final List<String> CANDIDATE_DOC_PATHS = Arrays.asList(
            "TECHNICAL_DOCUMENTATION.md",
            "technical_documentation.md",
            "docs/TECHNICAL_DOCUMENTATION.md",
            "docs/technical_documentation.md",
            "docs/TechnicalDocumentation.md",
            "docs/GenesisOS_TechnicalDocumentation.md",
            "README.md",
            "readme.md"
    );

    private static final String TITLE_LINE = "# Genesis OS - Technical Documentation";

    private static String docContent;
    private static Path docPath;

    @BeforeAll
    static void loadDocumentation() throws IOException {
        // Try exact candidate paths first
        for (String p : CANDIDATE_DOC_PATHS) {
            Path path = Paths.get(p);
            if (Files.exists(path) && Files.isRegularFile(path)) {
                String content = Files.readString(path, StandardCharsets.UTF_8);
                if (content.contains(TITLE_LINE)) {
                    docPath = path;
                    docContent = content;
                    break;
                }
            }
        }

        // If not found, search all .md files for the title
        if (docContent == null) {
            List<Path> mdFiles;
            try {
                mdFiles = Files.walk(Paths.get("."))
                        .filter(f -> Files.isRegularFile(f) && f.getFileName().toString().toLowerCase(Locale.ROOT).endsWith(".md"))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new AssertionError("Failed walking repository to discover documentation files", e);
            }

            for (Path p : mdFiles) {
                try {
                    String content = Files.readString(p, StandardCharsets.UTF_8);
                    if (content.contains(TITLE_LINE)) {
                        docPath = p;
                        docContent = content;
                        break;
                    }
                } catch (IOException ignored) {
                }
            }
        }

        assertNotNull(docContent, "Technical documentation not found. Expected to find a .md file containing the title line: " + TITLE_LINE);
        assertNotNull(docPath, "A documentation path should be resolved when content was found.");
    }

    @Test
    @DisplayName("Documentation contains the expected top-level title")
    void containsTitle() {
        assertTrue(docContent.lines().findFirst().orElse("").trim().equals(TITLE_LINE),
                "First line should be the exact title: " + TITLE_LINE);
    }

    @Test
    @DisplayName("Contains required high-level sections")
    void containsRequiredSections() {
        // Required section headers based on the provided diff
        List<String> requiredHeaders = Arrays.asList(
                "## Overview",
                "## Architecture",
                "### Project Structure",
                "### Technology Stack",
                "## Build System",
                "### Gradle Configuration",
                "### Key Build Tasks",
                "### OpenAPI Integration",
                "### Native Code (C++20)",
                "## Dependencies",
                "### Core Dependencies",
                "### Development Dependencies",
                "## Development Workflow",
                "### Prerequisites",
                "### Setup Instructions",
                "### Module Dependencies",
                "## Build Optimization",
                "### Performance Settings",
                "### Memory Management",
                "## Testing",
                "### Test Structure",
                "### Running Tests",
                "## Security",
                "### Features",
                "### Sensitive Data",
                "## Deployment",
                "### CI/CD Pipeline",
                "### Release Process",
                "## Troubleshooting",
                "### Common Issues",
                "### Debug Commands",
                "## Contributing",
                "### Code Style",
                "### Pull Request Process",
                "### Module Development",
                "## Performance Metrics",
                "### Build Times",
                "### APK Metrics",
                "## Future Roadmap",
                "### Planned Enhancements",
                "### Technology Updates"
        );

        for (String header : requiredHeaders) {
            assertTrue(docContent.contains(header), "Missing required section header: " + header);
        }
    }

    @Test
    @DisplayName("Key command snippets are present and correctly formatted")
    void containsKeyCommandSnippets() {
        List<String> requiredCommands = Arrays.asList(
                "./gradlew clean build",
                "./gradlew generateAllOpenApiClients",
                "./gradlew externalNativeBuildDebug",
                "./gradlew test",
                "./gradlew assembleDebug",
                "./gradlew installDebug",
                "./gradlew -q javaToolchains",
                "./gradlew dependencies",
                "./gradlew :app:externalNativeBuildDebug --debug",
                "./gradlew clean --quiet",
                "./gradlew connectedAndroidTest",
                "./gradlew :core-module:test"
        );

        for (String cmd : requiredCommands) {
            assertTrue(docContent.contains(cmd), "Expected command not found in documentation: " + cmd);
        }
    }

    @Test
    @DisplayName("Technology versions follow expected patterns")
    void technologyVersions() {
        // Kotlin version: "Kotlin 2.2.20-Beta2"
        Pattern kotlin = Pattern.compile("- \\*\\*Language\\*\\*: Kotlin\\s+\\d+\\.\\d+\\.\\d+(-[A-Za-z0-9]+)?\\s+with\\s+K2\\s+compiler");
        assertTrue(kotlin.matcher(docContent).find(), "Kotlin version line missing or malformed.");

        // Java version: "Java 24 (Oracle OpenJDK 24.02)"
        Pattern java = Pattern.compile("- \\*\\*Java Version\\*\\*: Java\\s+\\d+\\s+\\(Oracle OpenJDK\\s+\\d+\\.\\d+\\)");
        assertTrue(java.matcher(docContent).find(), "Java version line missing or malformed.");

        // Gradle version: "Gradle 8.13.0"
        Pattern gradle = Pattern.compile("The project uses Gradle\\s+\\d+\\.\\d+\\.\\d+\\s+with the following key features:");
        assertTrue(gradle.matcher(docContent).find(), "Gradle version line missing or malformed.");

        // Android NDK version (e.g., 27.0.12077973)
        Pattern ndk = Pattern.compile("- Android NDK\\s+\\d+\\.\\d+\\.\\d+");
        assertTrue(ndk.matcher(docContent).find(), "NDK version line missing or malformed.");
    }

    @Test
    @DisplayName("Lists include expected modules and dependencies")
    void modulesAndDependencies() {
        // Modules
        List<String> expectedModules = Arrays.asList(
                "app/",
                "core-module/",
                "feature-module/",
                "secure-comm/",
                "collab-canvas/",
                "colorblendr/",
                "datavein-oracle-native/",
                "oracle-drive-integration/",
                "romtools/",
                "sandbox-ui/",
                "modules-a-f/"
        );
        for (String m : expectedModules) {
            assertTrue(docContent.contains(m), "Expected module listing not found: " + m);
        }

        // Core dependencies
        List<String> coreDeps = Arrays.asList(
                "Android Jetpack",
                "Compose BOM",
                "Firebase",
                "Hilt",
                "Room",
                "Retrofit",
                "Xposed Framework"
        );
        for (String d : coreDeps) {
            assertTrue(docContent.contains(d), "Expected core dependency not found: " + d);
        }

        // Development dependencies
        List<String> devDeps = Arrays.asList(
                "KSP",
                "Timber",
                "LeakCanary",
                "JUnit 5",
                "Espresso"
        );
        for (String d : devDeps) {
            assertTrue(docContent.contains(d), "Expected development dependency not found: " + d);
        }
    }

    @Test
    @DisplayName("Build optimization and memory recommendations are documented")
    void buildOptimizationAndMemory() {
        assertTrue(docContent.contains("- **JVM Args**: `-Xmx6g -Xms2g -XX:+UseG1GC`"),
                "Expected JVM args not documented.");
        assertTrue(docContent.contains("Parallel Builds"),
                "Parallel builds mention expected.");
        assertTrue(docContent.contains("Configuration Cache"),
                "Configuration cache mention expected.");
        assertTrue(docContent.contains("Build Cache"),
                "Build cache mention expected.");

        assertTrue(docContent.contains("16GB RAM"), "Minimum RAM recommendation should be present.");
        assertTrue(docContent.contains("8GB available for Gradle JVM"), "Gradle JVM memory recommendation should be present.");
    }

    @Test
    @DisplayName("Testing structure and commands are present")
    void testingSection() {
        assertTrue(docContent.contains("### Test Structure"), "Test Structure section missing.");
        assertTrue(docContent.contains("- **Unit Tests**: JUnit 5 with Kotlin coroutines testing"),
                "Unit Tests description missing.");
        assertTrue(docContent.contains("- **Integration Tests**: Hilt testing for DI"),
                "Integration tests description missing.");
        assertTrue(docContent.contains("- **UI Tests**: Compose testing with Espresso"),
                "UI tests description missing.");
        assertTrue(docContent.contains("- **Native Tests**: C++ unit tests for native components"),
                "Native tests description missing.");

        assertTrue(docContent.contains("### Running Tests"), "Running Tests section missing.");
        assertTrue(docContent.contains("./gradlew test"), "Unit test command missing.");
        assertTrue(docContent.contains("./gradlew connectedAndroidTest"), "Instrumentation test command missing.");
    }

    @Test
    @DisplayName("Security features and sensitive data categories are documented")
    void securitySection() {
        List<String> features = Arrays.asList(
                "End-to-end encryption for communications",
                "Secure key storage using Android Keystore",
                "Firebase security rules",
                "Proguard/R8 code obfuscation",
                "Runtime application self-protection (RASP)"
        );
        for (String f : features) {
            assertTrue(docContent.contains(f), "Security feature missing: " + f);
        }

        List<String> sensitive = Arrays.asList(
                "Firebase configuration (externalized)",
                "API keys and secrets (environment variables)",
                "User authentication tokens",
                "AI model parameters"
        );
        for (String s : sensitive) {
            assertTrue(docContent.contains(s), "Sensitive data item missing: " + s);
        }
    }

    @Test
    @DisplayName("CI/CD pipeline and release process steps exist")
    void cicdAndRelease() {
        assertTrue(docContent.contains("GitHub Actions workflow includes:"), "CI/CD section listing missing.");
        List<String> cicd = Arrays.asList(
                "Code Analysis",
                "Dependency Updates",
                "Build Verification",
                "Testing",
                "APK Generation"
        );
        for (String c : cicd) {
            assertTrue(docContent.contains(c), "CI/CD pipeline step missing: " + c);
        }

        List<String> release = Arrays.asList(
                "Version bump in `gradle.properties`",
                "Update `CHANGELOG.md`",
                "Create release branch",
                "Run full test suite",
                "Generate signed APK",
                "Deploy to distribution channels"
        );
        for (String r : release) {
            assertTrue(docContent.contains(r), "Release process step missing: " + r);
        }
    }

    @Test
    @DisplayName("Troubleshooting section contains common issues and debug commands")
    void troubleshooting() {
        List<String> issues = Arrays.asList(
                "Java Version Conflicts",
                "Native Build Failures",
                "Memory Issues",
                "OpenAPI Generation",
                "Module Dependencies"
        );
        for (String i : issues) {
            assertTrue(docContent.contains(i), "Expected troubleshooting issue missing: " + i);
        }

        List<String> debugCmds = Arrays.asList(
                "./gradlew -q javaToolchains",
                "./gradlew dependencies",
                "./gradlew :app:externalNativeBuildDebug --debug",
                "./gradlew clean --quiet"
        );
        for (String cmd : debugCmds) {
            assertTrue(docContent.contains(cmd), "Expected debug command missing: " + cmd);
        }
    }

    @Test
    @DisplayName("Performance metrics include build times and APK metrics")
    void performanceMetrics() {
        assertTrue(docContent.contains("### Build Times"), "Build Times section should exist.");
        assertTrue(docContent.contains("Clean build:"), "Clean build time metric missing.");
        assertTrue(docContent.contains("Incremental build:"), "Incremental build time metric missing.");
        assertTrue(docContent.contains("OpenAPI generation:"), "OpenAPI generation metric missing.");
        assertTrue(docContent.contains("Native compilation:"), "Native compilation metric missing.");

        assertTrue(docContent.contains("### APK Metrics"), "APK Metrics section should exist.");
        assertTrue(docContent.contains("Debug APK:"), "Debug APK metric missing.");
        assertTrue(docContent.contains("Release APK:"), "Release APK metric missing.");
        assertTrue(docContent.contains("Native libraries:"), "Native libraries metric missing.");
        assertTrue(docContent.contains("Dex size:"), "Dex size metric missing.");
    }

    @Test
    @DisplayName("Future roadmap contains planned enhancements and technology updates")
    void futureRoadmap() {
        List<String> planned = Arrays.asList(
                "Kotlin Multiplatform",
                "AI Model Integration",
                "Plugin Architecture",
                "Performance Optimization",
                "Testing Enhancement"
        );
        for (String p : planned) {
            assertTrue(docContent.contains(p), "Planned enhancement missing: " + p);
        }

        List<String> techUpdates = Arrays.asList(
                "Migration to Kotlin 2.x stable",
                "Compose Multiplatform adoption",
                "Latest Android API integration",
                "Enhanced security features"
        );
        for (String t : techUpdates) {
            assertTrue(docContent.contains(t), "Technology update item missing: " + t);
        }
    }

    @Test
    @DisplayName("Documentation ends with a support reference")
    void endsWithSupportReference() {
        assertTrue(docContent.trim().endsWith("For questions or support, please refer to the project's GitHub issues or discussion forums."),
                "Documentation should end with the support reference sentence.");
    }

    @Test
    @DisplayName("No empty critical sections (sanity check)")
    void noEmptyCriticalSections() {
        // Ensure that each critical section header is followed by at least one non-empty line within the next ~10 lines
        String[] lines = docContent.split("\\R");
        Map<String, Integer> headerIndex = new LinkedHashMap<>();
        for (int i = 0; i < lines.length; i++) {
            String l = lines[i].trim();
            if (l.startsWith("## ") || l.startsWith("### ")) {
                headerIndex.put(l, i);
            }
        }

        // Check a subset of critical headers
        List<String> criticalHeaders = Arrays.asList(
                "## Build System",
                "### Key Build Tasks",
                "## Dependencies",
                "## Testing",
                "## Security",
                "## Deployment",
                "## Troubleshooting",
                "## Performance Metrics"
        );

        for (String h : criticalHeaders) {
            Integer idx = headerIndex.get(h);
            assertNotNull(idx, "Critical header not found: " + h);
            boolean foundMeaningful = false;
            for (int j = idx + 1; j < Math.min(lines.length, idx + 12); j++) {
                String candidate = lines[j].trim();
                if (!candidate.isEmpty() && !candidate.startsWith("##") && !candidate.startsWith("###") && !candidate.startsWith("```")) {
                    foundMeaningful = true;
                    break;
                }
            }
            assertTrue(foundMeaningful, "Critical header appears empty or lacks content near: " + h);
        }
    }
}