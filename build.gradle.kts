plugins {
    java
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "ro.dorobantiu"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-jersey")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-quartz")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
    implementation("org.apache.poi:poi:5.2.3")
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    implementation("org.jsoup:jsoup:1.15.4")
    implementation ("com.moandjiezana.toml:toml4j:0.7.2")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
