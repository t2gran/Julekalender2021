package day14

import readInput

fun main() {
    val filename =  "day14/input-ex"
    val input = read(filename)
    println(input)

    println("Part 1")
    val result : PE = input.template
    repeat(10) {
        result.insert(input.rules)
        //println(result)
    }

    val counts = HashMap<Char, Long>()

    result.forEach { e ->
        counts.put(e.ch, counts.getOrDefault(e.ch, 0) + 1)
    }
    val min = counts.minOf { it.value }
    val max = counts.maxOf { it.value }

    println("$max - $min = ${max - min}")
    println()
}

private fun insert(map : HashMap<PI, Int>, rules: List<InsertionRule>) {

}

class PI (val a : Char, val b : Char) {
    var n: Int = 1

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PI) return false
        return a == other.a && b == other.b
    }
    override fun hashCode(): Int = 31 * a.hashCode() + b.hashCode()
    override fun toString(): String = "($a$b #$n)"


}

class PE (val ch : Char, var next : PE?) {

    fun <T> fold(acc : T, body : (acc : T, ch : Char) -> T) : T {
        val r : T = body.invoke(acc, ch)
        return if(next == null) r else next?.fold(r, body)!!
    }

    fun insert(rules : List<InsertionRule>) {
        forEachPair { t0, t1 ->
            rules.find{ it.match(t0) }
                ?.let { t0.next = PE(it.v, t1) }
        }
    }

    fun forEach(body : (e : PE) -> Unit) {
        var e : PE? = this
        while (e != null) {
            body.invoke(e)
            e = e.next
        }
    }

    private fun forEachPair(body : (a : PE, b : PE) -> Unit) {
        var t0 : PE = this
        var t1 : PE? = next
        while (t1 != null) {
            body.invoke(t0, t1)
            t0 = t1
            t1 = t1.next
        }
    }

    override fun toString(): String {
        return append(StringBuilder()).toString()
    }
    private fun append(buf : StringBuilder) : StringBuilder {
        buf.append(ch)
        next?.append(buf)
        return buf
    }
}

data class InsertionRule(val a : Char, val b : Char, val v: Char) {
    fun match(pe : PE) : Boolean {
        return a == pe.ch && pe.next?.ch == b
    }
    override fun toString(): String = "$a$b -> $v"
}

data class PolymerInput(val template : PE, val rules : List<InsertionRule>)

fun read(file : String) : PolymerInput {
    var head : PE? = null
    val rules = ArrayList<InsertionRule>()
    readInput(file).forEach { line ->
        if(line.isBlank()) { /* continue */}
        else if ( line.all(Char::isLetter) && head == null) {
            head = line.foldRight(null, ::PE)
        }
        else if (line.contains(" -> ")) {
            rules.add(InsertionRule(line[0], line[1], line[6]))
        }
        else throw IllegalStateException(line)
    }
    return PolymerInput(head!!, rules)
}
