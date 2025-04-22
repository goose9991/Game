package entity;

import org.Game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

//root character class
public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";
    public int actionLockCounter = 0;
    public int type; // 0 = player, 1 = npc, 2 = monster
    public String name;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    // CHARACTER STATUS
    public int maxLife;
    public int life;

    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }
}
