package homework10

import java.lang.Thread.sleep

//import mu.KotlinLogging

class ArtificialPlayer(ui: UserInterface) : Player(ui) {
    // Source of real Wordle word list: https://github.com/tabatkins/wordle-list
    private val wordList = readWordsFromFile("input-words.txt")
    private val constraints: MutableList<(String) -> Boolean> = mutableListOf()
    //private val logger = KotlinLogging.logger {}


    override fun startNewGame() {
        constraints.clear()
    }

    override fun generateGuess(): String {
        // Pause for one second so the play doesn't move too fast.
        sleep(1000L)

        // Return the first word that satisfies all the constraints.
        // You may want to write a helper method.
        return wordList.first { satisfiesConstraints(it) }
    }

    private fun satisfiesConstraints(word: String): Boolean {
        var isValid = true
        for (predicate in constraints) {
            if (!predicate(word)) {
                isValid = false
            }
        }
        return isValid
    }

    override fun incorporateFeedback(guess: String, matchString: String) {
        // Create new constraints based on our last guess and the resulting
        // matchString. For example, if we had:
        //     guess:       WORLD
        //     matchString: #+...
        // We would add 6 predicates to constraints:
        // 1. Return true for words that start with "W".
        // 2. Return true for words where "O" is in the word but is not
        //    the second character.
        // 3. Return true for words that do not contain "R".
        // 4. Return true for words that do not contain "L".
        // 5. Return true for words that do not contain "D".

        // As described in INSTRUCTIONS.md, the rules are more complicated
        // if the guess has repeated letters.
        for (i in matchString.indices) {
            val charOccur = guess.count { it == guess[i] }
            if (matchString[i] == '*') {
                constraints.add {
                    it[i] == guess[i]
                }
            } else if (matchString[i] == '+') {
                constraints.add {
                    it.contains(guess[i]) && it[i] != guess[i]
                }
            } else {
                if (charOccur <= 1) {
                    constraints.add {
                        !it.contains(guess[i])
                    }
                } else {
                    constraints.add {
                        it.count { it == guess[i] } < charOccur
                    }
                }
            }
        }

    }
}