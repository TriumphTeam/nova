import dev.triumphteam.root.configure.PublishConfigure

plugins {
    `maven-publish`
    signing
    id("dev.triumphteam.root")
}

root {
    configurePublishing {
        configure {

            from(components["java"])

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

        snapshotsRepo(PublishConfigure.TRIUMPH_SNAPSHOTS) {
            username = property("triumph.repo.user")
            password = property("triumph.repo.token")
        }

        releasesRepo(PublishConfigure.CENTRAL) {
            username = property("central.repo.user")
            password = property("central.repo.password")
        }

        signing {
            sign(publishing.publications["maven"])
        }
    }
}
