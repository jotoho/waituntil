import java.io.ByteArrayOutputStream
import java.io.OutputStream

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    java
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation(group="org.apache", name="commons-cli", version="1.5.0")
}

fun versionBanner(): String {
    val os = ByteArrayOutputStream()
    val devNull = OutputStream.nullOutputStream()
    project.exec {
        commandLine = "git describe --tags --always --dirty --abbrev".split(" ")
        standardOutput = os
        errorOutput = devNull
    }
    return String(os.toByteArray()).trim()
}

group = "de.jotoho"
version = versionBanner()

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.jar {
    manifest {
        attributes(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Main-Class" to "de.jotoho.waituntil.Main",
            "Main-Module" to "de.jotoho.waituntil.main"
        )
    }
}

application {
    // Define the main class for the application.
    mainClass.set("de.jotoho.waituntil.Main")
    mainModule.set("de.jotoho.waituntil.main")
}
