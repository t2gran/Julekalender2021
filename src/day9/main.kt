package day9

import bgBlue
import readInput
import kotlin.math.min

fun main() {
    val m =  readMatrix("day9/input-my")
    println(m)
    println("Risk level: ${m.sumRiskLevel()}")

    println(m)
    val basins = m.findBasins().sortedDescending()
    println(m)
    println("Basins: $basins")
    println("Total : ${basins.take(3).reduce { acc, i -> acc * i }}")
}

class Matrix(val width : Int, val height : Int) {
    val c : IntArray = IntArray(width * height)

    fun get(x : Int, y : Int) : Int { return c[index(x,y)] }
    fun set(x : Int, y : Int, v : Int) {  c[index(x,y)] = v }

    fun getMax(x : Int, y : Int) : Int {
        if(x<0 || x >= width || y < 0 || y >= height) { return 99 }
        return c[index(x,y)]
    }

    fun isLocalMin(x : Int, y : Int) : Boolean {
        val n = getMax(x, y-1)
        val w = getMax(x-1, y)
        val s = getMax(x, y+1)
        val e = getMax(x+1, y)
        val minNeighbour = min(min(n, w), min(s, e))
        return get(x,y) < minNeighbour
    }

    fun sumRiskLevel() : Int {
        var sum = 0
        for(x in (0 until width)) {
            for(y in (0 until height)) {
                if(isLocalMin(x, y)) sum += get(x, y) + 1
            }
        }
        return sum
    }

    fun findBasins() : List<Int> {
        val result = ArrayList<Int>()
        for(x in (0 until width)) {
            for(y in (0 until height)) {
                result.add(basin(x, y))
            }
        }
        return result
    }

    private fun basin(x: Int, y: Int): Int {
        val v = getMax(x, y)
        if(v >= 9) return 0;
        set(x, y, v  + 10)
        var sum = 1
        sum += basin(x, y-1)
        sum += basin(x-1, y)
        sum += basin(x, y+1)
        sum += basin(x+1, y)
        set(x, y, v  + 10)
        return sum;
    }

    override fun toString(): String {
        var buf = ""
        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                val v = get(x, y) % 10
                buf += if(v == 9) v else bgBlue(v)
            }
            buf += "\n"
        }
        return buf
    }
    private fun index(x: Int, y: Int) = x + y * width
}

fun readMatrix(file : String) : Matrix {
    val lines =  readInput(file)
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
