package com.company;



import java.util.Scanner;

import static java.lang.StrictMath.max;

public class Controller implements ViewDatasource {

    private static ViewDelegate viewDelegate;
    final WordDatasource wordDatasource = new DefaultWordsource();;

    Controller(){

    }

    public void play(){

        viewDelegate = new Display(this);

        viewDelegate.showStartOfGameInstructions();

        final String response = userResponseToInstructions();
        final String word = wordDatasource.wordFromResponse(response);

        guess(word, wordDatasource.startingWorkingWord("-", word.length()), 0);

    }

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

            final String workingCop = applyGuess(word, workingCopy, letter, 0);
            final int mistak = updateMistakes(mistakes, workingCopy, workingCop);

            viewDelegate.display(word, workingCop, mistak, letter, maxMistakes);
            guess(word, workingCop, mistak);

        }

    }

     String applyGuess(final String word, final String workingCopy, final String letter, final int position){

        final String remainingWord = word.substring(position, word.length());

        if (remainingWord.contains(letter)){
            final int index = word.indexOf(letter, position);

            final String newWorkingCopy = workingCopy.substring(0, index) + letter + workingCopy.substring(index + 1, word.length());

            return applyGuess(word, newWorkingCopy, letter, position + 1);
        }

        return workingCopy;
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

    // Self documenting functions

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

    public boolean done(final boolean won, final boolean lost){
        return won || lost;
    }

    public boolean lost(final int mistakes, final int maxMistakes) {
        return mistakes > maxMistakes;
    }

    public boolean won(final String word, final String workingCopy){
        return word.equals(workingCopy);
    }

    boolean isHint(final String response){

        if (response.charAt(0) == '?') {
            return true;
        }
        return false;
    }
}

