package io.github.poeschl.adventofcode2018

import org.assertj.core.util.Files
import java.io.File

internal fun createTmpFileWithLines(lines: Array<String>): File {
    val tmpFile = Files.newTemporaryFile()
    val fileWriter = tmpFile.printWriter()

    lines.forEach {
        fileWriter.println(it)
    }
    fileWriter.close()

    return tmpFile
}
