package ext

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.Platform

fun MavenPublishBaseExtension.configurePlatform(platform: Platform) {
    configureDefaults()
    configure(platform)
}

fun MavenPublishBaseExtension.configureDefaults() {
    publishToMavenCentral()
    signAllPublications()

    pom {
        name.set("nova")
        description.set("State framework.")
        url.set("https://github.com/TriumphTeam/triumph-cmds")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://www.opensource.org/licenses/mit-license.php")
            }
        }

        developers {
            developer {
                id.set("Matt")
                name.set("Mateus Moreira")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/TriumphTeam/nova.git")
            developerConnection.set("scm:git:ssh://github.com:TriumphTeam/nova.git")
            url.set("https://github.com/TriumphTeam/nova")
        }
    }
}
