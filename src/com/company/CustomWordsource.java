package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CustomWordsource implements WordDatasource{

    String filePath;

    CustomWordsource (String fp){
        this.filePath = fp;
    }

    private  String randomWordFromFileAtPath(String wordsource) {
        String line;
        ArrayList<String> customwords = new ArrayList<String>();

        try {
            FileReader file = new FileReader(wordsource);
            BufferedReader reader = new BufferedReader(file);
            while((line = reader.readLine()) != null) {
                customwords.add(line);
            }
            return customwords.get((int)(Math.random()*customwords.size()));
        } catch(FileNotFoundException e) {
            System.out.println("File error");
            return "";
        } catch(IOException e) {
            System.out.println("IO error");
            return "";
        }
    }

    public String startingWorkingWord(final String repeatingCharacter, final int lengthRemaining){

        if (lengthRemaining==0) {
            return "";
        }

        if (lengthRemaining - 1 == 0) {
            return repeatingCharacter;
        }else {
            return startingWorkingWord(repeatingCharacter + "-", lengthRemaining-1);
        }
    }

    public String wordFromResponse(String response) {
        return randomWordFromFileAtPath(filePath);
    }
}
