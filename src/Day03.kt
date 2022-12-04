
fun main() {
    fun prioritize(common: List<Char>): Int {
        return common.sumOf {
            if (it in 'a'..'z') {
                it - 'a' + 1
            } else {
                it - 'A' + 27
            }
        }
    }

    fun part1(input: List<String>): Int {
        val rucksacks = input.map { it.substring(0, it.length / 2) to it.substring(it.length / 2, it.length) }
        val common = rucksacks.map { (first, second) ->
            for (i in first) {
                if (i in second) {
                    return@map i
                }
            }
            error("no common item")
        }
        return prioritize(common)
    }

    fun part2(input: List<String>): Int {
        val groups = input.chunked(3)
        val common = groups.map {
            for (i in it[0]) {
                if (i in it[1] && i in it[2]) {
                    return@map i
                }
            }
            error("no common item")
        }
        return prioritize(common)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
