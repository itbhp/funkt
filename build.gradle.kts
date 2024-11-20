plugins {
    val pluginsVersions = object {
        val kotlin = "1.9.20"
        val ktLint = "10.2.0"
        val detekt = "1.18.1"
        val owasp = "6.4.1.1"
    }
    kotlin("jvm") version pluginsVersions.kotlin
    id("idea")
    id("org.jlleitschuh.gradle.ktlint") version pluginsVersions.ktLint
    id("io.gitlab.arturbosch.detekt") version pluginsVersions.detekt
    id("org.owasp.dependencycheck") version pluginsVersions.owasp
}

subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    apply(plugin = "java")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.owasp.dependencycheck")

    val detekt by configurations.creating

    tasks.register<JavaExec>("detekt") {
        mainClass.set("io.gitlab.arturbosch.detekt.cli.Main")
        classpath = detekt

        val input = projectDir
        val config = "${rootProject.projectDir}/detekt-config.yml"
        val exclude = ".*/build/.*,.*/resources/.*"
        val params = listOf("-i", input, "-c", config, "-ex", exclude)

        args(params)
    }

    apply(plugin = "idea")

    dependencies {
        implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
        testImplementation("org.junit.jupiter", "junit-jupiter-api", depVersion("junit.version"))
        testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", depVersion("junit.version"))
        testImplementation("org.assertj", "assertj-core", depVersion("assertj.version"))
        testImplementation("io.kotest", "kotest-runner-junit5", depVersion("kotest.version"))
        testImplementation("io.kotest", "kotest-property", depVersion("kotest.version"))
        detekt("io.gitlab.arturbosch.detekt:detekt-cli:${depVersion("detekt.version")}")
    }

    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
        reports {
            html.required.set(true)
        }
    }

    ktlint {
        version.set(depVersion("ktlint.pinterest.version"))
    }

    tasks.check {
        dependsOn("detekt")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

fun depVersion(key: String): String = project.properties[key].toString()
