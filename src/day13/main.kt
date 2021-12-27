package day13

import readInput
import splitLine
import toPair
import java.lang.Integer.max
import java.util.*

fun main() {
    val filename = "day13/input-my"
    println("Part 1")
    val origami = readOrigami(filename)
    val m = origami.toMatrix()
    var r = m.fold(origami.instruction.first())
    println(r)
    println(r.count())

    println()
    println("Part 2")
    r = origami.instruction.fold(m, Matrix::fold);
    println(r)
}

class Matrix(val width : Int, val height : Int) {
    private val c : BitSet = BitSet(width * height)

    operator fun get(x : Int, y : Int) : Boolean {
        if(outside(x, y)) return false
        return c[index(x,y)]
    }
    operator fun set(x : Int, y : Int, v : Boolean) {
        if(outside(x, y)) return
        c[index(x,y)] = v
    }

    fun fold(cmd : FoldCmd) : Matrix {
        return if(cmd.x_axis) foldXAxis(cmd.value) else foldYAxis(cmd.value)
    }

    fun foldXAxis(x : Int) : Matrix {
        val m0 = this.crop(0, x, 0, height).flipVertically();
        val m1 = this.crop(x+1, width, 0, height);
        return m0.union(m1).flipVertically()
    }

    fun foldYAxis(y : Int) : Matrix {
        val m0 = this.crop(0, width, 0, y).flipHorizontally();
        val m1 = this.crop(0, width, y+1, height);
        return m0.union(m1).flipHorizontally()
    }

    fun union(other : Matrix) : Matrix {
        val m = Matrix(max(width, other.width), max(height, other.height))
        m.forEachIndexed{ i,j ->
            m[i, j] = this[i, j] || other[i, j]
        }
        return m
    }

    fun crop(x0:Int, x1:Int, y0:Int, y1:Int) : Matrix {
        val m = Matrix(x1-x0, y1-y0)
        m.forEachIndexed{ i,j -> m[i, j] = this[x0+i, y0+j] }
        return m
    }

    fun flipHorizontally() : Matrix {
        val m = Matrix(width, height)
        m.forEachIndexed{ i,j -> m[i, j] = this[i, height-(j+1)] }
        return m
    }
    fun flipVertically() : Matrix {
        val m = Matrix(width, height)
        m.forEachIndexed{ i,j -> m[i, j] = this[width-(i+1), j] }
        return m
    }

    override fun toString(): String {
        val buf = StringBuilder()
        for(y in 0 until height) {
            for(x in 0 until width) {
                buf.append(if(this.get(x, y)) '#' else ' ')
            }
            buf.append(" |\n")
        }
        return buf.toString()
    }

    fun count() : Int {
        return c.cardinality()
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
}

data class FoldCmd(val x_axis : Boolean, val value : Int)

data class Origami(val coordinates : List<Pair<Int, Int>>, val instruction : List<FoldCmd> ) {
    companion object {
        fun builder() : Builder = Builder();
    }
    class Builder() {
        val coordinates : MutableList<Pair<Int, Int>> = ArrayList()
        val instruction : MutableList<FoldCmd> = ArrayList()
        fun build() : Origami = Origami(coordinates, instruction)
    }
    fun toMatrix() : Matrix {
        val m = Matrix(coordinates.maxOf { it.first }+1, coordinates.maxOf { it.second }+1)
        coordinates.forEach { m[it.first, it.second] = true }
        return m
    }
}

fun readOrigami(file : String) : Origami {
    val builder = Origami.builder()
    readInput(file).forEach { line ->
        if (line.startsWith("fold along x=")) {
            builder.instruction.add(FoldCmd(true, line.substring(13).toInt()))
        }
        else if (line.startsWith("fold along y=")) {
            builder.instruction.add(FoldCmd(false, line.substring(13).toInt()))
        }
        else if (line.isNotBlank()) {
            builder.coordinates.add(splitLine(line, String::toInt).toPair())
        }
    }
    return builder.build()
}
