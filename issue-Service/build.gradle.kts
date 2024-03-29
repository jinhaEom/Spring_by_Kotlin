import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
	id("org.springframework.boot") version "2.7.3" apply false
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}


java.sourceCompatibility = JavaVersion.VERSION_17

allprojects{
	group = "com.bu.ac.kr"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}
subprojects{
	apply(plugin = "kotlin")
	apply(plugin= "kotlin-spring")
	apply(plugin="io.spring.dependency-management")

	dependencies {
		// JWT 인증
		implementation("com.auth0:java-jwt:3.19.2")

		//kotlin 로깅
		implementation("io.github.microutils:kotlin-logging:1.12.5")

		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

		//H2DB
		runtimeOnly("com.h2database:h2")
		testImplementation("org.springframework.boot:spring-boot-starter-test")


	}
	dependencyManagement{
		imports{
			mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)

		}
	}




	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}




