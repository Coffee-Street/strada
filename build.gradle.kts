import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
 //   id("com.github.hierynomus.license") version "0.15.0"  //TODO:
    id("pmd")
    id("jacoco")
    id("idea")
//    id("checkstyle") //TODO:
    kotlin("jvm") version "1.3.71"
    kotlin("kapt") version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
    kotlin("plugin.jpa") version "1.3.71"
}

group = "com.wnsgml972"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

// TODO: gradle 정리 좀..
// https://woowabros.github.io/tools/2019/04/30/gradle-kotlin-dsl.html
dependencies {
    // Spring Boot Starter
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.springframework:spring-test")

    // Spring Boot Devtools
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    // Spring Boot Starter WebFlux (React)
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("io.projectreactor:reactor-test")

    // Spring Boot Starter Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")
    implementation(kotlin("stdlib-jdk8"))

    // Spring AoP
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.aspectj:aspectjrt:1.8.13")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // for query dsl
    api("com.querydsl:querydsl-jpa:4.2.2")
    kapt("com.querydsl:querydsl-apt:4.2.2:jpa")
    kapt("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.2.Final")

    // mysql
    implementation("mysql:mysql-connector-java")

    // swagger
    implementation("io.springfox:springfox-swagger2:2.6.1")
    implementation("io.springfox:springfox-swagger-ui:2.6.1")

    // configure
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation(group = "org.springframework.security.oauth.boot", name = "spring-security-oauth2-autoconfigure", version = "2.2.5.RELEASE")

    // flyway
    implementation("org.flywaydb:flyway-core:5.0.7")

    implementation(group = "com.google.code.gson", name = "gson", version = "2.8.5")
    implementation(group = "org.modelmapper", name = "modelmapper", version = "2.3.2")

    implementation("io.github.microutils:kotlin-logging:1.7.9")
    implementation("org.jetbrains.kotlin:kotlin-noarg")
    implementation("org.jetbrains.kotlin:kotlin-allopen")

    implementation("com.h2database:h2")
    implementation("org.postgresql:postgresql")

    // ss
    implementation("org.apache.commons:commons-lang3:3.11")
    implementation("net.logstash.logback:logstash-logback-encoder:5.3")

    // jackson
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.module:jackson-module-afterburner:2.7.1")

    implementation("com.github.pagehelper:pagehelper-spring-boot-starter:1.2.13")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

//tasks.withType<KotlinCompile> {
//    kotlinOptions {
//        freeCompilerArgs = listOf("-Xjsr305=strict")
//        jvmTarget = "1.8"
//    }
//}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}
allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
    kotlin.srcDir("$buildDir/generated/source/kapt/main")
}

// https://umbum.dev/1039
sourceSets {
    main {
        resources {
            srcDir(listOf("src/main/resources"))
        }
    }
}

//tasks.test {
//    outputs.dir(snippetsDir)
//}
//
//tasks.asciidoctor {
//    inputs.dir(snippetsDir)
//    dependsOn(test)
//}


//
//// [ Plugin 세부 설정 ] ------------------------------------------------------------------------
//// 1. Check Style
//// Reference : https://java.ihoney.pe.kr/367
//// Reference : https://docs.gradle.org/current/dsl/org.gradle.api.plugins.quality.Checkstyle.html
//// Remote Code Style 라이브러리
//checkstyle {
//    maxWarnings = 0
//    configFile = file("checkstyle/checkstyle.xml") // 1.1.2 에서 작성한 checkstyle 파일 지정
//    reportsDir = file("${buildDir}/checkstyle-output") // 리포트 파일이 위치할 디렉토리 지정
//}
//
//checkstyleMain {
//    reports {
//        xml.destination = file("${checkstyle.reportsDir}/checkstyle-report.xml") // 리포트 파일의 위치 및 파일명 지정
//    }
//}
//
//// 2. pmd
//// Reference : https://java.ihoney.pe.kr/367
//// 코드 정적 분석 라이브러리
//pmd {
//    ignoreFailures = true // 분석결과 예외가 발생하면 빌드실패 발생시키는 것을 제외
//    reportsDir = file("${buildDir}/pmd-output")
//}
//
//pmdMain {
//    reports {
//        xml.destination = file("${pmd.reportsDir}/pmd-report.xml")
//        xml.enabled = true
//    }
//}
//
//// 3. license
//// 코드 내 라이센스 관련 라이브러리
//// 프로젝트의 모든 소스코드에는 라이선스가 표기되어 있어야 한다는 요구사항이 있다고 가정하자.
//// 이제 헤더에 라이선스가 기록되지 않은 소스코드를 만들고 빌드하면 빌드 에러가 발생할 것이다.
//// `./gradlew licenseFormat` 를 실행하면 자동으로 라이선스가 빠진 소스코드에 헤더를 넣어준다.
//license {
//    header rootProject.file('LICENSE')
//    strictCheck true
//    ext {
//        year = Calendar.getInstance().get(Calendar.YEAR)
//        name = 'wnsgml972'
//        email = 'wnsgml972@gmail.com'
//    }
//    mapping {
//        javascript = 'JAVADOC_STYLE'
//    }
//}
//
//
//// 4. Jacoco
//// Test Coverage 라이브러리
//// jacoco Reference : https://woowabros.github.io/experience/2020/02/02/jacoco-config-on-gradle-project.html
//// jacoco github badge Reference : https://github.com/dawnwords/jacoco-badge-gradle-plugin
//test {
//    jacoco {
//        destinationFile = file("$buildDir/jacoco/jacoco.exec")
//    }
//
//    useJUnitPlatform()
//
//    finalizedBy 'jacocoTestReport'
//}
//
//jacoco {
//    // JaCoCo 버전
//    toolVersion = '0.8.6'
//
////  테스트결과 리포트를 저장할 경로 변경
////  default 는 "${project.reporting.baseDir}/jacoco"
////  reportsDir = file("$buildDir/customJacocoReportDir")
//}
//
//jacocoTestReport {
//    reports {
//        // 원하는 리포트를 켜고 끌 수 있다.
//        xml.enabled true
//        html.enabled true
//        csv.enabled false
//
////      각 리포트 타입 마다 리포트 저장 경로를 설정할 수 있다.
//        xml.destination file("jacoco.xml")
//    }
//
//    finalizedBy 'jacocoTestCoverageVerification'
//}
//
//// TODO: 좀 짜면 각 커버리지 설정들 좀 풀어 놓기..
//jacocoTestCoverageVerification {
//    violationRules {
//        rule {
//            // 'element' 가 없으면 프로젝트의 전체 파일을 합친 값을 기준으로 한다.
//            limit {
//                // 'counter' 를 지정하지 않으면 default 는 'INSTRUCTION'
//                // 'value' 를 지정하지 않으면 default 는 'COVERED RATIO'
////                minimum = 0.30
//            }
//        }
//
//        rule {
//            // 룰을 간단히 켜고 끌 수 있다.
//            enabled = true
//
//            // 룰을 체크할 단위는 클래스 단위
//            element = 'CLASS'
//
//            // 브랜치 커버리지를 최소한 90% 만족시켜야 한다.
//            limit {
//                counter = 'BRANCH'
//                value = 'COVEREDRATIO'
////                minimum = 0.90
//            }
//
//            // 라인 커버리지를 최소한 80% 만족시켜야 한다.
//            limit {
//                counter = 'LINE'
//                value = 'COVEREDRATIO'
////                minimum = 0.80
//            }
//
//            // 빈 줄을 제외한 코드의 라인수를 최대 200라인으로 제한한다.
//            limit {
//                counter = 'LINE'
//                value = 'TOTALCOUNT'
//                maximum = 200
//            }
//
//            // 커버리지 체크를 제외할 클래스들
//            excludes = [
//                    '*.test.*',
//            ]
//        }
//    }
//}
//
//task testCoverage(type: Test) {
//    group 'verification'
//    description 'Runs the unit tests with coverage'
//
//    dependsOn(':test',
//            ':jacocoTestReport',
//            ':jacocoTestCoverageVerification')
//
//    tasks['jacocoTestReport'].mustRunAfter(tasks['test'])
//    tasks['jacocoTestCoverageVerification'].mustRunAfter(tasks['jacocoTestReport'])
//}
//