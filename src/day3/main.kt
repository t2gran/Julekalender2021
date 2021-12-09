package day3

import readInput

const val ZERO_BIT = -1
const val ONE_BIT = 1

fun main() {
    val input = input("my")
    println("Part 1:")
    input.reduce(DiagnosticRow::plus).log()

    println("Part 2:")
    val oxygenGeneratorRating = input.rating { nZeros, nOnes -> nZeros > nOnes }.log()
    val co2ScrubberRating = input.rating { nZeros, nOnes -> nZeros <= nOnes }.log()
    println("Result: ${oxygenGeneratorRating.gamma() * co2ScrubberRating.gamma()}")
}

class DiagnosticRow(val value : IntArray) {
    operator fun plus(o : DiagnosticRow) : DiagnosticRow {
        return DiagnosticRow(
            value.mapIndexed { i, v -> v + o.value[i] }
                .toIntArray()
        )
    }
    fun gamma() : Int   { return value.binaryToInt(ZERO_BIT) }
    fun epsilon() : Int { return value.binaryToInt(ONE_BIT) };

    fun log() : DiagnosticRow {
        println(
            "Values: ${value.toList()}, gamma: ${gamma()}, " +
            "epsilon: ${epsilon()}, product: ${gamma() * epsilon()} "
        )
        return this
    }
}

fun IntArray.binaryToInt(zeroBit : Int) : Int {
    return this.fold(0) { acc, i -> acc*2 + if(i==zeroBit) 0 else 1 }
}

fun List<DiagnosticRow>.rating(selectBySize : (Int, Int) -> Boolean) : DiagnosticRow {
    var candidates = this;
    val indices = this.first().value.indices
    for(i in indices) {
        candidates =  candidates
            .partition { it.value[i] == ZERO_BIT }
            .let { (a,b) ->
                if(a.isEmpty()) b
                else if(b.isEmpty()) a
                else if(selectBySize(a.size, b.size)) a else b
            }
        if(candidates.size == 1) { return candidates.single() }
    }
    throw IllegalStateException("Multiple candidates found: $candidates")
}

fun input(name : String) : List<DiagnosticRow> {
    return readInput("day3/input-$name") {
        DiagnosticRow(
            it.asIterable()
                .map { ch -> if(ch == '0') ZERO_BIT else ONE_BIT }
                .toIntArray()
        )
    }
}
