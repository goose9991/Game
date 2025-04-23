package org.Game;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial_40;

    double playtime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp)
    {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2)
    {
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // TIME
        playtime += (double)1/gp.FPS;
        g2.drawString("Time: " + dFormat.format(playtime), gp.tileSize*11, 65);




    }
}
