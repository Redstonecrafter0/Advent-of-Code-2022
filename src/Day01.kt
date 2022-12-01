
fun main() {
    fun parse(input: List<String>): List<Int> {
        val separators = listOf(-1) + input.withIndex().filter { it.value == "" }.map { it.index } + (input.lastIndex + 1)
        val elves = separators.windowed(2).map { input.subList(it[0] + 1, it[1]) }
        return elves.map { it.sumOf { i -> i.toInt() } }
    }

    fun part1(input: List<String>): Int {
        val sums = parse(input)
        return sums.max()
    }

    fun part2(input: List<String>): Int {
        val sortedSums = parse(input).sortedBy { it }.reversed()
        return sortedSums.subList(0, 3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
