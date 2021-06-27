plugins {
    id(Plugins.spring_boot)
    id(Plugins.kotlin_noarg)

    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")

    // test 코드를 다른 모듈에서 참조할 수 있도록 해줍니다
    // see: https://github.com/hauner/gradle-plugins/tree/master/jartest
    id(Plugins.jarTest) version Plugins.Versions.jarTest
}

noArg {
    // @Entity가 붙은 클래스에 한해서만 noArg 플러그인을 적용합니다.
    annotation("javax.persistence.Entity")
}

tasks.getByName<Jar>("jar") {
    // Spring Boot 2.5.0 이하에서는 bootJar를 통해 jar가 생성되기 때문에 자동으로 disable 상태이지만
    // 이후부터는 설정하지 않으면 2개의 Job을 통해 2개의 jar가 생성됩니다.
    enabled = false
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

    api(Libs.springBoot("autoconfigure"))
    kapt(Libs.springBoot("configuration-processor"))
    kaptTest(Libs.springBoot("configuration-processor"))

    api(Libs.springBootStarter("data-jpa"))
    api(Libs.springBootStarter("validation"))
    api(Libs.springBootStarter("actuator"))
    api(Libs.springBootStarter("aop"))
    api(Libs.springBootStarter("oauth2-resource-server"))
    api(Libs.springBootStarter("web"))
    api(Libs.springBootStarter("validation"))

    api(Libs.springData("jpa"))

    implementation("org.springframework.data:spring-data-jpa")

    testApi(Libs.springBootStarter("test")) {
        exclude(module = "junit")
        exclude(module = "junit-vintage-engine")
    }

    api(Libs.mu_klog)
    api(Libs.logstash)

    // jackson
    implementation(Libs.jackson_annotations)
    implementation(Libs.jackson_datatype_jdk8)
    implementation(Libs.jackson_module_kotlin)

    // Reactor
    implementation(Libs.reactor_adapter)
    implementation(Libs.reactor_extra)
    implementation(Libs.reactor_kotlin_extensions)
    testImplementation(Libs.reactor_test)

    // Coroutines
    implementation(Libs.kotlinx_coroutines_jdk8)
    implementation(Libs.kotlinx_coroutines_reactor)

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

    // Test
    testImplementation(Libs.testcontainers)
    testImplementation(Libs.testcontainers_mysql)
    testImplementation(Libs.archunit_junit5)
    testImplementation(Libs.reactor_test)
    testImplementation(Libs.springBootStarter("webflux"))

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
