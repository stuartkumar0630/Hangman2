package com.company;

interface ViewDatasource{
    int lengthOfRemainingWord(String word, String workingCopy);
    String getHint(String word, String workingCopy, int position);
    boolean lost(int mistakes, int maxMistakes);
    boolean won(String word, String workingCopy);
}