
fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map { it.split(",").map { i -> i.split("-").map { j -> j.toInt() } } }
            .map { it.map { i -> i[0]..i[1] } }
            .map { it[0] to it[1] }
            .count { (first, second) ->
                (first.first in second && first.last in second) || (second.first in first && second.last in first)
            }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.split(",").map { i -> i.split("-").map { j -> j.toInt() } } }
            .map { it.map { i -> i[0]..i[1] } }
            .map { it[0] to it[1] }
            .count { (first, second) ->
                first.first in second || first.last in second || second.first in first || second.last in first
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
