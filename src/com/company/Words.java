package com.company;

import java.io.*;
import java.util.ArrayList;

class Words {

    private static String[] counties = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
            "East Lothian", "Highland", "Dumfries and Galloway",
            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };
    private static String[] countries = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
            "France", "Germany", "Netherlands", "Spain", "Portugal",
            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };
    private static String[] cities = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };

    private static ArrayList<String> customWords;

    static String randomWord(final int category) {
        if (category == 1) {
            return counties[(int) (Math.random() * 9)];
        }
        if (category == 2) {
            return countries[(int) (Math.random() * 15)];
        }
        return cities[(int)(Math.random()*10)];
    }


    static String randomWord(final String wordSourceURL) {
        String line;
        customWords = new ArrayList<String>();

        try {
            final FileReader file = new FileReader(wordSourceURL);
            final BufferedReader reader = new BufferedReader(file);
            while((line = reader.readLine()) != null) {
                customWords.add(line);
            }
            return customWords.get((int)(Math.random()*customWords.size()));
        } catch(FileNotFoundException e) {
            System.out.println("File error");
            return "";
        } catch(IOException e) {
            System.out.println("IO error");
            return "";
        }
    }
}
