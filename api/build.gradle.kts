plugins {
    idea
    war
    id(Plugins.spring_boot)
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")

    // test 코드를 다른 모듈에서 참조할 수 있도록 해줍니다
    // see: https://github.com/hauner/gradle-plugins/tree/master/jartest
    id(Plugins.jarTest) version Plugins.Versions.jarTest
}

idea {
    module {
        val kaptMain = file("$buildDir/generated/source/kapt/main")
        sourceDirs.plus(kaptMain)
        generatedSourceDirs.plus(kaptMain)

        val kaptTest = file("$buildDir/generated/source/kapt/test")
        testSourceDirs.plus(kaptTest)
    }
}

kapt {
    val compileJava: JavaCompile by tasks
    javacOptions {
        option("--module-path", compileJava.classpath.asPath)
    }
}

configurations.all {
    if (name.contains("kapt") || name.contains("proto")) {
        attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
    }
}

configurations.forEach {
    if (it.name.toLowerCase().contains("runtimeclasspath")) {
        it.attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
    }
}

dependencies {

    api("org.springframework.boot:spring-boot-autoconfigure")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    kaptTest("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.data:spring-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

    testApi("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "junit-vintage-engine")
    }

    api(Libs.mu_klog)

    // jackson
    implementation(Libs.jackson_annotations)
    implementation(Libs.jackson_datatype_jdk8)
    implementation(Libs.jackson_module_kotlin)

    // Hibernate
    api(Libs.hibernate_core)
    testImplementation(Libs.hibernate_testing)

    // Hibernate enversx
    implementation(Libs.hibernate_envers)
    implementation("org.springframework.data:spring-data-envers")

    // NOTE: Java 9+ 환경에서 kapt가 제대로 동작하려면 jakarta.annotation-api 를 참조해야 합니다.
    api(Libs.jakarta_annotation_api)

    // Jakarta Api
    api(Libs.jakarta_persistence_api)
    api(Libs.jakarta_inject_api)
    api(Libs.jakarta_activation_api)

    api(Libs.jakarta_el_api)
    api(Libs.jakarta_el)

    api(Libs.jakarta_validation_api)
    api(Libs.hibernate_validator)

    // QueryDSL
    api(Libs.querydsl_jpa)
    kapt(Libs.querydsl_apt + ":jpa")
    kaptTest(Libs.querydsl_apt + ":jpa")

    implementation(Libs.hikaricp)
    implementation(Libs.mariadb_java_client)
    optional(Libs.mysql_connector_java)

    testImplementation(Libs.h2)
    testImplementation(Libs.testcontainers_mysql)

    // 참고: https://dzone.com/articles/spring-boot-unit-test-your-project-architecture-wi
    testImplementation(Libs.archunit_junit5)

    /*
    * webapi
    */
    // Auth0(OAuth2) Jwt
    implementation(Libs.auth0_jwt)

    // Metrics
    implementation(Libs.micrometer_core)
    implementation(Libs.micrometer_registry_prometheus)
    implementation(Libs.prometheus_simpleclient_spring_boot)

    // Springdoc OpenApi
    implementation(Libs.springdoc_openapi_ui)

    // Problem
    implementation(Libs.problem_spring_web)
}
