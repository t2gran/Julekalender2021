package day10

import readInput

fun main() {

    val filename = "day10/input-my"

    val sum = readInput(filename)
        .map { corruptedLine(it) }
        .sum()
    println("Part 1: $sum")
    println()

    val res = readInput(filename)
        .map { toFixLine(it) }
        .filterNotNull()
        .map { it.asIterable().fold(0L) { acc, ch -> acc * 5  + score(ch) } }
        .toList()
        .sorted()
        .toList()
    println(res.joinToString("\n2"))
    println()
    println("Part 2: ${res[res.size/2]}")
}

fun corruptedLine(line: String) : Int {
    val s = Stack<Char>()
    line.forEach {
        when(it) {
            '(', '[', '{', '<' -> s.push(it)
            ')' -> if(s.pop() != '(') return points(it)
            ']' -> if(s.pop() != '[') return points(it)
            '}' -> if(s.pop() != '{') return points(it)
            '>' -> if(s.pop() != '<') return points(it)
        }
    }
    return 0
}

fun toFixLine(line: String) : String? {
    val s = Stack<Char>()
    line.forEach {
        when(it) {
            '(' -> s.push(')')
            '[' -> s.push(']')
            '{' -> s.push('}')
            '<' -> s.push('>')
            ')' , ']', '}', '>' -> if(s.pop() != it) return null
        }
    }
    println(line + "  ---  " + s.toString().reversed())
    return s.toString().reversed()
}


class Stack<T> {
    val list : MutableList<T> = ArrayList<T>()
    fun push(item: T) { list.add(item) }
    fun pop() : T? { return if (list.isNotEmpty()) list.removeAt(list.lastIndex) else null }

    override fun toString(): String {
        return list.joinToString("")
    }
}

fun points(ch : Char) : Int {
    when(ch) {
        ')' -> return 3
        ']' -> return 57
        '}' -> return 1197
        '>' -> return 25137
    }
    return 0
}

fun score(ch : Char) : Int {
    when(ch) {
        ')' -> return 1
        ']' -> return 2
        '}' -> return 3
        '>' -> return 4
    }
    return 0
}