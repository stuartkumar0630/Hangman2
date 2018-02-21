package com.company;

interface ViewDelegate{

    void showStartOfGameInstructions();
    void showHint(String word, String workingCopy);
    void showStartOfRoundInstructions();

    void displayWon();
    void displayLost(String word);
    void displayEndOfRound(final int mistakes, final String letter, final String workingCopy);
}
