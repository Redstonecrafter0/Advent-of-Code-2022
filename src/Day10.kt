
fun main() {
    fun part1(input: List<String>): Int {
        var x = 1
        var cycle = 0
        var result = 0
        var lastAdd = 0
        for (cmd in input.map { it.split(" ") }) {
            when (cmd[0]) {
                "noop" -> cycle++
                "addx" -> cycle += 2
            }
            if ((cycle + 20) / 40 != lastAdd) {
                lastAdd = (cycle + 20) / 40
                result += (if (cycle % 2 == 1) cycle - 1 else cycle) * x
            }
            if (cmd[0] == "addx") {
                x += cmd[1].toInt()
            }
        }
        return result
    }

    fun part2(input: List<String>) {
        var x = 1
        var cycle = 0
        var instruction = -1
        var leftCycles = 0
        val instructions = input.map { it.split(" ") }
        while (true) {
            if (leftCycles == 0) {
                if (instruction >= 0 && instructions[instruction][0] == "addx") {
                    x += instructions[instruction][1].toInt()
                }
                instruction++
                when (instructions[instruction][0]) {
                    "noop" -> leftCycles = 1
                    "addx" -> leftCycles = 2
                }
            }
            leftCycles--
            cycle++
            if (cycle % 40 in x..(x + 2)) {
                print("#")
            } else {
                print(".")
            }
            if (cycle % 40 == 0) {
                println()
            }
            if (instruction == instructions.lastIndex) {
                break
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    part2(testInput)

    val input = readInput("Day10")
    println(part1(input))
    part2(input)
}
