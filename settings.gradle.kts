pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        // Optional: declare plugin versions here if not using TOML
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://repo.sendbird.com") }
    }
    versionCatalogs {

    }
}

rootProject.name = "PRO.EEEEEEEEEEEEET"
include(":app")