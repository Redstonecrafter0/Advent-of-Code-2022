
fun main() {
    fun parse(input: List<String>): Pair<List<MutableList<Char>>, List<Triple<Int, Int, Int>>> {
        val gapIndex = input.indexOf("")
        val cratesRange = 0 until input[gapIndex - 1].split(" ").last().toInt()
        val cratesVertical = input
                .subList(0, gapIndex - 1)
                .map { cratesRange.map { i -> it.getOrNull(i * 4 + 1).let { s -> if (s == ' ') null else s } } }
                .reversed()
        val stacks = cratesRange.map { mutableListOf<Char>() }
        cratesVertical.forEach {
            for (i in cratesRange) {
                stacks[i] += it[i] ?: continue
            }
        }
        val inputs = input.subList(gapIndex + 1, input.size).map { it.split(" ") }.map { Triple(it[1].toInt(), it[3].toInt(), it[5].toInt()) }
        return stacks to inputs
    }

    fun part1(input: List<String>): String {
        val (stacks, inputs) = parse(input)
        inputs.forEach { (first, second, third) ->
            for (i in 1..first) {
                stacks[third - 1] += stacks[second - 1].last()
                stacks[second - 1].removeLast()
            }
        }
        return stacks.map { it.last() }.toCharArray().concatToString()
    }

    fun part2(input: List<String>): String {
        val (stacks, inputs) = parse(input)
        inputs.forEach { (first, second, third) ->
            stacks[third - 1] += stacks[second - 1].subList(stacks[second - 1].size - first, stacks[second - 1].size)
            for (i in 1..first) {
                stacks[second - 1].removeLast()
            }
        }
        return stacks.map { it.last() }.toCharArray().concatToString()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
