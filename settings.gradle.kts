pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
        mavenCentral()

        // for Avro plugin ( https://github.com/davidmc24/gradle-avro-plugin )
        maven(url = "https://dl.bintray.com/gradle/gradle-plugins")

        // kotlinx benchamrk
        maven(url = "https://dl.bintray.com/kotlin/kotlinx")
    }
}

rootProject.name = "strada"

include("api")
