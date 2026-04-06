import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.jvm)
}

val javaVersion: String? = libs.versions.java.get()

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenLocal()
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion))
        }
    }

    tasks.register<JavaExec>("detekt") {
        mainClass.set("io.gitlab.arturbosch.detekt.cli.Main")
        classpath = configurations["detektClasspath"]

        val input = projectDir
        val config = "${rootProject.projectDir}/detekt-config.yml"
        val exclude = ".*/build/.*,.*/resources/.*"
        val params = listOf("-i", input, "-c", config, "-ex", exclude)

        args(params)
    }

    tasks.check {
        dependsOn("detekt")
    }

    tasks.withType<KotlinCompile>().all {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(javaVersion!!))
            freeCompilerArgs.add("-Xskip-metadata-version-check")
        }
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}
