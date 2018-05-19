package org.abhijitsarkar.kalgs4.test

import java.io.InputStream
import java.nio.file.Path
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

/**
 * @author Abhijit Sarkar
 */
object ZipUtil {
    fun <T> transformEntry(archive: Path, condition: (ZipEntry) -> Boolean, transformer: (InputStream) -> T): T {
        return ZipFile(archive.toFile()).use { zipFile ->
            zipFile.entries()
                    .asSequence()
                    .find(condition)
                    ?.let { zipFile.getInputStream(it).use(transformer) }
                    ?: throw IllegalArgumentException("No file found that meets the given condition")
        }
    }

    fun nameMatches(filename: String): (ZipEntry) -> Boolean {
        return { entry -> !entry.isDirectory && entry.name.endsWith(filename) }
    }
}