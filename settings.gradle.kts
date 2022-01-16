pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()

        // for Schema Registry plugin ( https://github.com/ImFlog/schema-registry-plugin )
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "http://packages.confluent.io/maven/")
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "strada"

include("api")
