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

    public void display(final String word, final String workingCopy, final int mistakes, final String letter, final int maxMistakes){

        final boolean won = viewDatasource.won(word, workingCopy);
        final boolean lost = viewDatasource.lost(mistakes, maxMistakes);


        if (won){
            System.out.println("You won");
        }
        else if (lost){
            System.out.println("You lost");
            System.out.print("The answer was " + word);
        }else {
            System.out.println("You have made " + mistakes + " mistakes so far");
            System.out.println("You tried (" + letter + ")");
            System.out.println(workingCopy);


        }

        System.out.print(System.lineSeparator());
    }

    public void showStartOfRoundInstructions(){
        System.out.println("Please guess a letter");
    }

}
