package day12

import readInput

fun main() {
    val filename = "day12/input-my"
    println("Part 1")
    val caveIndex = HashMap<String, Node>()

    readInput(filename).forEach { line ->
        val (a,b) = line.split('-')
            .map { caveIndex.computeIfAbsent(it, ::Node)  }
        a.edges.add(b)
        b.edges.add(a)
    }
    val paths = caveIndex["start"]
        ?.traverse(listOf(), until = {e, list -> e.isSmall && list.contains(e)})!!
    println(paths.toStr())
    println("Number of paths: " + paths.size)

    println()
    println("Part 2")
    val paths2 = caveIndex["start"]
        ?.traverse(listOf(), until = { e, list ->e.isSmall && list.smallCaveVisitedTwice(e) })!!
    val r = paths2.toStr()

    println(r)
    println("Number of paths: " + paths2.size)
}


class Node(val name : String, val edges : MutableList<Node> = ArrayList()) {
    val isSmall : Boolean = name.all { it.isLowerCase() }

    fun traverse(visited : List<Node>, until : (Node, List<Node>) -> Boolean) : List<List<Node>> {
        if(until.invoke(this, visited)) return listOf()
        val path = ArrayList(visited)
        path.add(this)
        if(name == "end") return listOf(path)
        return edges.map { it.traverse(path, until) }.flatten().toList()
    }

    override fun toString(): String {
        return name
    }
}

fun List<Node>.smallCaveVisitedTwice(e : Node) : Boolean {
    if(isEmpty()) return false
    if(e.name == "start") return true
    val small = this.filter(Node::isSmall)
    return small.contains(e) && small.size != small.toSet().size
}

fun List<List<Node>>.toStr() : String {
    return this.map { it.joinToString(" - ") }.sorted().joinToString("\n");
}