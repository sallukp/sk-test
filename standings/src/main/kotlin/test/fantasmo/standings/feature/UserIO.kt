package test.fantasmo.standings.feature

import kotlinx.cli.*
import test.fantasmo.standings.App
import test.fantasmo.standings.domain.repository.LeagueRepository
import java.io.File

class UserIO private constructor() {

    var version: Boolean = false
    var input: String? = null
    var output: String? = null
    var leagueRepository = LeagueRepository.getInstance()

    constructor(parser: ArgParser, args: Array<String>): this() {
        val _version by parser.option(ArgType.Boolean, shortName = "V", description = "Version")
            .default(false)
        val _input by parser.option(ArgType.String, shortName = "i", description = "Relative or fully qualified path " +
                "of input file")
        val _output by parser.option(ArgType.String, shortName = "o", description = "Relative or fully qualified path " +
                "of output file")

        // Add all input to parser
        parser.parse(args)
        version = _version
        input = _input
        output = _output
    }

    companion object {
        const val TEXT_RESET = "\u001B[0m"
        const val TEXT_ERROR_COLOR = "\u001B[31m"
        const val TEXT_INFO_COLOR = "\u001B[34m"
        const val TEXT_OUTPUT_COLOR = "\u001B[32m"
    }

    fun process() {
        if(version) printInfo(App.version)
        if (shouldProceed()) {
            proceed()
        }
    }



    fun isValidFile(file: File): Boolean {
        return file.exists()
    }

    fun shouldProceed(): Boolean {
        input?.let {
            File(it).also { file ->
                if (isValidFile(file)) {
                    printInfo("Processing input file: '$input'...")
                    output?.let { printInfo("Output will be saved in '$output'") }
                    return true
                } else {
                    printError("Could not find input file: '$input'")
                    return false
                }
            }
        }
        return false
    }

    fun proceed() {
        val standings = leagueRepository.createStandingsTable(File(input))
        output?.let {
            leagueRepository.writeOutputTo(it, standings)
            printOutput("Output is saved in '$it'")
        } ?: run {
            printOutput(standings)
        }
    }

    fun printInfo(info: String) {
        println("$TEXT_INFO_COLOR$info$TEXT_RESET")
    }

    fun printError(error: String) {
        println("$TEXT_ERROR_COLOR$error$TEXT_RESET")
    }


    fun printOutput(info: String) {
        println("$TEXT_OUTPUT_COLOR$info$TEXT_RESET")
    }
}