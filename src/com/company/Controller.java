package com.company;

import java.util.Scanner;

public class Controller {

    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static GameState game;
    private static View view = new View();
    private static CommandOpts opts;

    public static void main(String[] args) {


        opts = new CommandOpts(args);

        view.showGameInstructions();

        String targetWord = targetWordFromWordSource(opts.wordSource);
        game = new GameState(targetWord, opts.maximumNumberOfGuesses, opts.maximumNumberOfHints);


        while(!game.won() && !game.lost()) {

            view.showRoundInstructions();

            String response = userResponseToInstructions();

            boolean correct = userResponseWasCorrect(response);
            boolean wrong = userResponseWasWrong(response);
            boolean hint = userResponseWasHint(response);

            if (hint) view.showHint(game.numberOfHintsRemaining, game.targetCharactersMissing);
            if (correct) view.showCorrectGuess(game.targetWord, game.guessesRemaining, game.targetCharactersGuessed);
            if (wrong) view.showIncorrectGuess(game.targetWord, game.guessesRemaining, game.targetCharactersGuessed);

            game.numberOfGuessesTaken++;
            game.guessesRemaining--;
        }
        endGame();
    }

    private static String targetWordFromWordSource(String wordSource){

        if (wordSource.isEmpty()){
            return Words.randomWord(sc.nextInt());
        }else{
            return Words.randomWord(opts.wordSource);
        }
    }

    private static String userResponseToInstructions() {

        String response = sc.next();
        return response.toLowerCase();
    }

    private static boolean userResponseWasWrong(String response){

        if (response.length() > 1) {
            if (response.equals(game.targetWord)) {
                game.targetCharactersMissing.clear();
                return false;
            } else {
                return true;
            }
        }

        if (userResponseWasHint(response)) return false;


        char answer = response.charAt(0);

        for(int i = 0; i < game.targetCharactersMissing.size(); i++) { // Loop over the not got
            if (game.targetCharactersMissing.get(i) == answer) {
                game.targetCharactersMissing.remove(i);
                game.targetCharactersGuessed.add(answer);
                game.numberOfGuessesTaken++;
                return false;
            }
        }



        return true;
    }

    private static boolean userResponseWasCorrect(String response){

        if (response.length() > 1) {
            if (response.equals(game.targetWord)) {
                game.targetCharactersMissing.clear();
                return true;
            } else return false;
        }

        if (userResponseWasHint(response)) return false;

        char answer = response.charAt(0);

        for(int i = 0; i < game.targetCharactersMissing.size(); i++) { // Loop over the not got
            if (game.targetCharactersMissing.get(i) == answer) {

                return true;
            }
        }

        return false;
    }



    private static boolean userResponseWasHint(String response){

        if (response.charAt(0) == '?') {
            return true;
        }

        return false;
    }


    private static void endGame(){
        if (game.won()) {
            view.showWin(game.numberOfGuessesTaken);
        } else {
            view.showLoss(game.targetWord);
        }
    }


}