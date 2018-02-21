package com.company;

interface ViewDelegate{
    void showStartOfGameInstructions();
    void showHint(String word, String workingCopy);
    void display(String word, String workingCopy, int mistakes, String letter, int maxMistakes);
    void showStartOfRoundInstructions();
}
