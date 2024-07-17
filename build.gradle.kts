plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("java")
}

group = "org.nwtls"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    implementation("cloud.commandframework:cloud-paper:1.8.4")
}

tasks.test {
    useJUnitPlatform()
}

project.tasks.build {
    dependsOn(tasks.shadowJar)
}
