package test.fantasmo.standings.data.repository

import test.fantasmo.standings.data.io.FileReader
import test.fantasmo.standings.data.io.FileWriter
import test.fantasmo.standings.domain.interfaces.IReader
import test.fantasmo.standings.domain.interfaces.IWriter
import test.fantasmo.standings.domain.model.Team
import test.fantasmo.standings.domain.repository.LeagueRepository
import java.io.File

class LeagueRepositoryImpl(val reader: IReader, val writer: IWriter): LeagueRepository {

    override fun createStandingsTable(file: File): String {
        reader.open(file)
        val pointsTable = mutableMapOf<String, Team>()
        var row = reader.readLine()
        while (row != null) {
            for (i in 0..1) {
                pointsTable[row[i].name]?.let {
                    it.goals += row!![i].goals
                    it.point += row!![i].point
                } ?: run {
                    pointsTable[row!![i].name] = row!![i]
                }
            }
            row = reader.readLine()
        }
        val pointTable = pointsTable.values.sortedWith(compareByDescending(Team::point)
            .thenBy{it.name.toLowerCase()})

        // prepares positions in the points table
        var position = 1
        for (i in pointTable.indices) {
            if (i == 0 || pointTable[i - 1].point != pointTable[i].point) {
                position = i + 1
            }
            pointTable[i].position = position
        }
        reader.close()
        return pointTable.joinToString("\n", transform = { it.toPointsTableEntry() })
    }

    override fun writeOutputTo(fileName: String, content: String) {
        writer.open(fileName)
        writer.writeFile(content)
        writer.close()
    }

    companion object {
        fun getInstance() = LeagueRepositoryImpl(FileReader(), FileWriter())
    }

}