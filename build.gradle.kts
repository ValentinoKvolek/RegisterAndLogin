plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "2.3.4" //add plugin for ktor
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {

    //ktor
    implementation("io.ktor:ktor-server-core:2.3.4")
    implementation("io.ktor:ktor-server-netty:2.3.4")
    implementation("io.ktor:ktor-server-html-builder:2.3.4")
    implementation("io.ktor:ktor-server-request-validation:2.3.4")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-gson:2.3.4")
    implementation("io.ktor:ktor-server-call-logging:2.3.4")
    implementation("ch.qos.logback:logback-classic:1.4.12")


    implementation("org.mindrot:jbcrypt:0.4") // Bcrypt hashing
    implementation("com.google.code.gson:gson:2.10.1") // gson
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

