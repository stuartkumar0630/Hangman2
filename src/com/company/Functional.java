package com.company;

public class Functional {

    public static void main(String[] args){
        Compromise compromise = new Compromise();
        compromise.play();
    }

    Functional (String word, String workingCopy, int mistakes){
        //Initialize again, no assignments here ;)
//        if(workingCopy.length() == 0){
//            this = new Functional(word, startingWorkingWord("-", word.length()), 0);
//        }
    }


    static Functional guess(String letter, String word, String workingCopy, int mistakes){

        boolean won = won(word, workingCopy);
        boolean lost = lost(mistakes);


        if (done(won, lost) == false){
            System.out.println("yo");
            mistakes = updateMistakes(mistakes, workingCopy, applyGuess(word, workingCopy, letter));
            workingCopy = applyGuess(word, workingCopy, letter);
        }
        display(word, workingCopy, mistakes, letter);

        return new Functional(word, workingCopy, mistakes);
    }


    static void display(String word, String workingCopy, int mistakes, String letter){

        boolean won = won(word, workingCopy);

        if (won){
            System.out.println("You won");
        }

        boolean lost = lost(mistakes);

        if (lost){
            System.out.println("You lost");
        }else {
            System.out.println("You tried (" + letter + ")");

        }

        System.out.println(workingCopy);
        System.out.print(System.lineSeparator());
    }

    /**
     * TODO: make sure that not only first instance of letter in word
     * @param word
     * @param workingCopy
     * @param letter
     * @return
     */

    static String applyGuess(String word, String workingCopy, String letter){

        if (word.contains(letter)){
            int index = word.indexOf(letter);
            String newWorkingCopy = workingCopy.replace("-", letter);
            return newWorkingCopy;
        }

        return workingCopy;
    }

    static String startingWorkingWord(String s, int lengthRemaining){

        if (lengthRemaining == 0){
            return s;
        }else{
            startingWorkingWord(s + "-", lengthRemaining-1);
        }

        return s;
    }

    static int updateMistakes(int mistakes, String oldWorkingCopy, String newWorkingCopy){

        if (oldWorkingCopy.equals(newWorkingCopy)){
            return mistakes + 1;
        }else {
            return mistakes;
        }
    }

    static boolean done(boolean won, boolean lost){
        return won || lost;
    }

    static boolean lost(int mistakes) {
        return mistakes > 5;
    }

    static boolean won(String word, String workingCopy){
        return word.equals(workingCopy);
    }
}
