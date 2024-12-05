fun main() {
    val file = "src/input/santa/Day05.txt"
    Day05.day05(file)
}

class Day05 {
    companion object {
        fun day05(path: String) {
            // parse data
            val data = path.readFile().split("\n\n")
            val rules = data[0].split("\n").map { a -> a.split("|").map { it.toInt() } }
            val updates = data[1].split("\n").map { a -> a.split(",").map { it.toInt() } }

            // part 1
            val inOrder = updates.filter { a -> a.isOrdered(rules) }
            val middleValues = inOrder.map { a -> a[a.size / 2] }
            println(
                "first: " + middleValues.sum()
            )

            // part 2
            val outOfOrder = updates.filter { a -> !a.isOrdered(rules) }
            val sortedOutOfOrder = outOfOrder.map { it.sortedByRules(rules) }
            val middleValuesThen = sortedOutOfOrder.map { a -> a[a.size / 2] }
            println(
                "second: " + middleValuesThen.sum()
            )
        }
    }
}

private fun List<Int>.isOrdered(rules: List<List<Int>>): Boolean {
    return rules.all { rule ->
        (this.indexOf(rule[0]) < this.indexOf(rule[1])) || this.indexOf(rule[0]) < 0 || this.indexOf(rule[1]) < 0
    }
}

private fun List<Int>.sortedByRules(rules: List<List<Int>>): List<Int> {
    return this.sortedWith { a, b -> if (rules.contains(listOf(a, b))) -1 else 1 }
}
