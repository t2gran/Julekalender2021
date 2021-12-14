package day5

import readInput

fun main() {
    val lines =  readInput("day5/input-my") { ofLine(it) }

    val linesPart1 = lines; //.filter { it.isHorizontal() || it.isVertical() }.toList()

    val size = linesPart1.fold(0) { acc, l -> Math.max(acc, l.max()) } + 1

    val m = Matrix(size, size)
    linesPart1.forEach(m::mark)
    //println(m.toString())
    println("Number > 2: " + m.count { it > 1 })
}

class V2 (val x: Int,  val y : Int) {
    operator fun plus(o : V2) : V2 { return V2(x+o.x, y+o.y) }
    operator fun minus(o : V2) : V2 { return V2(x-o.x, y-o.y) }
    operator fun plus(s : Int) : V2 { return V2(x+s, y+s) }
    operator fun minus(s :Int) : V2 { return this + -s }
    operator fun times(s : Int) : V2 { return V2(x*s, y*s) }
    operator fun div(s : Int) : V2 { return V2(x/s, y/s) }
    fun abs() : V2 { return V2(Math.abs(x), Math.abs(y)) }
    fun max() : Int { return Math.max(x, y) }
    override fun toString(): String { return "$x, $y" }
}

class Line (val p0: V2,  val p1 : V2) {
    fun max() : Int { return Math.max(p0.max(), p1.max()) }
    fun isHorizontal() : Boolean { return p0.y == p1.y }
    fun isVertical() : Boolean { return p0.x == p1.x }
    fun pointsOnLine() : List<V2> {
        var dV = p1 - p0
        val d = dV.abs().max()
        if(d == 0) { return listOf(p0) }
        dV /= d
        assert(Math.abs(dV.x + dV.y) == 1)
        return (0..d).fold(ArrayList()) { acc, i -> acc.add(p0 + (dV * i)); acc }
    }
    override fun toString(): String { return "$p0 -> $p1" }
}

class Matrix(val width : Int, val height : Int) {
    val c : IntArray = IntArray(width * height)

    fun get(x : Int, y : Int) : Int { return c[index(x,y)] }
    fun plus(p : V2, value : Int) {  c[index(p)] += value }
    fun mark(l : Line) { l.pointsOnLine().forEach { plus(it, 1) } }
    fun count(predicate :  (Int) -> Boolean) : Int { return c.count(predicate) }
    private fun index(p: V2) = index(p.x, p.y)
    private fun index(x: Int, y: Int) = x + y * height

    override fun toString(): String {
        var buf = ""
        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                buf += get(x, y)
            }
            buf += "\n"
        }
        return buf.replace('0', '.')
    }
}

fun ofLine(text : String) : Line {
    val m = text.split(Regex("[->, ]+")).map { it.toInt() }
    return Line(V2(m[0], m[1]), V2(m[2], m[3]))
}