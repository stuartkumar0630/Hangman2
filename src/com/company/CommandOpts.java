package com.company;

public class CommandOpts {

    public int maximumNumberOfGuesses;
    public int maximumNumberOfHints;

    String wordSource;

    CommandOpts(String[] args) {
        maximumNumberOfGuesses = 10;
        maximumNumberOfHints = 2;

        wordSource = "";

        for(int i = 0; i < args.length; ++i) {
            if (args[i].equals("--guesses")) {
                maximumNumberOfGuesses = Integer.parseInt(args[i+1]);
                i++;
            }
            else if (args[i].equals("--hints")) {
                maximumNumberOfHints = Integer.parseInt(args[i+1]);
                i++;
            }
            else wordSource = args[i];
        }
    }
}