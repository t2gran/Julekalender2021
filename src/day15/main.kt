package day15


import StopWatch
import readInput

fun main() {
    val filename = "day15/input-my"
    println("Part 1")
    /*
        Part one can be solved using a classic graph, but we already implemented a Matrix class,
        and we will use it instead of a graph. The input is read into matrix m. To find the
        accumulated risk at the destination we compute the accumulated risk for each cell in a
        result matrix (r). All cells in r are initially set to a high value except the starting
        cell, witch is set to zero.

        The risk of r[x,y] is the lowest risk of all neighboring cells plus m[x,y]. We do not
        know the order(path) to calculate the risk for each cell, but, instead we can iterate
        over all cells in the matrix until no better(lower) risks are found.

        Note! We do not find the path through the cave only the lowest risk level for each
        cell (including the destination).

    */
    val m = readMatrix(filename)
    var r = findRiskLevels(m)
    println(m)
    println(r)
    println("Risk level at exit: " +  r.lastCell())

    println()
    println("Part 2")
    val n = expandMatrix(m)
    StopWatch.timeAndPrint("Execution time findRiskLevels():") {
        r = findRiskLevels(n)
    }
    println("Risk level at exit: " +  r.lastCell())
}

private fun findRiskLevels(m: Matrix) : Matrix {
    val r = Matrix(m.width, m.height)
    r.forEachIndexed { i, j -> r[i, j] = Matrix.MAX_VALUE }
    r[0, 0] = 0
    var changed = true

    while (changed) {
        changed = false
        r.forEachIndexed { i, j ->
            val min = m[i, j] + minOf(r[i, j-1], r[i+1, j], r[i, j+1], r[i-1, j])

            if (min < r[i, j]) {
                changed = true
                r[i, j] = min
            }
        }
    }
    return r
}

/** Expand Matrix the given n times both horizontally and vertically. */
private fun expandMatrix(m: Matrix, n : Int = 5): Matrix {
    val r = Matrix(m.width * n, m.height * n)
    for (i in 0 until n) {
        val i0 = m.width * i
        for (j in 0 until n) {
            val j0 = m.height * j
            m.forEachIndexed { x, y ->
                r[i0 + x, j0 + y] = 1 + (m[x, y] + i + j - 1) % 9
            }
        }
    }
    return r
}

class Matrix(val width : Int, val height : Int) {
    private val c : IntArray = IntArray(width * height)

    operator fun get(x : Int, y : Int) : Int {
        if(outside(x, y)) return MAX_VALUE
        return c[index(x,y)]
    }
    operator fun set(x : Int, y : Int, v : Int) {
        if(outside(x, y)) return
        c[index(x,y)] = v
    }

    fun lastCell() : Int { return this[width-1, height-1] }

    override fun toString(): String {
        val buf = StringBuilder()
        for(y in 0 until height) {
            for(x in 0 until width) {
                buf.append(String.format(" %2d", this[x, y]))
            }
            buf.append(" |\n")
        }
        return buf.toString()
    }


    fun forEachIndexed(body : (i : Int, j : Int) -> Unit) {
        for(j in (0 until height)) {
            for(i in (0 until width)) {
                body.invoke(i, j)
            }
        }
    }

    private fun index(x: Int, y: Int) = x + y * width
    private fun outside(x: Int, y: Int) = x<0 || x >= width || y < 0 || y >= height
    companion object {
        val MAX_VALUE = 1_000_000
    }
}

fun readMatrix(file : String) : Matrix {
    val lines = readInput(file)
    val height = lines.size
    val width = lines.first().length
    val m = Matrix(width, height)

    lines.forEachIndexed {  y, line ->
        for(x in line.indices) {
            val v  = line[x].digitToInt()
            m[x, y] = v
        }
    }
    return m
}
