package day8

import readInput
import java.util.*

fun main() {
    val filename = "day8/input-my"
    println("Part 1");
    val sum = readInput(filename)
        .map { it.split('|').last().split(' ') }
        .flatten()
        .count { it.length in setOf(2,3,4,7) }
    println("Sum $sum");

    println("\nPart 2");
    val sum2 = readInput(filename)
        .map { decodeLine(it) }
        .sum()
    println("Sum $sum2");
}

enum class Signal { a, b, c, d, e, f, g }

fun decodeLine(line: String) : Int {
    val input = line.split(" | ")
    val numbers = decodeNumbers(input.first())

    return input.last().split(' ').fold(0) {
            acc,s -> 10*acc + numbers[toSet(s)]!!
    }
}

fun decodeNumbers(line: String) : Map<Set<Signal>, Int> {
    val list  =  line.split(' ')
        .map { toSet(it) }
        .toList()

    val one = list.single { it.size == 2 }
    val four = list.single { it.size == 4 }
    val seven = list.single { it.size == 3 }
    val eight = list.single { it.size == 7 }

    // Find signal e, f and c to decode the rest of the numbers
    val signalByCount = Signal.values().associateBy { list.count { s -> s.contains(it) } }
    val e = signalByCount[4]!!
    val f = signalByCount[9]!!
    val c = (one - f).single()!!

    // 5 segments
    val list5 = list.filter { it.size == 5 }.toList()
    val two = list5.single { it.contains(e) }
    val three = list5.single { it.contains(c, f) }
    val five = list5.single { !it.contains(c) }

    // 6 segments
    val list6 = list.filter { it.size == 6 }.toList()
    val zero = list6.single { it.contains(c, e) }
    val six = list6.single  { !it.contains(c) }
    val nine = list6.single { !it.contains(e) }

    return mapOf(
        zero to 0, one to 1, two to 2, three to 3, four to 4,
        five to 5, six to 6, seven to 7, eight to 8, nine to 9,
    )
}

fun toSet(text : String) : EnumSet<Signal> {
    return EnumSet.copyOf(text.asIterable().map { Signal.valueOf(it.toString()) }.toList())
}

fun EnumSet<Signal>.contains(s : Signal, t : Signal) :Boolean {
    return this.contains(s) && this.contains(t)
}
