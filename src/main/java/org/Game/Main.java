package org.Game;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        //make JFrame window
        JFrame window = new JFrame();
        //set close option
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //make unresizeable
        window.setResizable(false);
        //set title
        window.setTitle("2D Adventure");

        //create GamePanel instance
        GamePanel gamePanel = new GamePanel();
        //pass it to JFrame window
        window.add(gamePanel);
        //run window JFrame
        window.pack();
        //sets window at center of screen
        window.setLocationRelativeTo(null);
        //makes window visible
        window.setVisible(true);

        //start timer
        gamePanel.startGameThread();
    }
}