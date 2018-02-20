package com.company;

interface ViewDatasource{
    int lengthOfRemainingWord(String word, String workingCopy);
    String getHint(String word, String workingCopy, int position);
    boolean lost(int mistakes, int maxMistakes);
    boolean won(String word, String workingCopy);
}

 class Display implements ViewDelegate {

    static ViewDatasource viewDatasource;

    Display (ViewDatasource vd){
        this.viewDatasource = vd;
    }

    public void showStartOfGameInstructions(){

        System.out.println("  1. Counties");
        System.out.println("  2. Countries");
        System.out.println("  3. Cities");
        System.out.println("Pick a category:");
    }

    public void showHint(String word, String workingCopy){

        if (viewDatasource.lengthOfRemainingWord(word, workingCopy) < 3){
            System.out.println(" No more hints remaining");
        }
        else{
            String hintLetter = viewDatasource.getHint(word, workingCopy, 0);
            System.out.println("Hint: The word contains the letter " + hintLetter);
        }
    }

    public void display(String word, String workingCopy, int mistakes, String letter, int maxMistakes){

        boolean won = viewDatasource.won(word, workingCopy);
        boolean lost = viewDatasource.lost(mistakes, maxMistakes);


        if (won){
            System.out.println("You won");
        }
        else if (lost){
            System.out.println("You lost");
            System.out.print("The answer was " + word);
        }else {
            System.out.println("You have made " + String.valueOf(mistakes) + " mistakes so far");
            System.out.println("You tried (" + letter + ")");
            System.out.println(workingCopy);


        }

        System.out.print(System.lineSeparator());
    }

    public void showStartOfRoundInstructions(){
        System.out.println("Please guess a letter");
    }

}
