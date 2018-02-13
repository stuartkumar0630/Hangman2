package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {

    public String targetWord;
    public int numberOfGuessesTaken;
    public int guessesRemaining;
    public int numberOfHintsRemaining;

    ArrayList<Character> targetCharactersRemaining;
    ArrayList<Character> targetCharactersMissing;

    public Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public GameState(String target, int g, int h) {
        this.targetWord = target;
        targetCharactersMissing = new ArrayList<Character>();
        targetCharactersRemaining = new ArrayList<Character>();

        for(int i = 0; i < target.length(); ++i) {
            if (!targetCharactersMissing.contains(Character.toLowerCase(target.charAt(i))))
                targetCharactersMissing.add(Character.toLowerCase(target.charAt(i)));
        }
        //System.out.println(missing);

        this.numberOfGuessesTaken = 0;
        this.guessesRemaining = g;
        this.numberOfHintsRemaining = h;
    }

    void showWord() {
        for (int i = 0; i < targetWord.length(); ++i) {
            if (targetCharactersRemaining.contains(targetWord.charAt(i))) {
                System.out.print(targetWord.charAt(i));
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
        // System.out.println(missing);
    }

    boolean guessLetter() {
        int i;
        char letter;

        System.out.print("Guess a letter or word (? for a hint): ");

        String str = sc.next().toLowerCase();

        if (str.length() > 1) {
            if (str==targetWord) {
                targetCharactersMissing.clear();
                return true;
            } else return false;
        }

        letter = str.charAt(0);

        if (letter == '?') {
            hint();
            return false;
        }

        for(i = 0; i < targetCharactersMissing.size(); ++i) { // Loop over the not got
            if (targetCharactersMissing.get(i) == letter) {
                targetCharactersMissing.remove(i);
                targetCharactersRemaining.add(letter);
                numberOfGuessesTaken++;
                return true;
            }
        }

        numberOfGuessesTaken++; // One more guess
        guessesRemaining--;
        return false;
    }

    boolean won() {

        if (targetCharactersMissing.size() == 0)
            return true;
        else
            return false;
    }

    boolean lost() {

        if (targetCharactersMissing.size() > 0 && guessesRemaining == 0)
            return true;
        else return false;
    }

    void hint() {
        if (numberOfHintsRemaining == 0) {
            System.out.println("No more hints allowed");
        }

        System.out.print("Try: ");
        System.out.println(targetCharactersMissing.get((int)(Math.random()*targetCharactersMissing.size())));
    }
}