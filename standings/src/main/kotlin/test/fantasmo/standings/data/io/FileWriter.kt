package test.fantasmo.standings.data.io

import test.fantasmo.standings.domain.interfaces.IWriter
import test.fantasmo.standings.domain.model.Team
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths

class FileWriter: IWriter {

    var writer: BufferedWriter? = null

    override fun open(fileName: String) {
        fileName.split("/").also {
            it.subList(0, it.size - 1).joinToString("/").also { path ->
                if (path.isNotEmpty() && path != "/")
                    Files.createDirectories(Paths.get(path))
            }
        }
        writer = BufferedWriter(FileWriter(fileName))
    }

    override fun close() {
        writer?.close()
    }

    override fun writeFile(content: String) {
        writer?.write(content)
    }
}