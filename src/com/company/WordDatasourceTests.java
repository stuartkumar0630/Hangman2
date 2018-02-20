package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WordDatasourceTests {

    @Test
    public void startingWordTest(){
        WordDatasource wd = new DefaultWordsource();
        assertEquals(wd.startingWorkingWord("-", 0), "");
        assertEquals(wd.startingWorkingWord("-", 5), "-----");
    }

    @Test
    public void categoryTest(){
        WordDatasource wd = new DefaultWordsource();
        assertNotEquals(wd.wordFromCategory(WordSource.CITIES), "Scotland");
    }


}
