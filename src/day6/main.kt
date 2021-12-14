package day6

import readInput
import toIntArray

fun main() {
    val population : Population = readPopulation("day6/input-my")

    println("The initial population: $population")

    repeat(256) { day ->
        population.swimForADay()
        println("Population after day ${day+1}: $population, total: ${population.total()}")
    }
}

class Population(var ageGroups : LongArray = LongArray(9)) {
    fun add(value : Int) { ageGroups[value]++ }
    fun total(): Long { return ageGroups.sum() }
    fun swimForADay() {
        val ready = ageGroups[0];
        (0 until (ageGroups.size-1)).forEach { i -> ageGroups[i] = ageGroups[i+1] }
        ageGroups[8] = ready
        ageGroups[6] += ready
    }
    override fun toString(): String {
        return ageGroups.toList().toString()
    }
}

private fun readPopulation(file : String) = toIntArray(
    readInput(file).first()
).fold(Population()) { p, i -> p.add(i); p }
