plugins {
    kotlin("jvm") version "2.0.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin test dependency
    testImplementation(kotlin("test"))

    // Gson library to handle JSON
    implementation("com.google.code.gson:gson:2.10.1")  // Add Gson here
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
