import java.io.File

fun String.readFile(): String {
    return File(this).readText()
}
fun String.findMatches(regex: Regex): List<String> {
    return regex.findAll(this).map { it.value }.toList()
}