package day7

import readInput
import kotlin.math.abs

fun main() {
    println("Part 1");
    val list : MutableList<Int> = readInput("day7/input-my").first()
        .split(",")
        .map { it.toInt() }
        .toMutableList()
    list.sort()
    val mean = list.get(list.size / 2)
    val cost = list.sumOf { abs(it - mean) }
    println("Mean: $mean, cost: $cost")

    println("\nPart 2");

    val crabs = ArrayList<Reke>()
    var i=0
    var j=1
    while (i < list.size) {
        while (j < list.size && list[i] == list[j]) ++j
        crabs.add(Reke(list[i], j-i))
        i=j
        j++
    }
    println(crabs)

    val s = Bag(crabs.first(), 0, 1)
    val t = Bag(crabs.last(), crabs.size - 1, -1)

    while (s.v < t.v) {
        if(s.nextFuelCost() < t.nextFuelCost()) s.next(crabs) else t.next(crabs)
    }
    println()
    println("Mean: ${s.mean(t)}, cost: ${s.fuelCost() + t.fuelCost()}")
    println("S: $s")
    println("T: $t")
}

class Bag(r0 : Reke, var i : Int, val step : Int) {
    var v = r0.v0
    val set = mutableListOf(r0)
    fun fuelCost() : Int { return set.sumOf { it.fuelCost } }
    fun nextFuelCost() : Int { return set.sumOf { it.fuelCost(v+step) } }
    fun mean(other: Bag) : Int { return if(n() > other.n()) v else other.v }
    fun n() : Int { return set.sumOf{ it.n } }
    fun next(crabs : List<Reke>) {
        v += step
        set.forEach { it.gotoNext(v) }
        if(v == crabs[i+step].v0) {
            i += step
            set.add(crabs[i])
        }
    }
    override fun toString(): String { return "{ Cost: ${fuelCost()}, i: $i, v: $v, crabs: $set }" }
}

/** A Reke is a group of n Crabs at the same horizontal position with start position v0. */
data class Reke (val v0: Int, val n : Int) {
    var fuelCost : Int = 0
    fun fuelCost(v : Int) : Int { return abs(v - v0) * n }
    fun gotoNext(v : Int) { fuelCost += fuelCost(v) }
    override fun toString(): String { return "($v0 #$n €$fuelCost)" }
}
