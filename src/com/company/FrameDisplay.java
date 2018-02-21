package com.company;

import java.awt.*;
import javax.swing.*;


public class FrameDisplay extends JFrame implements ViewDelegate
{

    final JLabel label = new JLabel( "          ");
    private static ViewDatasource viewDatasource;

    FrameDisplay(ViewDatasource vd) {

        viewDatasource = vd;

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        panel1.add(label);


        getContentPane().add(panel1, BorderLayout.CENTER);
        getContentPane().add(panel2, BorderLayout.PAGE_END);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    @Override
    public void showStartOfGameInstructions() {
        label.setText("Please select one of the below options by entering 1, 2, or 3");
    }

    @Override
    public void showHint(String word, String workingCopy) {
        if (viewDatasource.lengthOfRemainingWord(word, workingCopy) < 3){
            label.setText(" No more hints remaining");
        }
        else{
            final String hintLetter = viewDatasource.getHint(word, workingCopy, 0);
            label.setText("Hint: The word contains the letter " + hintLetter);
        }
    }

    @Override
    public void showStartOfRoundInstructions() {
        label.setText("  1. Counties -  2. Countries - 3. Cities - Pick a category:");
    }

    @Override
    public void displayWon() {
        label.setText("You won");
    }

    @Override
    public void displayLost(String word) {
        label.setText("You lost - The answer was " + word);
    }

    @Override
    public void displayEndOfRound(int mistakes, String letter, String workingCopy) {
        label.setText("You have made \" + mistakes + \" mistakes so far - You tried (" + letter + ") - workingCopy");
    }

}