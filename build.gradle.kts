subprojects {
  apply(plugin = "java")
  apply(plugin = "idea")
  dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.assertj:assertj-core:3.21.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.0.0")
    testImplementation("io.kotest:kotest-property:5.0.0")
  }

  repositories {
    mavenLocal()
    mavenCentral()
  }
  tasks {
    test {
      testLogging {
        events("passed", "skipped", "failed")
      }
      reports {
        html.required.set(true)
      }
    }
  }
}

repositories {
  mavenLocal()
  mavenCentral()
}

plugins {
  id("org.jetbrains.kotlin.jvm") version "1.5.31"
  idea
}

allprojects {
  tasks {
    test {
      useJUnitPlatform()
    }
  }
}