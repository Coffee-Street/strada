object Plugins {

    object Versions {
        const val detekt = "1.14.1"
        const val dokka = "0.10.1"
        const val dependency_management = "1.0.10.RELEASE"
        const val propdeps = "0.0.10"
        const val jooq = "3.0.3"
        const val protobuf = "0.8.13"
        const val avro = "0.17.0"
        const val jarTest = "1.0.1"
        const val testLogger = "2.1.0"
        const val kotlinx_benchmark = "0.2.0-dev-20"
        const val spring_boot = "2.5.2"
        const val jacoco = "0.8.6"
        const val kotlin_noarg = "1.4.32"
    }

    const val detekt = "io.gitlab.arturbosch.detekt"
    const val dokka = "org.jetbrains.dokka"
    const val dependency_management = "io.spring.dependency-management"
    const val spring_boot = "org.springframework.boot"

    const val propdeps = "cn.bestwu.propdeps"
    const val propdeps_idea = "cn.bestwu.propdeps-idea"
    const val propdeps_maven = "cn.bestwu.propdeps-maven"

    const val jooq = "nu.studer.jooq"

    // https://github.com/davidmc24/gradle-avro-plugin
    const val avro = "com.commercehub.gradle.plugin.avro"

    const val jarTest = "com.github.hauner.jarTest"
    const val testLogger = "com.adarshr.test-logger"
    const val kotlinx_benchmark = "kotlinx.benchmark"
    const val kotlin_noarg = "org.jetbrains.kotlin.plugin.noarg"
}

object Versions {
    const val jetbrains_annotations = "20.1.0"

    const val kotlin = "1.5.20"
    const val atomicfu = "0.16.1"
    const val kotlinx_coroutines = "1.5.0"
    const val kotlinx_serialization = "1.2.1"
    const val kotlinx_io = "0.1.16"
    const val kotlinx_benchmark = Plugins.Versions.kotlinx_benchmark
    const val ktor = "1.5.4"

    const val spring_boot = Plugins.Versions.spring_boot
    const val spring_cloud = "2020.0.2"
    const val reactor_bom = "2020.0.7"

    const val swagger = "1.6.2"
    const val springdoc_openapi = "1.5.8"
    const val springfox_swagger = "3.0.0"
    const val problem = "0.26.2"

    const val jackson = "2.12.3"

    const val lettuce = "6.0.3.RELEASE"
    const val redisson = "3.15.2"

    const val hibernate = "5.4.31.Final"
    const val hibernate_validator = "6.1.5.Final"
    const val querydsl = "4.4.0"

    const val mu_klog = "1.4.5"
    const val slf4j = "1.7.30"
    const val logback = "1.2.3"
    const val logback_core = "1.2.3"
    const val logstash = "5.3"
    const val log4j = "2.14.1"

    const val metrics = "4.2.0"
    const val prometheus = "0.10.0"
    const val micrometer = "1.7.0"

    const val junit_jupiter = "5.7.2"
    const val junit_platform = "1.7.2"
    const val assertj_core = "3.18.1"
    const val mockk = "1.11.0"
    const val mockito = "3.7.0"
    const val jmh = "1.26"
    const val testcontainers = "1.15.2"
    const val archunit = "0.18.0"

    const val sentry_logback = "4.3.0"

    const val auth0_jwt = "3.10.3"
}

object Libs {
    const val jetbrains_annotations = "org.jetbrains:annotations:${Versions.jetbrains_annotations}"

    // kotlin
    fun kotlin(artifact: String): String = "org.jetbrains.kotlin:kotlin-$artifact:${Versions.kotlin}"

    val kotlin_bom: String get() = kotlin("bom")
    val kotlin_stdlib: String get() = kotlin("stdlib")
    val kotlin_stdlib_common: String get() = kotlin("stdlib-common")
    val kotlin_stdlib_jdk7: String get() = kotlin("stdlib-jdk7")
    val kotlin_stdlib_jdk8: String get() = kotlin("stdlib-jdk8")
    val kotlin_reflect: String get() = kotlin("reflect")
    val kotlin_test: String get() = kotlin("test")
    val kotlin_test_common: String get() = kotlin("test-common")
    val kotlin_test_junit5: String get() = kotlin("test-junit5")

    // Kotlin 1.3.40 부터는 kotlin-scripting-jsr223 만 참조하면 됩니다.
    val kotlin_scripting_jsr223: String get() = kotlin("scripting-jsr223")
    val kotlin_compiler: String get() = kotlin("compiler")

    // Kotlin 1.4+ 부터는 kotlin-scripting-dependencies 를 참조해야 합니다.
    val kotlin_scripting_dependencies: String get() = kotlin("scripting-dependencies")

    val kotlin_compiler_embeddable: String get() = kotlin("compiler-embeddable")
    val kotlin_daemon_client: String get() = kotlin("daemon-client")
    val kotlin_scripting_common: String get() = kotlin("scripting-common")
    val kotlin_scripting_compiler_embeddable: String get() = kotlin("scripting-compiler-embeddable")
    val kotlin_scripting_jvm: String get() = kotlin("scripting-jvm")
    val kotlin_script_runtime: String get() = kotlin("script-runtime")
    val kotlin_script_util: String get() = kotlin("scripting-util")

    fun kotlinxCoroutines(artifact: String): String =
        "org.jetbrains.kotlinx:kotlinx-coroutines-$artifact:${Versions.kotlinx_coroutines}"

    val kotlinx_coroutines_bom: String get() = kotlinxCoroutines("bom")
    val kotlinx_coroutines_core: String get() = kotlinxCoroutines("core")
    val kotlinx_coroutines_core_common: String get() = kotlinxCoroutines("core-common")
    val kotlinx_coroutines_debug: String get() = kotlinxCoroutines("debug")
    val kotlinx_coroutines_jdk7: String get() = kotlinxCoroutines("jdk7")
    val kotlinx_coroutines_jdk8: String get() = kotlinxCoroutines("jdk8")
    val kotlinx_coroutines_jdk9: String get() = kotlinxCoroutines("jdk9")
    val kotlinx_coroutines_reactive: String get() = kotlinxCoroutines("reactive")
    val kotlinx_coroutines_reactor: String get() = kotlinxCoroutines("reactor")
    val kotlinx_coroutines_rx2: String get() = kotlinxCoroutines("rx2")
    val kotlinx_coroutines_rx3: String get() = kotlinxCoroutines("rx3")
    val kotlinx_coroutines_test: String get() = kotlinxCoroutines("test")

    fun kotlinxSerialization(artifact: String): String =
        "org.jetbrains.kotlinx:kotlinx-serialization-$artifact:${Versions.kotlinx_serialization}"

    val kotlinx_serialization_core: String get() = kotlinxSerialization("core")
    val kotlinx_serialization_json: String get() = kotlinxSerialization("json")

    const val kotlinx_io = "org.jetbrains.kotlinx:kotlinx-io:${Versions.kotlinx_io}"
    const val kotlinx_io_jvm = "org.jetbrains.kotlinx:kotlinx-io-jvm:${Versions.kotlinx_io}"
    const val kotlinx_coroutines_io_jvm = "org.jetbrains.kotlinx:kotlinx-coroutines-io-jvm:${Versions.kotlinx_io}"

    const val kotlinx_benchmark_runtime = "org.jetbrains.kotlinx:kotlinx.benchmark.runtime:${Versions.kotlinx_benchmark}"
    const val kotlinx_benchmark_runtime_jvm = "org.jetbrains.kotlinx:kotlinx.benchmark.runtime-jvm:${Versions.kotlinx_benchmark}"

    // Ktor
    fun ktor(artifact: String): String = "io.ktor:ktor-$artifact:${Versions.ktor}"
    val ktor_server_core: String get() = ktor("server-core")
    val ktor_server_netty: String get() = ktor("server-netty")
    val ktor_http_http_jvm: String get() = ktor("http-http-jvm")
    val ktor_http_cio_jvm: String get() = ktor("http-cio-jvm")
    val ktor_utils_jvm: String get() = ktor("http-utils-jvm")
    val ktor_client_apache: String get() = ktor("client-apache")
    val ktor_client_cio: String get() = ktor("client-cio")

    // javax api (Deperecated) (Use jakarta library)
    const val javax_activation_api = "javax.activation:javax.activation-api:1.2.0"
    const val javax_annotation_api = "javax.annotation:javax.annotation-api:1.3.2"
    const val javax_cache_api = "javax.cache:cache-api:1.1.1"
    const val javax_inject = "javax.inject:javax.inject:1"
    const val javax_servlet_api = "javax.servlet:javax.servlet-api:4.0.1"
    const val javax_transaction_api = "javax.transaction:jta:1.1"
    const val javax_validation_api = "javax.validation:validation-api:2.0.1.Final"

    // jakarta
    const val jakarta_activation_api = "jakarta.activation:jakarta.activation-api:1.2.2"
    const val jakarta_annotation_api = "jakarta.annotation:jakarta.annotation-api:1.3.5"
    const val jakarta_el_api = "jakarta.el:jakarta.el-api:3.0.3"
    const val jakarta_el = "org.glassfish:jakarta.el:3.0.3"
    const val jakarta_inject_api = "jakarta.inject:jakarta.inject-api:1.0.1"
    const val jakarta_interceptor_api = "jakarta.interceptor:jakarta.interceptor-api:1.2.5"
    const val jakarta_jms_api = "jakarta.jms:jakarta.jms-api:2.0.3"
    const val jakarta_json_api = "jakarta.json:jakarta.json-api:1.1.6"
    const val jakarta_persistence_api = "jakarta.persistence:jakarta.persistence-api:2.2.3"
    const val jakarta_servlet_api = "jakarta.servlet:jakarta.servlet-api:4.0.4"
    const val jakarta_transaction_api = "jakarta.transaction:jakarta.transaction-api:1.3.3"
    const val jakarta_validation_api = "jakarta.validation:jakarta.validation-api:2.0.2"

    // Java Money
    const val javax_money_api = "javax.money:money-api:1.1"
    const val javamoney_moneta = "org.javamoney:moneta:1.4.2"

    // Apache Commons
    const val commons_beanutils = "commons-beanutils:commons-beanutils:1.9.4"
    const val commons_compress = "org.apache.commons:commons-compress:1.20"
    const val commons_codec = "commons-codec:commons-codec:1.15"
    const val commons_collections4 = "org.apache.commons:commons-collections4:4.4"
    const val commons_csv = "org.apache.commons:commons-csv:1.8"
    const val commons_digest3 = "org.apache.commons:commons-digester3:3.2"
    const val commons_exec = "org.apache.commons:commons-exec:1.3"
    const val commons_io = "commons-io:commons-io:2.8.0"
    const val commons_lang3 = "org.apache.commons:commons-lang3:3.11"
    const val commons_logging = "commons-logging:commons-logging:1.2"
    const val commons_math3 = "org.apache.commons:commons-math3:3.6.1"
    const val commons_pool2 = "org.apache.commons:commons-pool2:2.8.0"
    const val commons_rng_simple = "org.apache.commons:commons-rng-simple:1.3"
    const val commons_text = "org.apache.commons:commons-text:1.9"
    const val commons_validator = "commons-validator:commons-validator:1.6"

    const val colt = "colt:colt:1.2.0"

    // typesafe config
    const val typesafe_config = "com.typesafe:config:1.4.0"

    const val mu_klog = "io.github.microutils:kotlin-logging:${Versions.mu_klog}"

    const val slf4j_api = "org.slf4j:slf4j-api:${Versions.slf4j}"
    const val slf4j_simple = "org.slf4j:slf4j-simple:${Versions.slf4j}"
    const val slf4j_log4j12 = "org.slf4j:slf4j-log4j12:${Versions.slf4j}"
    const val jcl_over_slf4j = "org.slf4j:jcl-over-slf4j:${Versions.slf4j}"
    const val jul_to_slf4j = "org.slf4j:jul-to-slf4j:${Versions.slf4j}"
    const val log4j_over_slf4j = "org.slf4j:log4j-over-slf4j:${Versions.slf4j}"

    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
    const val logback_core = "ch.qos.logback:logback-core:${Versions.logback_core}"
    const val logstash = "net.logstash.logback:logstash-logback-encoder:${Versions.logstash}"

    const val log4j_bom = "org.apache.logging.log4j:log4j-bom:${Versions.log4j}"
    const val log4j_api = "org.apache.logging.log4j:log4j-api"
    const val log4j_core = "org.apache.logging.log4j:log4j-core"
    const val log4j_jcl = "org.apache.logging.log4j:log4j-jcl"
    const val log4j_jul = "org.apache.logging.log4j:log4j-jul"
    const val log4j_slf4j_impl = "org.apache.logging.log4j:log4j-slf4j-impl"
    const val log4j_web = "org.apache.logging.log4j:log4j-web"

    const val findbugs = "com.google.code.findbugs:jsr305:3.0.2"
    const val guava = "com.google.guava:guava:28.0-jre"
    const val joda_time = "joda-time:joda-time:2.10.6"
    const val joda_convert = "org.joda:joda-convert:2.2.1"

    const val fst = "de.ruedigermoeller:fst:2.57"
    const val kryo = "com.esotericsoftware:kryo:4.0.2"
    const val kryo_serializers = "de.javakaffee:kryo-serializers:0.45"

    // Spring Boot
    const val spring_boot_dependencies = "org.springframework.boot:spring-boot-dependencies:${Versions.spring_boot}"

    // Spring Cloud
    const val spring_cloud_dependencies = "org.springframework.cloud:spring-cloud-dependencies:${Versions.spring_cloud}"

    fun spring(name: String): String = "org.springframework:spring-$name"
    fun springBoot(name: String): String = "org.springframework.boot:spring-boot-$name"
    fun springBootStarter(name: String): String = "org.springframework.boot:spring-boot-starter-$name"
    fun springData(name: String): String = "org.springframework.data:spring-data-$name"

    // Jackson
    const val jackson_bom = "com.fasterxml.jackson:jackson-bom:${Versions.jackson}"
    const val jackson_annotations = "com.fasterxml.jackson.core:jackson-annotations"
    const val jackson_core = "com.fasterxml.jackson.core:jackson-core"
    const val jackson_databind = "com.fasterxml.jackson.core:jackson-databind"

    const val jackson_datatype_jsr310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310"
    const val jackson_datatype_jsr353 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr353"
    const val jackson_datatype_jdk8 = "com.fasterxml.jackson.datatype:jackson-datatype-jdk8"
    const val jackson_datatype_joda = "com.fasterxml.jackson.datatype:jackson-datatype-joda"
    const val jackson_datatype_guava = "com.fasterxml.jackson.datatype:jackson-datatype-guava"

    const val jackson_dataformat_avro = "com.fasterxml.jackson.dataformat:jackson-dataformat-avro"
    const val jackson_dataformat_protobuf = "com.fasterxml.jackson.dataformat:jackson-dataformat-protobuf"
    const val jackson_dataformat_csv = "com.fasterxml.jackson.dataformat:jackson-dataformat-csv"
    const val jackson_dataformat_properties = "com.fasterxml.jackson.dataformat:jackson-dataformat-properties"
    const val jackson_dataformat_yaml = "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml"

    const val jackson_module_kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin"
    const val jackson_module_paranamer = "com.fasterxml.jackson.module:jackson-module-paranamer"
    const val jackson_module_parameter_names = "com.fasterxml.jackson.module:jackson-module-parameter-names"
    const val jackson_module_afterburner = "com.fasterxml.jackson.module:jackson-module-afterburner"

    // jakarta.json-api 의 구현체
    const val javax_json = "org.glassfish:javax.json:1.1.4"

    const val gson = "com.google.code.gson:gson:2.8.6"
    const val gson_jodatime_serialisers = "com.fatboyindustrial.gson-jodatime-serialisers:gson-jodatime-serialisers:1.7.1"

    const val msgpack_core = "org.msgpack:msgpack-core:0.8.18"
    const val msgpack_jackson = "org.msgpack:jackson-dataformat-msgpack:0.8.18"

    const val protostuff_core = "io.protostuff:protostuff-core:1.6.0"
    const val protostuff_runtime = "io.protostuff:protostuff-runtime:1.6.0"
    const val protostuff_collectionschema = "io.protostuff:protostuff-collectionschema:1.6.0"

    // Compression
    const val snappy_java = "org.xerial.snappy:snappy-java:1.1.7.7"
    const val lz4_java = "org.lz4:lz4-java:1.7.1"
    const val zstd_jni = "com.github.luben:zstd-jni:1.4.5-6"
    const val xz = "org.tukaani:xz:1.8"

    // Cryptography
    const val jasypt = "org.jasypt:jasypt:1.9.3"
    const val bouncycastle_bcprov = "org.bouncycastle:bcprov-jdk15on:1.66"
    const val bouncycastle_bcpkix = "org.bouncycastle:bcpkix-jdk15on:1.66"

    // MVEL
    const val mvel2 = "org.mvel:mvel2:2.4.10.Final"

    // Reactor
    const val reactor_bom = "io.projectreactor:reactor-bom:${Versions.reactor_bom}"
    const val reactor_core = "io.projectreactor:reactor-core"
    const val reactor_adapter = "io.projectreactor.addons:reactor-adapter"
    const val reactor_extra = "io.projectreactor.addons:reactor-extra"
    const val reactor_netty = "io.projectreactor.netty:reactor-netty"
    const val reactor_test = "io.projectreactor:reactor-test"

    const val reactor_kotlin_extensions = "io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.1"

    // RxJava
    const val rxjava = "io.reactivex:rxjava:1.3.8"
    const val rxjava2 = "io.reactivex.rxjava2:rxjava:2.2.19"
    const val rxkotlin2 = "io.reactivex.rxjava2:rxkotlin:2.4.0"

    const val rxjava3 = "io.reactivex.rxjava3:rxjava:3.0.6"
    const val rxkotlin3 = "io.reactivex.rxjava3:rxkotlin:3.0.0"

    // Metrics
    const val metrics_bom = "io.dropwizard.metrics:metrics-bom:${Versions.metrics}"
    const val metrics_annotation = "io.dropwizard.metrics:metrics-annotation"
    const val metrics_core = "io.dropwizard.metrics:metrics-core"
    const val metrics_json = "io.dropwizard.metrics:metrics-json"
    const val metrics_jvm = "io.dropwizard.metrics:metrics-jvm"
    const val metrics_graphite = "io.dropwizard.metrics:metrics-graphite"
    const val metrics_healthchecks = "io.dropwizard.metrics:metrics-healthchecks"
    const val metrics_jcache = "io.dropwizard.metrics:metrics-jcache"
    const val metrics_jmx = "io.dropwizard.metrics:metrics-jmx"

    // Prometheus
    const val prometheus_simpleclient = "io.prometheus:simpleclient:${Versions.prometheus}"
    const val prometheus_simpleclient_common = "io.prometheus:simpleclient_common:${Versions.prometheus}"
    const val prometheus_simpleclient_dropwizard = "io.prometheus:simpleclient_dropwizard:${Versions.prometheus}"
    const val prometheus_simpleclient_httpserver = "io.prometheus:simpleclient_httpserver:${Versions.prometheus}"
    const val prometheus_simpleclient_pushgateway = "io.prometheus:simpleclient_pushgateway:${Versions.prometheus}"
    const val prometheus_simpleclient_spring_boot = "io.prometheus:simpleclient_spring_boot:${Versions.prometheus}"

    // Micrometer
    fun micrometer(artifact: String): String = "io.micrometer:micrometer-$artifact:${Versions.micrometer}"
    val micrometer_bom: String get() = micrometer("bom")
    val micrometer_core: String get() = micrometer("core")
    val micrometer_test: String get() = micrometer("test")
    val micrometer_registry_cloudwatch: String get() = micrometer("registry-cloudwatch")
    val micrometer_registry_elastic: String get() = micrometer("registry-elastic")
    val micrometer_registry_graphite: String get() = micrometer("registry-graphite")
    val micrometer_registry_new_relic: String get() = micrometer("registry-new-relic")
    val micrometer_registry_prometheus: String get() = micrometer("registry-prometheus")
    val micrometer_registry_jmx: String get() = micrometer("registry-jmx")

    const val latencyUtils = "org.latencyutils:LatencyUtils:2.0.3"
    const val hdrHistogram = "org.hdrhistogram:HdrHistogram:2.1.11"

    val mongodb_driver_reactivestreams: String get() = "org.mongodb:mongodb-driver-reactivestreams:1.11.0"

    // ArangoDB
    fun arangodb(module: String, version: String): String = "com.arangodb:arangodb-$module:$version"
    val arangodb_java_driver: String get() = arangodb("java-driver", "6.7.5")
    val arangodb_java_driver_async: String get() = arangodb("java-driver-async", "6.0.0")
    val arangodb_spring_data: String get() = arangodb("spring-data", "3.3.0")

    // Redis
    const val lettuce_core = "io.lettuce:lettuce-core:${Versions.lettuce}"

    fun redisson(module: String): String = "org.redisson:$module:${Versions.redisson}"
    val redisson get() = redisson("redisson")
    val redisson_spring_boot_starter get() = redisson("redisson-spring-boot-starter")
    val redisson_spring_data_21 get() = redisson("redisson-spring-data-21")
    val redisson_spring_data_22 get() = redisson("redisson-spring-data-22")
    val redisson_spring_data_23 get() = redisson("redisson-spring-data-23")
    val redisson_spring_data_24 get() = redisson("redisson-spring-data-24")

    // Memcached
    const val folsom = "com.spotify:folsom:1.6.1"
    const val spymemcached = "net.spy:spymemcached:2.12.3"

    // Kafka
    const val kafka_clients = "org.apache.kafka:kafka-clients:2.6.0"
    const val pulsar_client = "org.apache.pulsar:pulsar-client:2.4.0"

    // Hibernate
    fun hibernate(module: String): String = "org.hibernate:hibernate-$module:${Versions.hibernate}"
    val hibernate_core get() = hibernate("core")
    val hibernate_jcache get() = hibernate("jcache")
    val hibernate_testing get() = hibernate("testing")
    val hibernate_envers get() = hibernate("envers")

    const val javassist = "org.javassist:javassist:3.27.0-GA"

    // Validators
    const val hibernate_validator = "org.hibernate:hibernate-validator:${Versions.hibernate_validator}"
    const val hibernate_validator_annotation_processor = "org.hibernate:hibernate-validator-annotation-processor:${Versions.hibernate_validator}"

    // Expression
    const val javax_el = "org.glassfish:javax.el:3.0.1-b11"
    const val javax_el_api = "javax.el:javax.el-api:3.0.0"

    fun querydsl(module: String) = "com.querydsl:querydsl-$module:${Versions.querydsl}"
    val querydsl_apt get() = querydsl("apt")
    val querydsl_core get() = querydsl("core")
    val querydsl_jpa get() = querydsl("jpa")
    val querydsl_sql get() = querydsl("sql")

    const val hikaricp = "com.zaxxer:HikariCP:3.4.5"
    const val dbcp2 = "org.apache.commons:commons-dbcp2:2.7.0"
    const val tomcat_jdbc = "org.apache.tomcat:tomcat-jdbc:9.0.36"

    const val mysql_connector_java = "mysql:mysql-connector-java:8.0.25"
    const val mariadb_java_client = "org.mariadb.jdbc:mariadb-java-client:2.7.2"
    const val postgresql_driver = "org.postgresql:postgresql:42.2.10"
    const val oracle_ojdbc8 = "com.oracle.ojdbc:ojdbc8:19.3.0.0"

    const val h2 = "com.h2database:h2:1.4.197"
    const val hsqldb = "org.hsqldb:hsqldb:2.5.1"
    const val flyway_core = "org.flywaydb:flyway-core:7.5.0"

    const val exposed = "org.jetbrains.exposed:exposed:0.17.7"

    // UUID Generator
    const val java_uuid_generator = "com.fasterxml.uuid:java-uuid-generator:4.0.1"
    const val uuid_creator = "com.github.f4b6a3:uuid-creator:1.3.9"

    const val ehcache = "org.ehcache:ehcache:3.9.0"

    // CSV parsers
    const val univocity_parsers = "com.univocity:univocity-parsers:2.8.4"

    const val objenesis = "org.objenesis:objenesis:3.0.1"

    // junit 5.4+ 부터는 junit-jupiter 만 있으면 됩니다.
    const val junit_jupiter = "org.junit.jupiter:junit-jupiter:${Versions.junit_jupiter}"
    const val junit_jupiter_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit_jupiter}"
    const val junit_jupiter_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit_jupiter}"
    const val junit_jupiter_params = "org.junit.jupiter:junit-jupiter-params:${Versions.junit_jupiter}"
    const val junit_jupiter_migrationsupport = "org.junit.jupiter:junit-jupiter-migrationsupport:${Versions.junit_jupiter}"

    const val junit_platform_commons = "org.junit.platform:junit-platform-commons:${Versions.junit_platform}"
    const val junit_platform_engine = "org.junit.platform:junit-platform-engine:${Versions.junit_platform}"
    const val junit_platform_runner = "org.junit.platform:junit-platform-runner:${Versions.junit_platform}"
    const val junit_platform_launcher = "org.junit.platform:junit-platform-launcher:${Versions.junit_platform}"
    const val junit_platform_suite_api = "org.junit.platform:junit-platform-suite-api:${Versions.junit_platform}"

    const val kluent = "org.amshove.kluent:kluent:1.65"
    const val assertj_core = "org.assertj:assertj-core:${Versions.assertj_core}"

    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val springmockk = "com.ninja-squad:springmockk:2.0.3"

    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockito_junit_jupiter = "org.mockito:mockito-junit-jupiter:${Versions.mockito}"
    const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"
    const val jmock_junit5 = "org.jmock:jmock-junit5:2.12.0"

    const val javafaker = "com.github.javafaker:javafaker:1.0.2"
    const val random_beans = "io.github.benas:random-beans:3.9.0"

    const val mockserver_netty = "org.mock-server:mockserver-netty:5.10.0"
    const val mockserver_client_java = "org.mock-server:mockserver-client-java:5.10.0"

    const val system_rules = "com.github.stefanbirkner:system-rules:1.19.0"

    const val jmh_core = "org.openjdk.jmh:jmh-core:${Versions.jmh}"
    const val jmh_generator_annprocess = "org.openjdk.jmh:jmh-generator-annprocess:${Versions.jmh}"

    fun testcontainers(artifact: String): String =
        "org.testcontainers:$artifact:${Versions.testcontainers}"

    val testcontainers_bom: String get() = testcontainers("testcontainers-bom")
    val testcontainers: String get() = testcontainers("testcontainers")
    val testcontainers_junit_jupiter: String get() = testcontainers("junit-jupiter")
    val testcontainers_cassandra: String get() = testcontainers("cassandra")
    val testcontainers_elasticsearch: String get() = testcontainers("elasticsearch")
    val testcontainers_influxdb: String get() = testcontainers("influxdb")
    val testcontainers_dynalite: String get() = testcontainers("dynalite")
    val testcontainers_mariadb: String get() = testcontainers("mariadb")
    val testcontainers_mysql: String get() = testcontainers("mysql")
    val testcontainers_postgresql: String get() = testcontainers("postgresql")
    val testcontainers_oracle_xe: String get() = testcontainers("oracle-xe")
    val testcontainers_kafka: String get() = testcontainers("kafka")
    val testcontainers_pulsar: String get() = testcontainers("pulsar")
    val testcontainers_rabbitmq: String get() = testcontainers("rabbitmq")
    val testcontainers_vault: String get() = testcontainers("vault")

    // the Atlassian's LocalStack, 'a fully functional local AWS cloud stack'.
    val testcontainers_localstack: String get() = testcontainers("localstack")
    val testcontainers_mockserver: String get() = testcontainers("mockserver")

    // Springdoc OpenAPI
    const val springdoc_openapi_ui = "org.springdoc:springdoc-openapi-ui:${Versions.springdoc_openapi}"
    const val springdoc_openapi_webflux_ui = "org.springdoc:springdoc-openapi-webflux-ui:${Versions.springdoc_openapi}"
    const val springdoc_openapi_security = "org.springdoc:springdoc-openapi-security:${Versions.springdoc_openapi}"

    // Swagger
    const val swagger_annotations = "io.swagger:swagger-annotations:${Versions.swagger}"
    const val springfox_boot_starter = "io.springfox:springfox-boot-starter:${Versions.springfox_swagger}"

    // Problem for Spring
    const val problem_spring_web = "org.zalando:problem-spring-web:${Versions.problem}"
    const val problem_spring_webflux = "org.zalando:problem-spring-webflux:${Versions.problem}"

    const val logback_slack_appender = "com.github.maricn:logback-slack-appender:1.4.0"
    const val sentry_logback = "io.sentry:sentry-logback:${Versions.sentry_logback}"

    // ArchUnit - https://www.archunit.org/userguide/html/000_Index.html
    const val archunit = "com.tngtech.archunit:archunit:${Versions.archunit}"
    const val archunit_junit5 = "com.tngtech.archunit:archunit-junit5:${Versions.archunit}"

    // Detekt Plugins
    const val detekt_formatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Plugins.Versions.detekt}"

    // Auth0(OAuth2) Jwt
    const val auth0_jwt = "com.auth0:java-jwt:${Versions.auth0_jwt}"
}