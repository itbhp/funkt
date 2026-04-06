plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.owasp)
    id("idea")
}

dependencies {
    implementation(project(":core"))
    implementation(platform(libs.kotlin.bom))
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.assertj)
    testImplementation(libs.kotest.runner)
    testImplementation(libs.kotest.property)
}

val detektClasspath by configurations.creating

dependencies {
    detektClasspath(libs.detekt.cli)
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
    version.set(libs.versions.ktlintPinterest)
}
