package org.Game;

import entity.Entity;
import object.OBJ_Crown;
import object.OBJ_Heart;
import object.OBJ_Skull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI implements GameVariables{

    GamePanel gp;
    Graphics2D g2;

    Font arial_40;

    BufferedImage heart_full, heart_half, heart_blank, skullImg, crownImg;

    double playtime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp)
    {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        //create HUD Objects, also Polymorphic
        Entity heart = new OBJ_Heart(gp);
        Entity skull = new OBJ_Skull(gp);
        Entity crown = new OBJ_Crown(gp);

        // UI Images
        crownImg = crown.image;
        skullImg = skull.image;
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;



    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        drawPlayState(); // Draws the time, and calls the PLAYER HEALTH, PLAYER KILL COUNT, and Mute drawings

        // Draws Special Screens based on gameState
        switch(gp.gameState)
        {
            case pauseState: drawPauseScreen(); break;
            case gameOverState: drawGameOverScreen(); break;
            case victoryState: drawVictoryScreen(); break;
            default: break;
        }
    }

    public void drawPlayState()
    {
        // TIME
        if (gp.gameState == playState) {playtime += (double)1/FPS;} // calculates playtime based on fps
        g2.drawString("Time: " + dFormat.format(playtime), screenWidth - tileSize*5, 68); // Creates Timer, Top Right

        // PLAYER HEALTH
        drawPlayerLife();

        // Player Kill Count
        drawKillCount();

        if(Sound.mute && gp.gameState == playState){
            drawMuteSymbol();
        }

    }
    public void drawKillCount()
    {
        int x = tileSize/2;
        int y = 2*tileSize;

        g2.drawImage(skullImg, x,y,null);
        g2.drawString(" X " + gp.player.killCount, x*3, 135);
    }
    public void drawMuteSymbol(){
        String text = "MUTE";
        int x = screenWidth/2 + tileSize*5;
        int y = screenHeight/2 + tileSize*5;

        g2.drawString(text,x,y);
    }

    public void drawGameOverScreen()
    {
        String text = "Game Over..";
        int x = screenWidth/2 - tileSize*2;
        int y = screenHeight/2;

        g2.drawString(text,x,y);
    }
    public void drawPauseScreen()
    {
        String text = "PAUSED";
        int x = screenWidth/2 - tileSize*2;
        int y = screenHeight/2;

        g2.drawString(text,x,y);
    }
    public void drawVictoryScreen()
    {
        String text = "VICTORY!!";
        int x = screenWidth/2 - tileSize*2;
        int y = screenHeight/2;


        g2.drawImage(crownImg,700,350,null);
        g2.drawString(text,x,y);
    }
    public void drawPlayerLife(){
        int x = tileSize/2;
        int y = tileSize/2;
        int i = 0;

        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += tileSize;
        }

        //RESET
        x = tileSize/2;
        y = tileSize/2;
        i = 0;

        //draw current life
        while(i < gp.player.getLife()){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i< gp.player.getLife()){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += tileSize;
        }

    }
}
