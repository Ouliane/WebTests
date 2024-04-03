plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
    id("io.freefair.lombok") version "8.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.codeborne:selenide:7.2.2")
    implementation("io.qameta.allure:allure-selenide:2.26.0")
    implementation("org.slf4j:slf4j-api:1.7.25")
    testImplementation("org.slf4j:slf4j-log4j12:2.0.12")
    testImplementation("org.assertj:assertj-core:3.6.1")
}

tasks.test {
    useJUnitPlatform()
}
