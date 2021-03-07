package test.fantasmo.standings.domain.interfaces

import test.fantasmo.standings.domain.model.Team
import java.io.File

interface IReader {

    fun open(file: File)

    fun close()

    fun readLine(): Array<Team>?

}