package test.fantasmo.standings

import kotlinx.cli.ArgParser
import test.fantasmo.standings.App.appName
import test.fantasmo.standings.App.version
import test.fantasmo.standings.feature.UserIO

fun main(args: Array<String>) {
    val parser = ArgParser("$appName:: $version")
    val userIO = UserIO(parser, args)
    userIO.process()
}

object App {
    const val appName = "Game Standings"
    const val version = "1.0.0"
}