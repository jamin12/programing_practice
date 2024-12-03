plugins {
    kotlin("jvm") version "2.0.21"
}

group = "info.jamin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")


}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}