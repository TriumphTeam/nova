plugins {
    id("nova.base")
    id("nova.library")
}

dependencies {
    api(projects.nova)
    api(kotlin("stdlib"))
}
