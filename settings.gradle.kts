import dev.triumphteam.root.includeProject

dependencyResolutionManagement {
    includeBuild("build-logic")
    repositories.gradlePluginPortal()
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.triumphteam.dev/releases")
    }
}

val base = "nova"
rootProject.name = "$base-project"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

plugins {
    id("dev.triumphteam.root.settings") version "0.0.15"
}

includeProject("core") {
    name = base
}
includeProject("kotlin") {
    name = "$base-kotlin"
}
