plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.example.back"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.assertj:assertj-core:3.26.3")
    implementation("com.google.code.gson:gson:2.10.1")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}