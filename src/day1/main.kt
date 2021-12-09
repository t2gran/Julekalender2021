package day1

import readInput


/**
 * https://adventofcode.com/
 */
fun main() {
    //val depths = inputEx()
    val depths = readInput("day1/input", String::toInt)
    println("Part 1    : " + part1(depths))
    println("Part 2 A  : " + part2a(depths))
    println("Part 2 B  : " + part2b(depths))
    println("Part 2 JB : " + part2JetBrains(depths))
}

fun part1(depths : List<Int>) : Int {
    return depths.windowed(2).count { it[0] < it[1] }
}

fun part2a(depths : List<Int>) : Int {
    return depths.windowed(4).count {
            it.subList(0, 3).sum() < it.subList(1, 4).sum()
    }
}

fun part2b(depths : List<Int>) : Int {
    return depths.windowed(4).count {
        it.take(3).sum() < it.takeLast(3).sum()
    }
}

/** https://blog.jetbrains.com/kotlin/2021/12/advent-of-code-2021-in-kotlin-day-1/ */
fun part2JetBrains(depths : List<Int>) : Int {
    return depths.windowed(4).count { it[0] < it[3] }
}

fun inputEx() : List<Int> {
    return listOf(199, 200, 208 ,210 ,200 ,207 ,240 ,269 ,260 ,263)
}
