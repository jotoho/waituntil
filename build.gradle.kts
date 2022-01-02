plugins {
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

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.jar {
    manifest {
        attributes(
            "Implementation-Title" to "de.jotoho.waituntil",
            "Implementation-Version" to "${project.version}",
            "Main-Class" to "de.jotoho.waituntil.Main"
        )
    }
}

application {
    // Define the main class for the application.
    mainClass.set("de.jotoho.waituntil.Main")
}
