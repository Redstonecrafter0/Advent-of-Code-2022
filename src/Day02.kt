
enum class Move(val score: Int) {
    Rock(1), Paper(2), Scissors(3);

    fun vs(other: Move): Result {
        return when (this) {
            Rock -> when (other) {
                Rock -> Result.Draw
                Paper -> Result.Lost
                Scissors -> Result.Won
            }
            Paper -> when (other) {
                Rock -> Result.Won
                Paper -> Result.Draw
                Scissors -> Result.Lost
            }
            Scissors -> when (other) {
                Rock -> Result.Lost
                Paper -> Result.Won
                Scissors -> Result.Draw
            }
        }
    }
}

enum class Result(val score: Int) {
    Won(6), Lost(0), Draw(3);

    fun requires(other: Move): Move {
        return when (this) {
            Won -> when (other) {
                Move.Rock -> Move.Paper
                Move.Paper -> Move.Scissors
                Move.Scissors -> Move.Rock
            }
            Lost -> when (other) {
                Move.Rock -> Move.Scissors
                Move.Paper -> Move.Rock
                Move.Scissors -> Move.Paper
            }
            Draw -> other
        }
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val moves = input.map { it.split(" ") }.map { it[0] to it[1] }.map { (theirs, yours) ->
            when (theirs) {
                "A" -> Move.Rock
                "B" -> Move.Paper
                "C" -> Move.Scissors
                else -> error("invalid move")
            } to when (yours) {
                "X" -> Move.Rock
                "Y" -> Move.Paper
                "Z" -> Move.Scissors
                else -> error("invalid move")
            }
        }
        return moves.sumOf { (theirs, yours) -> yours.vs(theirs).score + yours.score }
    }

    fun part2(input: List<String>): Int {
        val moves = input.map { it.split(" ") }.map { it[0] to it[1] }.map { (theirs, result) ->
            when (theirs) {
                "A" -> Move.Rock
                "B" -> Move.Paper
                "C" -> Move.Scissors
                else -> error("invalid move")
            } to when (result) {
                "X" -> Result.Lost
                "Y" -> Result.Draw
                "Z" -> Result.Won
                else -> error("invalid move")
            }
        }
        return moves.sumOf { (theirs, result) -> result.requires(theirs).score + result.score }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
