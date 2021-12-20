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

enum class Color(val fg : String,  val bg : String) {
    Black("30", "40"),
    BlackLT("30;1", "40;1"),
    Red("31", "41"),
    RedLT("31;1", "41;1"),
    Green("32", "42"),
    GreenLT("32;1", "42;1"),
    Yellow("33", "43"),
    YellowLT("33;1", "43;1"),
    Blue("34", "44"),
    BlueLT("34;1", "44;1"),
    Magenta("35", "45"),
    MagentaLT("35;1", "45;1"),
    Cyan("36", "46"),
    CyanLT("36;1", "46;1"),
    White("37", "47"),
    WhiteLT("37;1", "47;1");

    fun fg(text : String) : String { return "\u001B[${fg}m$text\u001B[0m" }
    fun fg(value : Int) : String { return fg(value.toString()) }
}

fun color(text : String, fg : Color, bg : Color) : String {
    return "\u001B[${fg.fg}m\u001B[${bg.bg}m$text\u001B[0m"
}
fun color(value : Int, fg : Color, bg : Color) : String {
    return color(value.toString(), fg, bg)
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String =
    BigInteger(1, MessageDigest.getInstance("MD5")
            .digest(toByteArray()))
            .toString(16)