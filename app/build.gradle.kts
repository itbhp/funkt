/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("it.twinsbrain.funkt.kotlin-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":utilities"))
}

application {
    // Define the main class for the application.
    mainClass.set("it.twinsbrain.funkt.app.AppKt")
}
