
class VFile(val name: String, val size: Int = -1) {

    val children: MutableMap<String, VFile> = mutableMapOf()

    val isDir: Boolean
        get() = size < 0

    val dirSize: Int
        get() = if (isDir) children.values.sumOf { it.dirSize } else size

    override fun toString(): String {
        return "VFile(name=$name, size=$size, dirSize=$dirSize, isDir=$isDir)"
    }

    fun resolve(path: List<String>): VFile {
        return if (path.isEmpty()) {
            this
        } else {
            children.values.first { it.name == path.first() }.resolve(path.subList(1, path.size))
        }
    }

    fun flat(): List<VFile> {
        return listOf(this) + children.map { (_, v) -> v.flat() }.flatten()
    }

}

fun main() {
    fun buildFileTree(input: List<String>): VFile {
        val dirStack = mutableListOf<String>()
        val root = VFile("/")
        for (i in input) {
            if (i.startsWith("$ ")) {
                val cmd = i.substring(2).split(" ")
                if (cmd[0] == "cd") {
                    when (cmd[1]) {
                        "/" -> dirStack.clear()
                        ".." -> dirStack.removeLast()
                        else -> dirStack += cmd[1]
                    }
                }
            } else {
                val (size, name) = i.split(" ")
                if (size == "dir") {
                    root.resolve(dirStack).children += name to VFile(name)
                } else {
                    root.resolve(dirStack).children += name to VFile(name, size.toInt())
                }
            }
        }
        return root
    }

    fun part1(input: List<String>): Int {
        val root = buildFileTree(input)
        return root.flat().filter { it.isDir && it.dirSize in 0..100000 }.sumOf { it.dirSize }
    }

    fun part2(input: List<String>): Int {
        val root = buildFileTree(input)
        val missingFree = 30000000 - (70000000 - root.dirSize)
        return root.flat().filter { it.isDir && it.dirSize >= missingFree }.minBy { it.dirSize }.dirSize
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
