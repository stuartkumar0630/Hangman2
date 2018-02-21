package com.company;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WordDatasourceTests {

    @Test
    public void startingWordTest() {
        WordDatasource wordDatasource = new DefaultWordsource();
        assertEquals(wordDatasource.startingWorkingWord("-", 0), "");
        assertEquals(wordDatasource.startingWorkingWord("-", 5), "-----");
    }

    @Test
    public void categoryTest() {
        WordDatasource wordDatasource = new DefaultWordsource();
        assertNotEquals(wordDatasource.wordFromResponse("3"), "Scotland");
    }


}
