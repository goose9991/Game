package entity;

import org.Game.GamePanel;
import org.Game.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//root character class
public class Entity {
    GamePanel gP;
    public int worldX, worldY;
    public double speed;
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

    public Entity(GamePanel gP)
    {
        this.gP = gP;
    }
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imageName +".png"));
            image = uTool.scaleImage(image, gP.tileSize, gP.tileSize);

        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
