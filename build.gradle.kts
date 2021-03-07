allprojects {
    repositories {
        jcenter()
    }
}



subprojects {
    tasks.register<Copy>("packageDistribution") {
        dependsOn("jar")
        from("${project.projectDir}/build/libs/${project.name}-${project.version}.jar") {
            into("lib")
        }
        into("${project.rootDir}/dist")
        doLast {
            val script = "#/usr/bin\njava -jar lib/${project.name}-${project.version}.jar $@"
            file("${project.rootDir}/dist/standings-cli").also {
                it.writeText(script)
                it.setExecutable(true)
            }
        }
    }
}