
data class Knot(var x: Int = 1000, var y: Int = 1000)

fun main() {
    val pullMap = buildList<List<Pair<Int, Int>>> {
        this += listOf(1 to 1, 1 to 1, 0 to 1, -1 to 1, -1 to 1)
        this += listOf(1 to 1, 0 to 0, 0 to 0, 0 to 0, -1 to 1)
        this += listOf(1 to 0, 0 to 0, 0 to 0, 0 to 0, -1 to 0)
        this += listOf(1 to -1, 0 to 0, 0 to 0, 0 to 0, -1 to -1)
        this += listOf(1 to -1, 1 to -1, 0 to -1, -1 to -1, -1 to -1)
    }

    fun part1(input: List<String>): Int {
        val visited = mutableSetOf<Knot>()
        val commands = input.map { it.split(" ") }.map { it[0] to it[1].toInt() }
        val head = Knot()
        val tail = Knot()
        visited += tail.copy()
        commands.forEach { (dir, amount) ->
            for (i in 1..amount) {
                when (dir) {
                    "U" -> head.y++
                    "D" -> head.y--
                    "R" -> head.x++
                    "L" -> head.x--
                }
                val offset = pullMap[tail.y - head.y + 2][tail.x - head.x + 2]
                tail.x += offset.first
                tail.y += offset.second
                visited += tail.copy()
            }
        }
        return visited.size
    }

    fun part2(input: List<String>): Int {
        val visited = mutableSetOf<Knot>()
        val commands = input.map { it.split(" ") }.map { it[0] to it[1].toInt() }
        val rope = buildList {
            for (i in 1..10) {
                this += Knot()
            }
        }
        visited += rope.last().copy()
        commands.forEach { (dir, amount) ->
            for (i in 1..amount) {
                when (dir) {
                    "U" -> rope.first().y++
                    "D" -> rope.first().y--
                    "R" -> rope.first().x++
                    "L" -> rope.first().x--
                }
                rope.windowed(2).forEach { (first, second) ->
                    val offset = pullMap[second.y - first.y + 2][second.x - first.x + 2]
                    second.x += offset.first
                    second.y += offset.second
                }
                visited += rope.last().copy()
            }
        }
        return visited.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
    check(part2(readInput("Day09_test2")) == 36)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
