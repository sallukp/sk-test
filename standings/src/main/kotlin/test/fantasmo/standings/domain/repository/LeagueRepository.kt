package test.fantasmo.standings.domain.repository

import test.fantasmo.standings.data.repository.LeagueRepositoryImpl
import java.io.File

interface LeagueRepository {

    fun createStandingsTable(file: File): String

    fun writeOutputTo(fileName: String, content: String)

    companion object {
        fun getInstance(): LeagueRepository = LeagueRepositoryImpl.getInstance()
    }

}