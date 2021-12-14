import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()


fun <T> readInput(name: String, map : (String) -> T) =
    File("src", "$name.txt").readLines().map(map)

fun toIntArray(line : String) : IntArray {
     return line.split(Regex("[\\s,;]+"))
        .filter { it.isNotEmpty()  }
        .map{ it.toInt()}
        .toIntArray()
}

fun <T> splitLine(line : String, mapping : (String) -> T) : List<T> {
    return line.split(Regex("[\\s,;]+"))
        .filter { it.isNotEmpty()  }
        .map(mapping)
        .toList()
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String =
    BigInteger(1, MessageDigest.getInstance("MD5")
            .digest(toByteArray()))
            .toString(16)