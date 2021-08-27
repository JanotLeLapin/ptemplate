import org.apache.tools.ant.filters.FixCrLfFilter
import org.apache.tools.ant.filters.ReplaceTokens
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    id("java")
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("com.github.gmazzo.buildconfig") version "3.0.0"
    id("org.ec4j.editorconfig") version "0.0.3"
}

group = "fr.janotlelapin.ptemplate"
version = "1.0.0-SNAPSHOT"

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
}

val minecraft: String by project

dependencies {
    compileOnly("org.spigotmc", "spigot-api", "$minecraft-R0.1-SNAPSHOT")
    compileOnly("com.github.azbh111", "craftbukkit-1.8.8", "R")
    implementation("org.reflections", "reflections", "0.9.12")

    testImplementation("org.mockito", "mockito-core", "3.11.2")
    testImplementation("org.mockito", "mockito-inline", "3.11.2")
    testImplementation("junit", "junit", "4.13")
}

configurations {
    testImplementation.get().extendsFrom(compileOnly.get())
}

tasks {
    processResources {
        filter(FixCrLfFilter::class)
        filter(ReplaceTokens::class, "tokens" to mapOf("version" to project.version))
        filteringCharset = "UTF-8"
    }
    jar {
        enabled = false
    }
    build {
        dependsOn(shadowJar)
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
