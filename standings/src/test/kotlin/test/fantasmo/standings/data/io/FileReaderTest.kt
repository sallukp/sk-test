package test.fantasmo.standings.data.io

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import kotlin.test.assertEquals


class FileReaderTest {

    val reader = mock<BufferedReader>()
    val fileReader = FileReader()

    @Before
    fun setUp() {
        fileReader.reader = reader
    }

    @Test
    fun testClose() {
        fileReader.close()
        verify(reader).close()
    }

    @Test
    fun testReadLineFirstTeamWins() {
        whenever(reader.readLine()).thenReturn("Fantastics 1, FC Super 0")
        val teams = fileReader.readLine()!!
        assertEquals("Fantastics", teams[0].name)
        assertEquals(1, teams[0].goals)
        assertEquals(3, teams[0].point)
        assertEquals("FC Super", teams[1].name)
        assertEquals(0, teams[1].goals)
        assertEquals(0, teams[1].point)
    }

    @Test
    fun testReadLineSecondTeamWins() {
        whenever(reader.readLine()).thenReturn("Crazy Ones 1, FC Super 3")
        val teams = fileReader.readLine()!!
        assertEquals("Crazy Ones", teams[0].name)
        assertEquals(1, teams[0].goals)
        assertEquals(0, teams[0].point)
        assertEquals("FC Super", teams[1].name)
        assertEquals(3, teams[1].goals)
        assertEquals(3, teams[1].point)
    }
    @Test
    fun testReadLineDraw() {
        whenever(reader.readLine()).thenReturn("Crazy Ones 3, Rebels 3")
        val teams = fileReader.readLine()!!
        assertEquals("Crazy Ones", teams[0].name)
        assertEquals(3, teams[0].goals)
        assertEquals(1, teams[0].point)
        assertEquals("Rebels", teams[1].name)
        assertEquals(3, teams[1].goals)
        assertEquals(1, teams[1].point)
    }
}