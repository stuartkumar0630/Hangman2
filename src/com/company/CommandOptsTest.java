package com.company;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandOptsTest {

    @Test
    public void optionsTest() {
        String[] args = { "--guesses", "2", "--hints", "4", "words.txt" };
        CommandOpts opts = new CommandOpts(args);
        assertEquals(opts.maximumNumberOfGuesses, 2);
        assertEquals(opts.maximumNumberOfHints, 4);
        assertEquals(opts.wordSource, "words.txt");
    }

}