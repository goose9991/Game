package org.Game;

import entity.Entity;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI implements GameVariables{

    GamePanel gp;
    Graphics2D g2;

    Font arial_40;

    BufferedImage heart_full, heart_half, heart_blank;

    double playtime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp)
    {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        //create HUD Object
        Entity heart = new OBJ_Heart(gp);

        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // TIME
        if (gp.gameState == playState)
        {
            playtime += (double)1/FPS; // calculates playtime based on fps
        }
        g2.drawString("Time: " + dFormat.format(playtime), tileSize*11, 65);

        // PLAYER HEALTH
        drawPlayerLife();

        // PAUSE SCREEN
        if (gp.gameState == pauseState)
        {
            drawPauseScreen();
        }
    }
    public void drawPauseScreen()
    {
        String text = "PAUSED";
        int x = screenWidth/2 - tileSize*2;
        int y = screenHeight/2;

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
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i< gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += tileSize;
        }

    }
}
