package homework10

import kotlin.system.exitProcess

class TextUserInterface : UserInterface {
    override fun readGuess(): String {
        // This should prompt the user for a guess, input it,
        // convert it to all capital letters, and return it.
        // You do not need to validate it.
        println("Enter guess:")
        return readln().uppercase()
    }

    override fun showError(msg: String) {
        // This should print the error message to System.err.
        System.err.println(msg)
    }

    override fun showFeedback(guess: String, matchString: String) {
        // This should print the guess and matchString.
        println(guess)
        println(matchString)
    }

    override fun showLoss(secretWord: String) {
        // This should tell the user what the secret word is.
        println("The secret word was $secretWord")
    }

    override fun showWin(numGuesses: Int) {
        // Print the appropriate entry from the list
        // WordleGame.WINNING_RESPONSES. For example, if the user guesses
        // the secret word correctly on their, this will print "Genius".
        // If they guess it on their 6th move, this will print "Phew".
        println(WordleGame.WINNING_RESPONSES[numGuesses - 1])
    }
}

fun main(args: Array<String>) {
    val ui = TextUserInterface()
    try {
        val player = if (isHumanPlayer(args)) HumanPlayer(ui) else ArtificialPlayer(ui)
        playGame(player, ui)
    } catch (e: IllegalArgumentException) {
        System.err.println(e.message)
        exitProcess(1)
    }
}
