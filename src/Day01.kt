import kotlin.math.abs

fun main() {
    val file = "src/input/santa/Day01.txt"
    Day01.day01(file)
}

class Day01 {
    companion object {
        fun day01(path: String) {
            println(
                "day 1 first: " +
                        path.readFile()
                            .splitTwice()
                            .listOfListsToListOfPairs()
                            .sortPairOfLists()
                            .calculateDifferences()
                            .sum()
            )
            println(
                "day 1 second: " +
                        path.readFile()
                            .splitTwice()
                            .listOfListsToListOfPairs()
                            .calculateAmounts()
                            .multiplyFirstBySecond()
                            .sum()
            )
        }

        private fun String.splitTwice(): List<List<String>> = this.split("\n").map { a -> a.split("   ") }

        private fun List<List<String>>.listOfListsToListOfPairs(): Pair<List<String>, List<String>> =
            this.fold(Pair(listOf<String>(), listOf<String>()))
            { a, b -> Pair(a.first + b[0], a.second + b[1]) }

        private fun Pair<List<String>, List<String>>.sortPairOfLists(): Pair<List<String>, List<String>> {
            return Pair(this.first.sorted(), this.second.sorted())
        }

        private fun Pair<List<String>, List<String>>.calculateDifferences(): List<Int> {
            return this.first.zip(this.second) { a, b -> abs(a.toInt() - b.toInt()) }
        }

        private fun Pair<List<String>, List<String>>.calculateAmounts(): Pair<List<Int>, List<Int>> {
            val counts = this.first.map { item -> this.second.count { it == item } }
            return Pair(this.first.map { a -> a.toInt() }, counts)
        }

        private fun Pair<List<Int>, List<Int>>.multiplyFirstBySecond(): List<Int> {
            return this.first.zip(this.second) { a, b -> a * b }
        }
    }


}