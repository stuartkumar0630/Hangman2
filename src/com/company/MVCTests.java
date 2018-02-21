package com.company;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The purpose of these tests is to provide a reusable
 * set of tests so that if new objects are used to fulfill
 * an interface its performance can be examined
 *
 */

public class MVCTests {

    // WordDatasource Tests

    @Test
    public void startWorkingWordTest() {
        WordDatasource wordDatasource = new DefaultWordsource();
        assertEquals(wordDatasource.startingWorkingWord("-", 0), "");
        assertEquals(wordDatasource.startingWorkingWord("-", 5), "-----");
    }

    @Test
    public void wordFromResponseTest() {
        WordDatasource wordDatasource = new DefaultWordsource();
        assertNotNull(wordDatasource.wordFromResponse("2"));
    }

    // ViewDatasourceTests

    @Test
    public void getHintTest() {
        ViewDatasource viewDatasource = new Controller();
        assertEquals(viewDatasource.getHint("Software", "Softwar-", 0), "e");
    }

    @Test
    public void lengthOfRemainingWordTest() {
        ViewDatasource viewDatasource = new Controller();
        assertEquals(viewDatasource.lengthOfRemainingWord("Edwin", "--win"), 2);
    }

    // View DelegateTests - No clear output to test


}
