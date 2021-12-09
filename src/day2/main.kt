package day2

import readInput

/**
 * https://adventofcode.com/
 */
fun main() {
    Submarine()
        .goto(commands("ex"))
        .log()
}

class Submarine(var depth: Int = 0, var horizontalPos: Int = 0, var aim : Int = 0) {
    fun dive(v : Int) { aim += v }
    fun rise(v : Int) { aim -= v }
    fun forward(v : Int) { horizontalPos += v; depth += aim * v; }
    fun answer() : Int { return depth * horizontalPos }
    fun goto(commands : List<Command>) : Submarine {
        commands.forEach { it.execute(this) }; return this
    }
    fun log() {
        println("depth=$depth, horizontalPos=$horizontalPos, aim=$aim, answer=${answer()}")
    }
}

class Command(private val action : Action, private val value: Int) {
    fun execute(sub : Submarine) { action.apply(sub, value) }
}

enum class Action(val apply : (Submarine, Int) -> Unit) {
    Forward(Submarine::forward),
    Up(Submarine::rise),
    Down(Submarine::dive);
}

fun commands(name : String) : List<Command> {
    return readInput("day2/input-$name") { toCommand(it) }
}

fun toCommand(line: String) : Command {
    return line.split(" ").let {
        Command(toAction(it.first()), it.last().toInt())
    }
}

fun toAction(text: String): Action {
    return Action.values().find { it.name.equals(text, true) }!!
}
