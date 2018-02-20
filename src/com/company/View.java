package com.company;

import java.util.ArrayList;

class View {

    void showWin(int numberOfGuessesTaken){
        System.out.println("Well done!");
        System.out.println("You took " + numberOfGuessesTaken + " guesses");
    }

    void showLoss(String targetWord){
        System.out.println("You lost! The word was " + targetWord);
    }

    void showCorrectGuess(String targetWord, int guessesRemaining, ArrayList<Character> targetCharactersRemaining){

        System.out.println("Guesses remaining: " + guessesRemaining);
        System.out.println("Good guess!");
        showGuessedLettersOfWord(targetWord, targetCharactersRemaining);


    }

    void showIncorrectGuess(String targetWord, int guessesRemaining, ArrayList<Character> targetCharactersRemaining){

        System.out.println("Guesses remaining: " + guessesRemaining);
        System.out.println("Wrong guess!");
        showGuessedLettersOfWord(targetWord, targetCharactersRemaining);
    }

    void showGameInstructions(){

        System.out.println("  1. Counties");
        System.out.println("  2. Countries");
        System.out.println("  3. Cities");
        System.out.println("Pick a category:");
    }

    void showRoundInstructions(){
        System.out.print(System.lineSeparator());
        System.out.println("Guess a letter or word (? for a hint): ");
    }

    void showHint(int numberOfHintsRemaining, ArrayList<Character> targetCharactersMissing){
        if (numberOfHintsRemaining == 0) {
            System.out.println("No more hints allowed");
        }

        System.out.print("Try: ");
        System.out.println(targetCharactersMissing.get((int)(Math.random()*targetCharactersMissing.size())));
    }


    private void showGuessedLettersOfWord(String targetWord, ArrayList<Character> targetCharactersRemaining) {

        for (int i = 0; i < targetWord.length(); ++i) {
            if (targetCharactersRemaining.contains(targetWord.toLowerCase().charAt(i))) {
                System.out.print(targetWord.charAt(i));
            } else {
                System.out.print("-");
            }
        }
        System.out.print(System.lineSeparator());
    }
}
