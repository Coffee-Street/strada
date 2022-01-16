import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin

plugins {
    base
    java
    `maven-publish`
    jacoco
    kotlin("jvm") version Versions.kotlin

    kotlin("plugin.serialization") version Versions.kotlin apply false
    kotlin("plugin.allopen") version Versions.kotlin apply false
    kotlin("plugin.noarg") version Versions.kotlin apply false
    kotlin("plugin.spring") version Versions.kotlin apply false
    kotlin("plugin.jpa") version Versions.kotlin apply false
    kotlin("kapt") version Versions.kotlin apply false

    id(Plugins.spring_boot) version Plugins.Versions.spring_boot apply false
    id(Plugins.dependency_management) version Plugins.Versions.dependency_management

    id(Plugins.propdeps) version Plugins.Versions.propdeps
    id(Plugins.propdeps_idea) version Plugins.Versions.propdeps
    id(Plugins.propdeps_maven) version Plugins.Versions.propdeps

    id(Plugins.dokka) version Plugins.Versions.dokka
    id(Plugins.detekt) version Plugins.Versions.detekt
}

val projectGroup: String by project
val baseVersion: String by project
val snapshotVersion: String by project

allprojects {

    group = projectGroup
    version = baseVersion + snapshotVersion

    repositories {
        mavenCentral()
        google()

        // for Oracle ojdbc10
        maven {
            name = "ICM"
            setUrl("http://maven.icm.edu.pl/artifactory/repo/")
        }

        maven {
            name = "jitpack"
            setUrl("https://jitpack.io")
        }
    }
}


subprojects {
    configurations.all {
        if (name.toLowerCase().contains("kapt") || name.toLowerCase().contains("proto")) {
            attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
        }
    }
    configurations.forEach {
        if (it.name.contains("productionRuntimeClasspath")) {
            it.attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
        }
    }

    apply {
        plugin<JavaLibraryPlugin>()
        plugin<KotlinPlatformJvmPlugin>()

        plugin("jacoco")
        plugin("maven-publish")

        plugin(Plugins.dependency_management)
        plugin(Plugins.propdeps)
        plugin(Plugins.propdeps_idea)
        plugin(Plugins.propdeps_maven)

        plugin(Plugins.dokka)
        plugin(Plugins.detekt)
    }

    val javaVersion = JavaVersion.VERSION_11.toString()

    tasks {

        compileJava {
            sourceCompatibility = javaVersion
            targetCompatibility = javaVersion
        }

        compileKotlin {
            sourceCompatibility = javaVersion
            kotlinOptions {
                jvmTarget = javaVersion
                freeCompilerArgs = listOf(
                    "-Xjsr305=strict",
                    "-Xjvm-default=enable",
                    "-Xinline-classes",
                    "-progressive",
                    "-Xstring-concat=indy"  // since Kotlin 1.4.20
                )

                val experimentalAnnotations = listOf(
                    "kotlin.Experimental",
                    "kotlin.ExperimentalStdlibApi",
                    "kotlin.contracts.ExperimentalContracts",
                    "kotlin.experimental.ExperimentalTypeInference",
                    "kotlin.ExperimentalMultiplatform",
                    "kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "kotlinx.coroutines.ObsoleteCoroutinesApi",
                    "kotlinx.coroutines.InternalCoroutinesApi",
                    "kotlinx.coroutines.FlowPreview"
                )
                freeCompilerArgs = freeCompilerArgs.plus(experimentalAnnotations.map { "-Xuse-experimental=$it" })
            }
        }

        compileTestKotlin {
            kotlinOptions {
                jvmTarget = javaVersion
                freeCompilerArgs = listOf(
                    "-Xjsr305=strict",
                    "-Xjvm-default=enable",
                    "-Xinline-classes",
                    "-progressive",
                    "-Xstring-concat=indy"      // since Kotlin 1.4.20
                )

                val experimentalAnnotations = listOf(
                    "kotlin.Experimental",
                    "kotlin.ExperimentalStdlibApi",
                    "kotlin.contracts.ExperimentalContracts",
                    "kotlin.experimental.ExperimentalTypeInference",
                    "kotlin.ExperimentalMultiplatform",
                    "kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "kotlinx.coroutines.ObsoleteCoroutinesApi",
                    "kotlinx.coroutines.InternalCoroutinesApi",
                    "kotlinx.coroutines.FlowPreview"
                )
                freeCompilerArgs = freeCompilerArgs.plus(experimentalAnnotations.map { "-Xuse-experimental=$it" })
            }
        }

        test {
            useJUnitPlatform()

            testLogging {
                showStandardStreams = true
                showExceptions = true
                showCauses = true
                showStackTraces = true
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL

                events("failed")
            }
        }

        detekt {
            autoCorrect = true
        }

        jacoco {
            toolVersion = Plugins.Versions.jacoco
        }

        // Configure existing Dokka task to output HTML to typical Javadoc directory
        dokka {
            outputFormat = "html"
            outputDirectory = "$buildDir/javadoc"

            // externalDocumentationLink { url = URL("https://docs.oracle.com/javase/8/docs/api/") }
            // commons-io 가 제대로 Link 되는데도, dokka에서 예외를 발생시킨다. 우선은 link 안되게 막음
            // externalDocumentationLink { url = URL("https://commons.apache.org/proper/commons-io/javadocs/api-2.6/") }
        }

        jacocoTestReport {
            reports {
                html.isEnabled = true
                xml.isEnabled = true
            }
        }

        jacocoTestCoverageVerification {
            dependsOn(jacocoTestReport)

            violationRules {
                rule {
                    // 룰 검증 수행 여부
                    enabled = true

                    // 룰을 검증할 단위를 클래스 단위로 한다
                    element = "CLASS" // BUNDLE|PACKAGE|CLASS|SOURCEFILE|METHOD

                    // 브랜치 커버리지를 최소한 10% 를 만족시켜야 한다
                    limit {
                        counter =
                            "INSTRUCTION" // INSTRUCTION, LINE, BRANCH, COMPLEXITY, METHOD and CLASS. Defaults to INSTRUCTION.
                        value =
                            "COVEREDRATIO" // TOTALCOUNT, MISSEDCOUNT, COVEREDCOUNT, MISSEDRATIO and COVEREDRATIO. Defaults to COVEREDRATIO
                        minimum = 0.10.toBigDecimal()
                    }
                }
            }
        }

        jar {
            manifest.attributes["Specification-Title"] = project.name
            manifest.attributes["Specification-Version"] = project.version
            manifest.attributes["Implementation-Title"] = project.name
            manifest.attributes["Implementation-Version"] = project.version
            manifest.attributes["Automatic-Module-Name"] = project.name.replace('-', '.')
            manifest.attributes["Created-By"] =
                "${System.getProperty("java.version")} (${System.getProperty("java.specification.vendor")})"
        }

        clean {
            doLast {
                delete("./.project")
                delete("./out")
                delete("./bin")
            }
        }
    }

    dependencyManagement {
        imports {
            mavenBom(Libs.spring_boot_dependencies)
            mavenBom(Libs.spring_cloud_dependencies)
            mavenBom(Libs.log4j_bom)
            mavenBom(Libs.testcontainers_bom)
            mavenBom(Libs.jackson_bom)
            mavenBom(Libs.metrics_bom)
            mavenBom(Libs.micrometer_bom)

            mavenBom(Libs.reactor_bom)
            mavenBom(Libs.kotlin_bom)
            mavenBom(Libs.kotlinx_coroutines_bom)
        }
        dependencies {
            dependency(Libs.jetbrains_annotations)

            dependency(Libs.kotlinx_coroutines_core)
            dependency(Libs.kotlinx_coroutines_core_common)
            dependency(Libs.kotlinx_coroutines_jdk7)
            dependency(Libs.kotlinx_coroutines_jdk8)
            dependency(Libs.kotlinx_coroutines_jdk9)
            dependency(Libs.kotlinx_coroutines_reactive)
            dependency(Libs.kotlinx_coroutines_reactor)
            dependency(Libs.kotlinx_coroutines_rx2)
            dependency(Libs.kotlinx_coroutines_test)
            dependency(Libs.kotlinx_coroutines_debug)

            dependency(Libs.kotlinx_io)
            dependency(Libs.kotlinx_io_jvm)
            dependency(Libs.kotlinx_coroutines_io_jvm)

            // Apache Commons
            dependency(Libs.commons_beanutils)
            dependency(Libs.commons_collections4)
            dependency(Libs.commons_compress)
            dependency(Libs.commons_codec)
            dependency(Libs.commons_csv)
            dependency(Libs.commons_lang3)
            dependency(Libs.commons_logging)
            dependency(Libs.commons_math3)
            dependency(Libs.commons_pool2)
            dependency(Libs.commons_text)
            dependency(Libs.commons_exec)
            dependency(Libs.commons_io)

            dependency(Libs.slf4j_api)
            dependency(Libs.logback)
            dependency(Libs.logstash)

            // Jakarta API
            dependency(Libs.jakarta_activation_api)
            dependency(Libs.jakarta_annotation_api)
            dependency(Libs.jakarta_el_api)
            dependency(Libs.jakarta_inject_api)
            dependency(Libs.jakarta_interceptor_api)
            dependency(Libs.jakarta_jms_api)
            dependency(Libs.jakarta_json_api)
            dependency(Libs.jakarta_persistence_api)
            dependency(Libs.jakarta_servlet_api)
            dependency(Libs.jakarta_transaction_api)
            dependency(Libs.jakarta_validation_api)

            // Compressor
            dependency(Libs.snappy_java)
            dependency(Libs.lz4_java)
            dependency(Libs.zstd_jni)

            // Java Money
            dependency(Libs.javax_money_api)
            dependency(Libs.javamoney_moneta)

            dependency(Libs.findbugs)
            dependency(Libs.guava)
            dependency(Libs.joda_time)

            dependency(Libs.fst)
            dependency(Libs.kryo)
            dependency(Libs.kryo_serializers)

            dependency(Libs.typesafe_config)

            dependency(Libs.rxjava2)
            dependency(Libs.rxkotlin2)

            // Hibernate
            dependency(Libs.hibernate_core)
            dependency(Libs.hibernate_jcache)
            dependency(Libs.javassist)

            dependency(Libs.querydsl_apt)
            dependency(Libs.querydsl_core)
            dependency(Libs.querydsl_jpa)

            // Validators
            dependency(Libs.javax_validation_api)
            dependency(Libs.hibernate_validator)
            dependency(Libs.hibernate_validator_annotation_processor)
            dependency(Libs.javax_el)

            dependency(Libs.hikaricp)
            dependency(Libs.mysql_connector_java)
            dependency(Libs.mariadb_java_client)
            dependency(Libs.h2)

            // Metrics
            dependency(Libs.latencyUtils)
            dependency(Libs.hdrHistogram)

            dependency(Libs.objenesis)

            dependency(Libs.junit_jupiter)
            dependency(Libs.junit_jupiter_api)
            dependency(Libs.junit_jupiter_engine)
            dependency(Libs.junit_jupiter_params)
            dependency(Libs.junit_jupiter_migrationsupport)

            dependency(Libs.junit_platform_commons)
            dependency(Libs.junit_platform_launcher)
            dependency(Libs.junit_platform_runner)
            dependency(Libs.junit_platform_engine)
            dependency(Libs.junit_platform_suite_api)

            dependency(Libs.kluent)
            dependency(Libs.assertj_core)

            dependency(Libs.mockk)
            dependency(Libs.mockito_core)
            dependency(Libs.mockito_junit_jupiter)
            dependency(Libs.mockito_kotlin)

            dependency(Libs.random_beans)
            dependency(Libs.javafaker)

            dependency(Libs.bouncycastle_bcpkix)
            dependency(Libs.bouncycastle_bcprov)

            dependency(Libs.prometheus_simpleclient)
            dependency(Libs.prometheus_simpleclient_common)
            dependency(Libs.prometheus_simpleclient_pushgateway)
            dependency(Libs.prometheus_simpleclient_spring_boot)

            // Springdoc OpenAPI
            dependency(Libs.springdoc_openapi_ui)
            dependency(Libs.springdoc_openapi_webflux_ui)
            dependency(Libs.springdoc_openapi_security)
        }
    }

    dependencies {
        val api by configurations
        val testApi by configurations
        val compileOnly by configurations
        val implementation by configurations
        val testImplementation by configurations

        val testRuntimeOnly by configurations

        implementation(Libs.jetbrains_annotations)

        implementation(Libs.kotlin_stdlib)
        implementation(Libs.kotlin_stdlib_common)
        implementation(Libs.kotlin_stdlib_jdk7)
        implementation(Libs.kotlin_stdlib_jdk8)
        implementation(Libs.kotlin_reflect)
        testImplementation(Libs.kotlin_test)
        testImplementation(Libs.kotlin_test_junit5)

        api(Libs.slf4j_api)
        api(Libs.logstash)
        testApi(Libs.logback)

        testImplementation(Libs.junit_jupiter)
        testImplementation(Libs.junit_jupiter_migrationsupport)
        testRuntimeOnly(Libs.junit_platform_engine)

        testImplementation(Libs.kluent)
        testImplementation(Libs.mockk)
        testImplementation(Libs.testcontainers)
        testImplementation(Libs.testcontainers_junit_jupiter)

        // Property based test
        testImplementation(Libs.javafaker)
        testImplementation(Libs.random_beans)

        detektPlugins(Libs.detekt_formatting)
    }

    tasks.withType<Jar> {
        manifest.attributes["Specification-Title"] = project.name
        manifest.attributes["Specification-Version"] = project.version
        manifest.attributes["Implementation-Title"] = project.name
        manifest.attributes["Implementation-Version"] = project.version
        manifest.attributes["Automatic-Module-Name"] = project.name.replace('-', '.')
        manifest.attributes["Created-By"] =
            "${System.getProperty("java.version")} (${System.getProperty("java.specification.vendor")})"
    }
}
