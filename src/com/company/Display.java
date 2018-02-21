package com.company;


class Display implements ViewDelegate {

    static ViewDatasource viewDatasource;
    Display (final ViewDatasource vd){
        this.viewDatasource = vd;
    }

    public void showStartOfGameInstructions(){

        System.out.println("  1. Counties");
        System.out.println("  2. Countries");
        System.out.println("  3. Cities");
        System.out.println("Pick a category:");
    }

    public void showHint(final String word, final String workingCopy){

        if (viewDatasource.lengthOfRemainingWord(word, workingCopy) < 3){
            System.out.println(" No more hints remaining");
        }
        else{
            final String hintLetter = viewDatasource.getHint(word, workingCopy, 0);
            System.out.println("Hint: The word contains the letter " + hintLetter);
        }
    }

    public void displayWon(){
        System.out.println("You won");
    }

    public void displayLost(final String word){
        System.out.println("You lost");
        System.out.print("The answer was " + word);
    }

    public void displayEndOfRound(final int mistakes, final String letter, final String workingCopy){
        System.out.println("You have made " + mistakes + " mistakes so far");
        System.out.println("You tried (" + letter + ")");
        System.out.println(workingCopy);
    }


    public void showStartOfRoundInstructions(){
        System.out.println("Please guess a letter");
    }

}
