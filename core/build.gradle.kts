import com.vanniktech.maven.publish.JavaLibrary
import com.vanniktech.maven.publish.JavadocJar
import ext.configurePlatform

plugins {
    id("nova.base")
    id("nova.library")
}

dependencies {
    api(libs.guava)
}

mavenPublishing {
    configurePlatform(
        platform = JavaLibrary(
            javadocJar = JavadocJar.Javadoc(),
            sourcesJar = true,
        )
    )
}
