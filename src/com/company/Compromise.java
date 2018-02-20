package com.company;

import java.util.*;

import static java.lang.StrictMath.max;

public class Compromise {

    enum WordSource{
        COUNTRIES, COUNTIES, CITIES;
    }

    private static String[] counties = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
            "East Lothian", "Highland", "Dumfries and Galloway",
            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };
    private static String[] countries = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
            "France", "Germany", "Netherlands", "Spain", "Portugal",
            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };
    private static String[] cities = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };

    public static void main(String[] args){


        WordSource category = categoryFromResponse();
        String word = wordFromCategory(category);

        guess(word, startingWorkingWord("-", word.length()), 0);

    }


    static void guess(String word, String workingCopy, int mistakes){

        boolean won = won(word, workingCopy);

        int maxMistakes = word.length()/2;

        boolean lost = lost(mistakes, maxMistakes);


        if (done(won, lost) == false){

            System.out.println("Please guess a letter");
            String letter = userResponseToInstructions();

            if (isHint(letter)){
                showHint(word, workingCopy);
                letter = userResponseToInstructions();
            }

            String workingCop = applyGuess(word, workingCopy, letter, 0);
            int mistak = updateMistakes(mistakes, workingCopy, workingCop);

            display(word, workingCop, mistak, letter, maxMistakes);
            guess(word, workingCop, mistak);

        }

    }

    static String wordFromCategory(WordSource category){

        switch (category){
            case CITIES:
                return getRandom(cities);
            case COUNTIES:
                return getRandom(counties);
            case COUNTRIES:
                return getRandom(countries);
            default:
                return null;
        }
    }

    static WordSource categoryFromResponse(){

        showStartOfGameInstructions();
        String response = userResponseToInstructions();

        switch (response){
            case "1":
                return WordSource.COUNTIES;
            case "2":
                return WordSource.COUNTRIES;
            case "3":
                return WordSource.CITIES;
            default:
                System.out.println("Please select one of the below options by entering 1, 2, or 3");
                return categoryFromResponse();

        }
    }


    static void display(String word, String workingCopy, int mistakes, String letter, int maxMistakes){

        boolean won = won(word, workingCopy);
        boolean lost = lost(mistakes, maxMistakes);


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

    static void showStartOfGameInstructions(){

        System.out.println("  1. Counties");
        System.out.println("  2. Countries");
        System.out.println("  3. Cities");
        System.out.println("Pick a category:");
    }

    /**
     * TODO: make sure that not only first instance of letter in word
     * @param word
     * @param workingCopy
     * @param letter
     * @return
     */

    static String applyGuess(String word, String workingCopy, String letter, int position){

        String remainingWord = word.substring(position, word.length());

        if (remainingWord.contains(letter)){
            int index = word.indexOf(letter, position);

            String newWorkingCopy = workingCopy.substring(0, index) + letter + workingCopy.substring(index + 1, word.length());

            return applyGuess(word, newWorkingCopy, letter, position + 1);
        }

        return workingCopy;
    }

    static String startingWorkingWord(String s, int lengthRemaining){

        if (lengthRemaining - 1 == 0){
            return s;
        }else{
            return startingWorkingWord(s + "-", lengthRemaining-1);
        }
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

    static boolean lost(int mistakes, int maxMistakes) {
        return mistakes > maxMistakes;
    }

    static boolean won(String word, String workingCopy){
        return word.equals(workingCopy);
    }

     static String userResponseToInstructions() {

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        String response = sc.next();

        if (response.length() != 1){
            System.out.println("Please give a one character answer");
            return userResponseToInstructions();
        }

        return response;
    }

    static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    static boolean isHint(String response){

        if (response.charAt(0) == '?') {
            return true;
        }
        return false;
    }

    static int lengthOfRemainingWord(String word, String workingCopy){

        String guessedWord = workingCopy.replace("-", "");
        return word.length() - guessedWord.length();
    }

    static String getHint(String word, String workingCopy, int position){

        // Precondition
        String hintLetter = word.substring(max(0, position - 1), position);

        if (workingCopy.contains(hintLetter)){
            return getHint(word, workingCopy, position + 1);
        }

        return hintLetter;
    }


    static void showHint(String word, String workingCopy){

        if (lengthOfRemainingWord(word, workingCopy) < 3){
            System.out.println(" No more hints remaining");
        }
        else{
            String hintLetter = getHint(word, workingCopy, 0);
            System.out.println("Hint: The word contains the letter " + hintLetter);
        }
    }

}

