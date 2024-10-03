plugins {
    kotlin("jvm") version "2.0.0"
    id("io.github.goooler.shadow") version "8.1.8"
}

group = "kr.jimin.vortexen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.org/repository/maven-public/")

    maven("https://repo.oraxen.com/releases")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")

    implementation("dev.dejvokep:boosted-yaml:1.3.6")
    implementation("dev.dejvokep:boosted-yaml-spigot:1.5")

    implementation("dev.jorel:commandapi-bukkit-shade:9.5.2")
    implementation("net.kyori:adventure-platform-bukkit:4.3.4")

    compileOnly("io.th0rgal:oraxen:1.182.0")
    compileOnly("com.github.LoneDev6:API-ItemsAdder:3.6.1")

    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()
    }

    shadowJar {
        archiveFileName.set("Vortexen-${rootProject.version}.jar")
        destinationDirectory.set(file("C:\\Users\\aa990\\OneDrive\\바탕 화면\\테스트 서버\\Vortexen(100)\\plugins"))
        // destinationDirectory.set(file("C:\\Users\\aa990\\OneDrive\\바탕 화면\\테스트 서버\\Vortexen-itemsadder(101)\\plugins"))
    }

    build.get().dependsOn(shadowJar)
}

kotlin {
    jvmToolchain(21)
}