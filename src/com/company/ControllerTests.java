package com.company;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ControllerTests {

    @Test
    public void doneTest(){
        final Controller comp = new Controller();
        //assertEquals(comp.applyGuess("-anana", "-anana", "b"), "banana");
        assertEquals(comp.done(true, true), true);
    }

    @Test
    public void lostTest(){
        final Controller comp = new Controller();
        assertEquals(comp.lost(10, 5), true);
    }

    @Test
    public void wonTest(){
        final Controller comp = new Controller();
        assertEquals(comp.won("yellow", "yellow"), true);
    }

    @Test
    public void updateMistakesTest(){
        final Controller comp = new Controller();
        assertEquals(comp.updateMistakes(6, "yellow", "yellow"), 7);
    }

    @Test
    public void applyGuessTest(){
        final Controller comp = new Controller();
        assertEquals(comp.applyGuess("banana", "-anana", "b", 0), "banana");
    }

    @Test
    public void isHintTest(){
        final Controller comp = new Controller();
        assertEquals(comp.isHint("?"), true);
        assertEquals(comp.isHint("j"), false);
    }

    @Test
    public void lengthOfRemainingWordTest(){
        final Controller comp = new Controller();
        assertEquals(comp.lengthOfRemainingWord("hello", "hello"), 0);
        assertEquals(comp.lengthOfRemainingWord("hello", "-ello"), 1);
        assertEquals(comp.lengthOfRemainingWord("hello", "hell-"), 1);
        assertEquals(comp.lengthOfRemainingWord("hello", "-e-l-"), 3);
        assertEquals(comp.lengthOfRemainingWord("hello", "-"), 5);
        assertEquals(comp.lengthOfRemainingWord("h", "-"), 1);

    }

    @Test
    public void getHintTest(){
        final Controller comp = new Controller();
        assertEquals(comp.getHint("idru", "-dru", 0), "i");
    }
}
