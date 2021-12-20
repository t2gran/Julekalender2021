package day11

import Color.*
import color
import readInput

fun main() {
    val filename = "day11/input-my"
    println("Part 1")

    val m = readMatrix(filename)

    println(m)

    repeat(100) {
        m.incAll()
        @Suppress("ControlFlowWithEmptyBody")
        while (m.flashAll()) {
        }
        //println(m)
    }
    println(m)
    println("Flashes: ${m.flashes}")

    println()

    println("Part 2")

    val n = readMatrix(filename)
    var i = 0

    println(n)

    while(!n.isZero()) {
        n.incAll()
        while (n.flashAll()) { }
        //println(n)
        ++i
    }
    println(n)
    println("Flashes: ${i}")
}

class Matrix(val width : Int, val height : Int) {
    var flashes = 0
    val c : IntArray = IntArray(width * height)

    fun get(x : Int, y : Int) : Int { return c[index(x,y)] }
    fun set(x : Int, y : Int, v : Int) {  c[index(x,y)] = v }
    fun incM(x : Int, y : Int) {
        if(getM(x,y) >  0) {
            c[index(x, y)]++
        }
    }
    fun incAll() { for(i  in c.indices) c[i]++ }

    fun getM(x : Int, y : Int) : Int {
        if(outside(x, y)) { return -1 }
        return c[index(x,y)]
    }

    fun isZero() : Boolean  { return c.all { it == 0 } }

    fun flashAll() : Boolean {
        var flashed = false;
        for(y in (0 until height)) {
            for(x in (0 until width)) {
                if(get(x,y) > 9) {
                    flashed = true;
                    flash(x, y)
                }
            }
        }
        return flashed
    }

    private fun flash(x: Int, y: Int) {
        for(j in (y-1..y+1)) {
            for(i in (x-1..x+1)) {
                incM(i, j)
            }
        }
        ++flashes
        set(x, y, 0)
    }

    override fun toString(): String {
        var buf = ""
        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                val v = get(x, y)
                buf += if(v == 0) color(v%10, WhiteLT, Red) else if(v > 9) CyanLT.fg(v%10) else v
            }
            buf += "\n"
        }
        return buf
    }
    private fun index(x: Int, y: Int) = x + y * width
    private fun outside(x: Int, y: Int) = x<0 || x >= width || y < 0 || y >= height
}

fun readMatrix(file : String) : Matrix {
    val lines = readInput(file)
    val height = lines.size
    val width = lines.first().length
    val m = Matrix(width, height)

    lines.forEachIndexed {  y, line ->
        for(x in line.indices) {
            val v  = line[x].digitToInt()
            m.set(x, y, v)
        }
    }
    return m
}
