package test.fantasmo.standings.domain.interfaces

import test.fantasmo.standings.domain.model.Team
import java.io.File

interface IWriter {

    fun open(fileName: String)

    fun close()

    fun writeFile(content: String)
}