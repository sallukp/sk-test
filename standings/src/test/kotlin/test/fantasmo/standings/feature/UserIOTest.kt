package test.fantasmo.standings.feature

import com.nhaarman.mockitokotlin2.*
import kotlinx.cli.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import test.fantasmo.standings.App
import test.fantasmo.standings.App.version
import test.fantasmo.standings.domain.repository.LeagueRepository
import test.fantasmo.standings.feature.UserIO.Companion.TEXT_INFO_COLOR
import test.fantasmo.standings.feature.UserIO.Companion.TEXT_ERROR_COLOR
import test.fantasmo.standings.feature.UserIO.Companion.TEXT_OUTPUT_COLOR
import test.fantasmo.standings.feature.UserIO.Companion.TEXT_RESET
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.assertEquals


class UserIOTest {

    val argParser = ArgParser("Test")


    @Test
    fun testConstructor() {
        val input = "input.txt"
        val output = "output.txt"
        val argArray = arrayOf("-V", "-i", input, "-o", output)
        val userIO = UserIO(argParser, argArray)
        assert(userIO.version)
        assertEquals(userIO.input, input)
        assertEquals(userIO.output, output)
    }

    @Test
    fun testIsValidFile() {
        val argArray: Array<String> = Array(1) {"-V"}
        val userIO = UserIO(argParser, argArray)
        val file: File = mock()
        whenever(file.exists()).thenReturn(true)
        assert(userIO.isValidFile(file))
        verify(file).exists()
        whenever(file.exists()).thenReturn(false)
        assert(!userIO.isValidFile(file))
        verify(file, times(2)).exists()
    }

    @Test
    fun testPrintInfo() {
        val argArray: Array<String> = Array(1) {"-V"}
        val userIO = UserIO(argParser, argArray)
        val info = "Test info"
        val outputStreamCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStreamCaptor))
        userIO.printInfo(info)
        assertEquals("$TEXT_INFO_COLOR$info$TEXT_RESET", outputStreamCaptor.toString().trim());
    }


    @Test
    fun testPrintOutput() {
        val argArray: Array<String> = Array(1) {"-V"}
        val userIO = UserIO(argParser, argArray)
        val output = "Test output"
        val outputStreamCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStreamCaptor))
        userIO.printOutput(output)
        assertEquals("$TEXT_OUTPUT_COLOR$output$TEXT_RESET", outputStreamCaptor.toString().trim());
    }

    @Test
    fun testPrintError() {
        val argArray: Array<String> = Array(1) {"-V"}
        val userIO = UserIO(argParser, argArray)
        val info = "Test error"
        val outputStreamCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStreamCaptor))
        userIO.printError(info)
        assertEquals("$TEXT_ERROR_COLOR$info$TEXT_RESET", outputStreamCaptor.toString().trim());
    }

    @Test
    fun testVersionCheck() {
        val argArray = arrayOf("-V")
        val userIO = UserIO(argParser, argArray)
        val outputStreamCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStreamCaptor))
        userIO.process()
        assertEquals("$TEXT_INFO_COLOR${App.version}$TEXT_RESET", outputStreamCaptor.toString().trim());
    }


    @Test
    fun testInputOnlyError() {
        val argArray = arrayOf("-i", "../input.txt")
        val userIO = UserIO(argParser, argArray)
        val outputStreamCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStreamCaptor))
        userIO.process()
        val fileNotFound = "Could not find input file: '${argArray[1]}'"
        assertEquals("$TEXT_ERROR_COLOR$fileNotFound$TEXT_RESET", outputStreamCaptor.toString().trim());
    }

    @Test
    fun testInputOnlySuccess() {
        val argArray = arrayOf("-i", "../sample-input.txt")
        val userIO = UserIO(argParser, argArray)
        var leagueRepository = mock<LeagueRepository>()
        val output = "1. Fantastics, 6 pts"
        whenever(leagueRepository.createStandingsTable(any())).thenReturn(output)
        userIO.leagueRepository = leagueRepository
        val outputStreamCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStreamCaptor))
        userIO.process()
        val fileFound = "Processing input file: '${argArray[1]}'..."
        assertEquals("$TEXT_INFO_COLOR$fileFound$TEXT_RESET\n" +
                "$TEXT_OUTPUT_COLOR$output$TEXT_RESET", outputStreamCaptor.toString().trim());
    }

    @Test
    fun testInputAndOutputSuccess() {
        val argArray = arrayOf("-i", "../sample-input.txt", "-o", "output.txt")
        val userIO = UserIO(argParser, argArray)
        var leagueRepository = mock<LeagueRepository>()
        val output = "1. Fantastics, 6 pts"
        whenever(leagueRepository.createStandingsTable(any())).thenReturn(output)
        userIO.leagueRepository = leagueRepository
        val outputStreamCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStreamCaptor))
        userIO.process()
        val fileFound = "Processing input file: '${argArray[1]}'..."
        val outputFileInfo = "Output will be saved in '${argArray[3]}'"
        val outputSaved = "Output is saved in '${argArray[3]}'"
        assertEquals("$TEXT_INFO_COLOR$fileFound$TEXT_RESET\n" +
                "$TEXT_INFO_COLOR$outputFileInfo$TEXT_RESET\n" +
                "$TEXT_OUTPUT_COLOR$outputSaved$TEXT_RESET", outputStreamCaptor.toString().trim());
    }
}