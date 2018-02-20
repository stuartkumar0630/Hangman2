package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void doneTest(){
        Compromise comp = new Compromise();
        //assertEquals(comp.applyGuess("-anana", "-anana", "b"), "banana");
        assertEquals(comp.done(true, true), true);
    }

    @Test
    public void lostTest(){
        Compromise comp = new Compromise();
        assertEquals(comp.lost(10, 5), true);
    }

    @Test
    public void wonTest(){
        Compromise comp = new Compromise();
        assertEquals(comp.won("yellow", "yellow"), true);
    }

    @Test
    public void updateMistakesTest(){
        Compromise comp = new Compromise();
        assertEquals(comp.updateMistakes(6, "yellow", "yellow"), 7);
    }

    @Test
    public void applyGuessTest(){
        Compromise comp = new Compromise();
        assertEquals(comp.applyGuess("banana", "-anana", "b", 0), "banana");
    }

    @Test
    public void isHintTest(){
        Compromise comp = new Compromise();
        assertEquals(comp.isHint("?"), true);
        assertEquals(comp.isHint("j"), false);
    }

    @Test
    public void lengthOfRemainingWordTest(){
        Compromise comp = new Compromise();
        assertEquals(comp.lengthOfRemainingWord("hello", "hello"), 0);
        assertEquals(comp.lengthOfRemainingWord("hello", "-ello"), 1);
        assertEquals(comp.lengthOfRemainingWord("hello", "hell-"), 1);
        assertEquals(comp.lengthOfRemainingWord("hello", "-e-l-"), 3);
        assertEquals(comp.lengthOfRemainingWord("hello", "-"), 5);
        assertEquals(comp.lengthOfRemainingWord("h", "-"), 1);

    }

    @Test
    public void getHintTest(){
        Compromise comp = new Compromise();
        assertEquals(comp.getHint("idru", "-dru", 0), "i");
    }
}
