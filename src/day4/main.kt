package day4

import readInput
import splitLine

fun main() {
    val input =  readInput("day4/input-my")

    val game = ofGame(input)

    println("Part 1:")
    game.playBingoVariant { it.playBingo(false) }

    println("Part 2:")
    game.playBingoVariant { it.findLastBingoBoard(true) }
}

data class BingoNumber(val value : Int, var marked : Boolean) {
    override fun toString(): String {
        val text = String.format("%3s", value)
        return if(marked) "\u001B[31m$text\u001B[0m" else text
    }
}

class BingoGame(val numbers : List<BingoNumber>, val boards : List<Board> = ArrayList()) {
    var drawNumber  : Int = 0
    fun lastNumber() : Int { return numbers[drawNumber-1].value }
    fun bingo(): Board? {
        return boards.find{ it.bingo() }
    }
    fun playBingoVariant(gameVariant : (BingoGame) -> Board) {
        reset()
        val board = gameVariant.invoke(this)
        println("Drawn numbers: ${numbers}")
        println(board)
        val sumUnmarked = board.sumUnmarkedNumbers()
        val answer = lastNumber() * sumUnmarked
        println("Answer: last=${lastNumber()}, sum unmarked=$sumUnmarked, product= $answer\n")
    }
    fun playBingo(log : Boolean = false) : Board {
        var board : Board? = null
        while (board == null) {
            drawNumber()
            board = bingo()
            if(log) log()
        }
        return board
    }
    fun findLastBingoBoard(log : Boolean = false) : Board {
        var boardWithoutBingo = boards
        var board : Board? = null
        while (!boardWithoutBingo.isEmpty()) {
            drawNumber()
            boardWithoutBingo = boardWithoutBingo.filter { ! it.bingo() }
            if(boardWithoutBingo.size == 1) {
                board = boardWithoutBingo[0]
            }
            if(log) println("-> New board found. " +
                    "Number: ${lastNumber()}, " +
                    "left: ${boardWithoutBingo.size}\n" +
                    "$numbers\n"
            )
        }
        return board!!
    }
    private fun log() {
        println("-> Draw numbers: $numbers")
        boards.forEach { println(it); println() }
    }
    private fun reset() {
        numbers.forEach{ it.marked = false }
        drawNumber = 0
    }
    private fun drawNumber() { numbers[drawNumber++].marked = true }
}

class Board(val rows : List<List<BingoNumber>>) {
    val col : List<List<BingoNumber>> = (0..4).map { i -> rows.map { row ->  row[i] } }

    fun bingo() : Boolean {
        return rows.any { it.bingo() } || col.any{ it.bingo() }
    }

    override fun toString(): String {
        return rows.joinToString("\n") {
            it.joinToString(" ")
        }
    }

    fun sumUnmarkedNumbers() : Int {
        return rows.flatMap { it }
            .filter { !it.marked }
            .map { it.value }
            .sum() }
}

fun ofGame(lines: List<String>): BingoGame {
    val it = lines.iterator()
    val numbers = splitLine(it.next()) { BingoNumber(it.toInt(), false)}
    val numberIndex = numbers.associateBy { it.value }.toMutableMap()
    val boards = ArrayList<Board>()

    assert(it.next().isBlank())

    while (it.hasNext()) {
        boards.add(
            Board((0..4).map { i -> ofBingoNumbers(numberIndex, it.next()) }.toList())
        )
        if(it.hasNext()) {
            assert(it.next().isBlank())
        }
    }
    return BingoGame(numbers, boards)
}

fun ofBingoNumbers(numberIndex: MutableMap<Int, BingoNumber>, line: String) : List<BingoNumber> {
    return splitLine(line) {
            it -> numberIndex.computeIfAbsent(it.toInt()) { BingoNumber(it, false) }
    }
}

fun  List<BingoNumber>.bingo() : Boolean { return this.all { it.marked } }
