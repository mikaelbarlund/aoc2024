import java.io.File

fun String.readFile(): String {
    return File(this).readText()
}
