import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import ext.configurePlatform

plugins {
    id("nova.base")
    id("nova.library")
    id("org.jetbrains.dokka")
}

dependencies {
    api(projects.nova)
    api(kotlin("stdlib"))
}

mavenPublishing {
    configurePlatform(
        platform = KotlinJvm(
            javadocJar = JavadocJar.Dokka("dokkaHtml"),
            sourcesJar = true,
        )
    )
}
