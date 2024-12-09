plugins {
    kotlin("jvm") version "2.0.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.code.gson:gson:2.10") // dependencia para JSON
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(15)
}