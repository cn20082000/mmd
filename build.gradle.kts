import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.21"
	kotlin("plugin.spring") version "1.7.21"
	kotlin("plugin.jpa") version "1.7.21"
}

group = "com.cn"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Thư viện kotlin basic
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Thư viện cơ bản của spring
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Sử dụng cho việc kết nối tới database
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.1")
	runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")

	// Sử dụng cho việc validate dữ liệu
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Thư việc lombok chứa các anno để sinh ra các hàm thông dụng, thường kotlin có sẵn rồi nên thêm thư viện này cho vui
	annotationProcessor("org.projectlombok:lombok")
	compileOnly("org.projectlombok:lombok")

	// Sử dụng cho việc mã hóa, giải mã, băm
	implementation("commons-codec:commons-codec")

	// Sử dụng để parse localdatetime
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict", "-opt-in=kotlin.RequiresOptIn")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
