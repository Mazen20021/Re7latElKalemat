plugins {
    kotlin("jvm") version "1.8.10" // Use appropriate version for Kotlin DSL if needed
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // Corrected syntax for Kotlin DSL
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.modelmapper:modelmapper:2.4.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
