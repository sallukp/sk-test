package test.fantasmo.standings.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import test.fantasmo.standings.domain.interfaces.IReader
import test.fantasmo.standings.domain.interfaces.IWriter
import test.fantasmo.standings.domain.model.Team
import java.io.File
import kotlin.test.assertEquals


internal class LeagueRepositoryImplTest {

    val fileReader = mock<IReader>()
    val fileWriter = mock<IWriter>()
    val repository = LeagueRepositoryImpl(fileReader, fileWriter)

    /**
     * Input:
     *      Crazy Ones 3, Rebels 3
     *      Fantastics 1, FC Super 0
     *      Crazy Ones 1, FC Super 1
     *      Fantastics 3, Rebels 1
     *      Crazy Ones 4, Misfits 0
     * Output:
     *      1. Fantastics, 6 pts
     *      2. Crazy Ones, 5 pts
     *      3. FC Super, 1 pt
     *      3. Rebels, 1 pt
     *      5. Misfits, 0 pts
     */
    @Test
    fun test1CreateStandingsTable() {
        val set1 = arrayOf(Team("Crazy Ones", 1, 3),
            Team("Rebels", 1, 3))
        val set2 = arrayOf(Team("Fantastics", 3, 1),
            Team("FC Super", 0, 0))
        val set3 = arrayOf(Team("Crazy Ones", 1, 1),
            Team("FC Super", 1, 1))
        val set4 = arrayOf(Team("Fantastics", 3, 3),
            Team("Rebels", 0, 1))
        val set5 = arrayOf(Team("Crazy Ones", 3, 4),
            Team("Misfits", 0, 0))
        whenever(fileReader.readLine()).thenReturn(set1, set2, set3, set4, set5, null)
        val output = "1. Fantastics, 6 pts\n" +
                "2. Crazy Ones, 5 pts\n" +
                "3. FC Super, 1 pt\n" +
                "3. Rebels, 1 pt\n" +
                "5. Misfits, 0 pts"
        val file = File("input.txt")
        val actualOutput = repository.createStandingsTable(file)
        verify(fileReader).open(file)
        verify(fileReader).close()
        assertEquals(output, actualOutput)
    }

    @Test
    fun test2CreateStandingsTable() {
        val set1 = arrayOf(Team("Crazy Ones", 1, 3),
            Team("Rebels", 1, 3))
        val set2 = arrayOf(Team("Fantastics", 1, 1),
            Team("FC Super", 1, 1))
        val set3 = arrayOf(Team("Crazy Ones", 1, 1),
            Team("FC Super", 1, 1))
        val set4 = arrayOf(Team("Fantastics", 1, 3),
            Team("Rebels", 1, 3))
        val set5 = arrayOf(Team("Crazy Ones", 1, 4),
            Team("Misfits", 1, 4))
        whenever(fileReader.readLine()).thenReturn(set1, set2, set3, set4, set5, null)
        val output = "1. Crazy Ones, 3 pts\n" +
                "2. Fantastics, 2 pts\n" +
                "2. FC Super, 2 pts\n" +
                "2. Rebels, 2 pts\n" +
                "5. Misfits, 1 pt"
        val file = File("input.txt")
        val actualOutput = repository.createStandingsTable(file)
        verify(fileReader).open(file)
        verify(fileReader).close()
        assertEquals(output, actualOutput)
    }

    @Test
    fun test3CreateStandingsTable() {
        val set1 = arrayOf(Team("Crazy Ones", 1, 3),
            Team("Rebels", 1, 3))
        val set2 = arrayOf(Team("Fantastics", 1, 1),
            Team("FC Super", 1, 1))
        val set3 = arrayOf(Team("Crazy Ones", 1, 1),
            Team("FC Super", 1, 1))
        val set4 = arrayOf(Team("Fantastics", 1, 3),
            Team("Rebels", 1, 3))
        whenever(fileReader.readLine()).thenReturn(set1, set2, set3, set4, null)
        val output = "1. Crazy Ones, 2 pts\n" +
                "1. Fantastics, 2 pts\n" +
                "1. FC Super, 2 pts\n" +
                "1. Rebels, 2 pts"
        val file = File("input.txt")
        val actualOutput = repository.createStandingsTable(file)
        verify(fileReader).open(file)
        verify(fileReader).close()
        assertEquals(output, actualOutput)
    }

    @Test
    fun testWriteOutputTo() {
        repository.writeOutputTo("output.txt", "test output")
        verify(fileWriter).open("output.txt")
        verify(fileWriter).writeFile("test output")
        verify(fileWriter).close()
    }
}