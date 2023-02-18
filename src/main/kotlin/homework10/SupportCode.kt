package homework10

import java.io.File

/**
 * Asserts that the [expected] value is equal to the [actual] value.
 */
fun assertEquals(expected: Any, actual: Any) {
    if (expected != actual) {
        throw IllegalArgumentException("Expected <$expected>, actual <$actual>")
    }
}

/**
 * Asserts that the [actual] value is `true`.
 */
fun assertTrue(actual: Boolean) {
    if (!actual) {
        throw java.lang.IllegalArgumentException("Expected <true>, actual <$actual>")
    }
}

/**
 * Asserts that the [actual] value is `false`.
 */
fun assertFalse(actual: Boolean) {
    if (actual) {
        throw java.lang.IllegalArgumentException("Expected <false>, actual <$actual>")
    }
}

/**
 * Reads words from [filename], returning a list of the words.
 */
fun readWordsFromFile(filename: String): List<String> =
    File("data/dictionaries/$filename").readLines()

/**
 * Keeps prompting the user for a valid integer into receiving one, which it returns.
 */
fun readInt(): Int {
    var result: Int? = null
    while (result == null) {
        print("Enter a valid integer: ")
        result = readlnOrNull()?.toIntOrNull()
    }
    return result
}
