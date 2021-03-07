package test.fantasmo.standings.domain.model

import org.junit.Test
import kotlin.test.assertEquals


class TeamTest {

    //  1. Fantastics, 6 pts
    @Test
    fun testToPointsTableEntry() {
        val team = Team("Fantastics", 6)
        team.position = 1
        assertEquals("1. Fantastics, 6 pts", team.toPointsTableEntry())
        team.point = 1
        assertEquals("1. Fantastics, 1 pt", team.toPointsTableEntry())
        team.point = 0
        assertEquals("1. Fantastics, 0 pts", team.toPointsTableEntry())
    }
}