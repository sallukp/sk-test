package test.fantasmo.standings.data.io

import test.fantasmo.standings.domain.interfaces.IReader
import test.fantasmo.standings.domain.model.Team
import java.io.*

class FileReader: IReader {

    var reader: BufferedReader? = null

    override fun open(file: File) {
        reader = BufferedReader(FileReader(file))
    }

    override fun close() {
        reader?.let { it.close() }
    }

    override fun readLine(): Array<Team>? {
        var line = reader!!.readLine()
        line?.let {
            val teams = line.split(", ")
            val team1 = parseTeamAndGoal(teams[0])
            val team2 = parseTeamAndGoal(teams[1])
            when (team1.goals.compareTo(team2.goals)) {
                0 -> {
                    team1.point = 1
                    team2.point = 1
                }
                1 -> team1.point = 3
                -1 -> team2.point = 3
            }
            return arrayOf(team1, team2)
        } ?: return null
    }

    private fun parseTeamAndGoal(scoreStr: String): Team {
        val lastSpaceIndex = scoreStr.lastIndexOf(" ")
        val team = Team(name = scoreStr.substring(0, lastSpaceIndex))
        team.goals = scoreStr.substring(lastSpaceIndex + 1).toInt()
        return team
    }
}