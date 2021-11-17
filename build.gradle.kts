subprojects {
  apply(plugin = "java")
  dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
  }
  repositories {
    mavenLocal()
    mavenCentral()
  }
}

repositories {
  mavenLocal()
  mavenCentral()
}

plugins {
  id("org.jetbrains.kotlin.jvm") version "1.5.31"
}

tasks {
  test {
    useJUnitPlatform()
  }
}