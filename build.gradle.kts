import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm") version "latest.release"

    // Apply the application plugin to add support for building a CLI application in Java.
    application

    // For generating fat jars
    id("com.github.johnrengelman.shadow") version "latest.release"
}

group = "de.jotoho"
version = "0.1.1"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform(kotlin("bom", "latest.release")))

    // Use the Kotlin standard library.
    implementation(kotlin("stdlib", "latest.release"))

    // Use the Kotlin test library.
    testImplementation(kotlin("test", "latest.release"))

    // Use the Kotlin JUnit integration.
    testImplementation(kotlin("test-junit", "latest.release"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
            "Implementation-Title" to "waituntil",
            "Implementation-Version" to "${project.version}",
            "Main-Class" to "de.jotoho.waituntil.StartKt"
        )
    }
}

tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "16"
}

application {
    // Define the main class for the application.
    mainClass.set("de.jotoho.waituntil.StartKt")
}
