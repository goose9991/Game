package org.Game;

import object.OBJ_Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

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
        SuperObject heart = new OBJ_Heart(gp);
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
        if (gp.gameState == gp.playState)
        {
            playtime += (double)1/gp.FPS; // calculates playtime based on fps
        }
        g2.drawString("Time: " + dFormat.format(playtime), gp.tileSize*11, 65);

        // PLAYER HEALTH
        drawPlayerLife();

        // PAUSE SCREEN
        if (gp.gameState == gp.pauseState)
        {
            drawPauseScreen();
        }
    }
    public void drawPauseScreen()
    {
        String text = "PAUSED";
        int x = gp.screenWidth/2 - gp.tileSize*2;
        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }
    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //draw current life
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i< gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }
}
