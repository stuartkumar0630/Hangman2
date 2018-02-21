package com.company;


interface WordDatasource {

    String startingWorkingWord(String repeatingCharacter, int lengthRemaining);

    String wordFromResponse(String response);
}

