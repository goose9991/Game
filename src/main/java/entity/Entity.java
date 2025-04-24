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
    Graphics2D g2;
    public int worldX, worldY;
    public double speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";
    public int actionLockCounter = 0;
    public int type; // 0 = player, 1 = npc, 2 = monster
    public String name;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    // CHARACTER STATUS
    public int maxLife;
    public int life;

    public Entity(GamePanel gP) {
        this.gP = gP;
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            image = uTool.scaleImage(image, gP.tileSize, gP.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void setAction(){}
    public void update()
    {
        setAction();
        collisionOn = false;
        gP.cChecker.checkTile(this);

        if (collisionOn == false)
        {
            // Allows Player/Entity to move if collision is off
            switch(direction)
            {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "right": worldX -= speed; break;
                case "left": worldX += speed; break;
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int screenX = worldX - gP.player.worldX + gP.player.screenX;
        int screenY = worldY - gP.player.worldY + gP.player.screenY;

        if (worldX + gP.tileSize > gP.player.worldX - gP.player.screenX &&
                worldX - gP.tileSize < gP.player.worldX + gP.player.screenX &&
                worldY + gP.tileSize > gP.player.worldY - gP.player.screenY &&
                worldY - gP.tileSize < gP.player.worldY + gP.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, gP.tileSize, gP.tileSize, null);
        }
    }
}