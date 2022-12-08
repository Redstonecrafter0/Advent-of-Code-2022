
fun main() {
    fun part1(input: List<String>): Int {
        val grid = input.map { it.toCharArray().map { i -> i.toString().toInt() } }
        var visible = grid.size * 2 + grid.first().size - 2 + grid.last().size - 2
        for (y in 1 until grid.lastIndex) {
            for (x in 1 until grid[y].lastIndex) {
                var test = true
                for (ix in (x - 1) downTo 0) {
                    if (grid[y][ix] >= grid[y][x]) {
                        test = false
                    }
                }
                if (test) {
                    visible++
                    continue
                }
                test = true
                for (iy in (y - 1) downTo 0) {
                    if (grid[iy][x] >= grid[y][x]) {
                        test = false
                    }
                }
                if (test) {
                    visible++
                    continue
                }
                test = true
                for (ix in (x + 1)..grid[y].lastIndex) {
                    if (grid[y][ix] >= grid[y][x]) {
                        test = false
                    }
                }
                if (test) {
                    visible++
                    continue
                }
                test = true
                for (iy in (y + 1)..grid.lastIndex) {
                    if (grid[iy][x] >= grid[y][x]) {
                        test = false
                    }
                }
                if (test) {
                    visible++
                }
            }
        }
        return visible
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { it.toCharArray().map { i -> i.toString().toInt() } }
        val scores = mutableListOf<Int>()
        for (y in 1 until grid.lastIndex) {
            for (x in 1 until grid[y].lastIndex) {
                var left = 0
                for (ix in (x - 1) downTo 0) {
                    left++
                    if (grid[y][ix] >= grid[y][x]) {
                        break
                    }
                }
                var up = 0
                for (iy in (y - 1) downTo 0) {
                    up++
                    if (grid[iy][x] >= grid[y][x]) {
                        break
                    }
                }
                var right = 0
                for (ix in (x + 1)..grid[y].lastIndex) {
                    right++
                    if (grid[y][ix] >= grid[y][x]) {
                        break
                    }
                }
                var down = 0
                for (iy in (y + 1)..grid.lastIndex) {
                    down++
                    if (grid[iy][x] >= grid[y][x]) {
                        break
                    }
                }
                scores += left * up * right * down
            }
        }
        return scores.max()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
