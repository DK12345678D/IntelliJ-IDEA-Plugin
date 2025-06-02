plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.17.3"
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

intellij {
    version.set("2024.1")
    type.set("IC")
    plugins.set(listOf("Kotlin"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}