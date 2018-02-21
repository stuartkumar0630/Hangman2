package com.company;

import java.util.Random;

class  DefaultWordsource implements WordDatasource {

    private static String[] counties = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
            "East Lothian", "Highland", "Dumfries and Galloway",
            "Renfrewshire", "Scottish Borders", "Perth and Kinross"};

    private static String[] countries = { "Scotland", "England", "Wales",
            "Northern Ireland", "Ireland",
            "France", "Germany", "Netherlands",
            "Spain", "Portugal", "Belgium", "Luxembourg",
            "Switzerland", "Italy", "Greece" };

    private static String[] cities = { "St Andrews", "Edinburgh", "Glasgow",
            "Kirkcaldy", "Perth", "Dundee",
            "Stirling", "Inverness", "Aberdeen",
            "Falkirk" };


    public String wordFromResponse(final String response) {
        final WordSource category = categoryFromResponse(response);
        final String word = wordFromCategory(category);
        return word;
    }

    public String wordFromCategory(final WordSource category) {

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


    public WordSource categoryFromResponse(final String response){

        switch (response){
            case "1":
                return WordSource.COUNTIES;
            case "2":
                return WordSource.COUNTRIES;
            case "3":
                return WordSource.CITIES;
            default:
                System.out.println("Please select one of the below options by entering 1, 2, or 3");
                return categoryFromResponse(response);

        }
    }

    private String getRandom(final String[] array) {
        final int rnd = new Random().nextInt(array.length);
        return array[rnd];
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
}
