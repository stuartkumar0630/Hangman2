package com.company;

import java.util.Scanner;
import static java.lang.StrictMath.max;

public class Controller implements ViewDatasource {

    // Response for handling view related activity
    private static ViewDelegate viewDelegate;
    // Responsible for sourcing words to be guessed
    final WordDatasource wordDatasource = new DefaultWordsource();;

    Controller(){

    }

    /**
     *
     * Starts game
     *
     * Displays start of game instructions and asks user to chose category, then starts the guessing
     * Also displays end of round status
     *
     */

    public void play(){

        viewDelegate = new FrameDisplay(this);

        viewDelegate.showStartOfGameInstructions();

        final String response = userResponseToInstructions();
        final String word = wordDatasource.wordFromResponse(response);

        guess(word, wordDatasource.startingWorkingWord("-", word.length()), 0);

    }

    /**
     * Main game loop
     *
     * Asks user for response, processes response, and recurse
     *
     * @param word
     * @param workingCopy
     * @param mistakes
     */

     void guess(final String word, String workingCopy, final int mistakes){

        final boolean won = won(word, workingCopy);

        final int maxMistakes = word.length()/2;

        final boolean lost = lost(mistakes, maxMistakes);


        if (done(won, lost) == false){

            viewDelegate.showStartOfRoundInstructions();
            String letter = userResponseToInstructions();

            if (isHint(letter)){
                viewDelegate.showHint(word, workingCopy);
                letter = userResponseToInstructions();
            }

            final String newWorkingCopy = applyGuess(word, workingCopy, letter, 0);
            final int newMistakes = updateMistakes(mistakes, workingCopy, newWorkingCopy);

            displayGameStateToUser(word, newMistakes, letter, workingCopy, maxMistakes);
            guess(word, newWorkingCopy, newMistakes);

        }



    }

    /**
     * Applies guess to working copy and returns a new working copy
     *
     * E.g (word: banana, workingCopy: b----, letter: a, position: 0) -> ba-a-a
     *
     * @param word
     * @param workingCopy
     * @param letter
     * @param position
     * @return
     */

     String applyGuess(final String word, final String workingCopy, final String letter, final int position){

        final String remainingWord = word.substring(position, word.length());

        if (remainingWord.contains(letter)){
            final int index = word.indexOf(letter, position);

            final String newWorkingCopy = workingCopy.substring(0, index) + letter + workingCopy.substring(index + 1, word.length());

            return applyGuess(word, newWorkingCopy, letter, position + 1);
        }

        return workingCopy;
    }

    // Self documenting functions

    void displayGameStateToUser(String word, int mistakes, String letter, String workingCopy, int maxMistakes){

        final boolean won = won(word, workingCopy);
        final boolean lost = lost(mistakes, maxMistakes);

        if (won){
            viewDelegate.displayWon();
        }else if (lost){
            viewDelegate.displayLost(word);
        }else {
            viewDelegate.displayEndOfRound(mistakes, letter, workingCopy);
        }
    }

    String userResponseToInstructions() {

        final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        final String response = scanner.next();

        if (response.length() != 1){
            System.out.println("Please give a one character answer");
            return userResponseToInstructions();
        }

        return response;
    }


    public String getHint(final String word, final String workingCopy, final int position){

        final String hintLetter = word.substring(max(0, position - 1), position);

        if (workingCopy.contains(hintLetter)){
            return getHint(word, workingCopy, position + 1);
        }

        return hintLetter;
    }

    int updateMistakes(final int mistakes, final String oldWorkingCopy, final String newWorkingCopy){

        if (oldWorkingCopy.equals(newWorkingCopy)){
            return mistakes + 1;
        }else {
            return mistakes;
        }
    }

    public int lengthOfRemainingWord(final String word, final String workingCopy){

        final String guessedWord = workingCopy.replace("-", "");
        return word.length() - guessedWord.length();
    }

    boolean done(final boolean won, final boolean lost){ return won || lost; }

    boolean lost(final int mistakes, final int maxMistakes) {
        return mistakes > maxMistakes;
    }

    boolean won(final String word, final String workingCopy){
        return word.equals(workingCopy);
    }

    boolean isHint(final String response){

        if (response.charAt(0) == '?') {
            return true;
        }
        return false;
    }
}

