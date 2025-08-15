@file:Suppress("UnstableApiUsage")

// ===== GENESIS AUTO-PROVISIONED SETTINGS =====
// Gradle 7.4+ auto-discovers gradle/libs.versions.toml
// NO manual version catalog configuration needed!

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://androidx.dev/storage/compose-compiler/repository/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://jitpack.io")
    }
}

plugins {
    // Auto-provision Java toolchains
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://androidx.dev/storage/compose-compiler/repository/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://jitpack.io")
    }
    // ✅ NO VERSION CATALOG CONFIG - Auto-discovered from gradle/libs.versions.toml
}

rootProject.name = "Genesis-Os"

// Genesis Protocol - Auto-discovered modules
include(":app")
include(":core-module")
include(":feature-module")
include(":datavein-oracle-native")
include(":oracle-drive-integration")
include(":secure-comm")
include(":sandbox-ui")
include(":collab-canvas")
include(":colorblendr")
include(":romtools")
include(":module-a", ":module-b", ":module-c", ":module-d", ":module-e", ":module-f")
