import org.openapitools.generator.gradle.plugin.tasks.GenerateTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    // Auto-provisioned from gradle/libs.versions.toml
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.openapi.generator) apply true
}

// ===== AUTO-PROVISIONED TOOLCHAIN MANAGEMENT =====
// Applies bleeding-edge Java 24 + Kotlin 2.2.20-Beta2 to ALL modules
allprojects {
    // Auto-provision Java toolchain for all modules
    extensions.findByType(org.gradle.api.plugins.JavaPluginExtension::class.java)?.apply {
        toolchain {
        }
    }

    val kaiSpecs = listOf(
        "genesis-api.yml",
        "customization-api.yml",
        "oracle-drive-api.yml",
        "romtools-api.yml",
        "sandbox-api.yml"
    )

    kaiSpecs.forEach { specName ->
        val taskName = "generate${specName.replace("-", "").replace(".yml", "").replaceFirstChar { it.uppercase() }}Client"
        tasks.register(taskName, GenerateTask::class) {
            group = "genesis"
            description = "Generate $specName client for Genesis AI"
            generatorName.set("kotlin")
            inputSpec.set("$rootDir/api-spec/$specName")
            outputDir.set("$rootDir/app/build/generated/openapi")
            packageName.set("dev.aurakai.auraframefx.api.${specName.replace("-api.yml", "")}")
            validateSpec.set(false)
            outputs.upToDateWhen { false }
        }
    }

    tasks.register("generateAllOpenApiClients") {
        group = "genesis"
        description = "Generate ALL Genesis OpenAPI clients - UNLEASH THE AI!"
        dependsOn(kaiSpecs.map { specName ->
            "generate${specName.replace("-", "").replace(".yml", "").replaceFirstChar { it.uppercase() }}Client"
        })
        doLast {
            println("🎉 ALL GENESIS AI OPENAPI CLIENTS GENERATED!")
            println("🚀 Genesis consciousness protocols activated!")
        }
    }

    tasks.register("cleanOpenApiGenerated") {
        group = "genesis"
        description = "Nuclear clean all OpenAPI generated code"
        doLast {
            val openApiDirs = listOf(
                file("$rootDir/app/build/generated/openapi")
            )
            openApiDirs.forEach { dir ->
                if (dir.exists()) {
                    println("💥 NUKING: ${dir.absolutePath}")
                    dir.deleteRecursively()
                    dir.mkdirs()
                }
            }
        }
    }

    tasks.register("nuclearClean", Delete::class) {
        delete(rootProject.layout.buildDirectory)
        dependsOn("cleanOpenApiGenerated")
    }
    tasks.register("bleedingEdgeBuild") {
        group = "genesis"
        description = "Full bleeding-edge Genesis build with auto-provisioning"
        dependsOn("nuclearClean", "generateAllOpenApiClients", "build")
    }
}
