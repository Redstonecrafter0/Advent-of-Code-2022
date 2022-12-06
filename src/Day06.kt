
fun main() {
    fun decode(str: String, width: Int): Int {
        return str
            .toCharArray().toList()
            .windowed(width)
            .withIndex()
            .first { (_, v) -> v.all { v.count { i -> i == it } == 1 } }.index + width
    }

    fun part1(input: List<String>): Int {
        return decode(input.first(), 4)
    }

    fun part2(input: List<String>): Int {
        return decode(input.first(), 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
