package day14

import readInput


/**
 * Part 1 find the polymer string - witch is a "more" fun exercise, but the size of the
 * string is "too big", so in part 2 we just count the parts.
 */
fun main() {
    val filename =  "day14/input-my"
    val (result, rules) = read2(filename)
    println("Template: $result")
    println("Rules: $rules")

    println("Part 2")
    repeat(40) {
        result.insert(rules)
        println(result)
    }
    val counts = HashMap<Char, Long>()

    result.values().forEach { e ->
        counts.put(e.a, counts.getOrDefault(e.a, 0) + e.n)
    }
    val min = counts.minOf { it.value }
    val max = counts.maxOf { it.value }

    println(String.format("%,d - %,d = %,d", max, min, max - min))
}

class P2 (val a : Char, val b : Char, var n: Long = 1) {
    fun merge(other : P2) { n += other.n }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is P2) return false
        return a == other.a && b == other.b
    }
    override fun hashCode(): Int = 31 * a.hashCode() + b.hashCode()
    override fun toString(): String = "($a$b #$n)"
}

class P2Set {
    var set = mutableMapOf<P2, P2>()

    fun values() : Collection<P2> { return set.keys }
    fun add(e : P2) { set.addOrMerge(e) }
    fun insert(rules: List<InsertionRule2>) {
        val result = mutableMapOf<P2, P2>()

        for (e in set.keys) {
            val rule  = rules.singleOrNull { it.match(e) }
            if(rule != null) {
                result.addOrMerge(P2(e.a, rule.v, e.n))
                result.addOrMerge(P2(rule.v, e.b, e.n))
            }
            else {
                result.addOrMerge(e)
            }
        }
        set = result
    }
    override fun toString(): String { return set.keys.toString() }
}

fun MutableMap<P2, P2>.addOrMerge(e : P2) {
    if(this.contains(e)) { this.get(e)!!.merge(e) }
    else { this.put(e, e) }
}

data class InsertionRule2(val a : String, val v: Char) {
    fun match(e : P2) : Boolean {
        return a[0] == e.a && a[1] == e.b
    }
    override fun toString(): String = "$a -> $v"
}

fun read2(file : String) : Pair<P2Set, List<InsertionRule2>> {
    val initialSet = P2Set()
    val rules = ArrayList<InsertionRule2>()
    readInput(file).forEach { line ->
        if(line.isBlank()) { /* continue */}
        else if ( line.all(Char::isLetter)) {
            line.windowed(2).forEach() { chunk ->
                initialSet.add(P2(chunk[0], chunk[1]))
            }
            // Add the last character as a "Pair", this make sure that is included in the
            // count in the end
            initialSet.add(P2(line.last(), '_'))
        }
        else if (line.contains(" -> ")) {
            rules.add(InsertionRule2(line.substring(0, 2), line[6]))
        }
        else throw IllegalStateException(line)
    }
    return Pair(initialSet, rules)
}
