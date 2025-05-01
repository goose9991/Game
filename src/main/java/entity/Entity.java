package entity;

import org.Game.GamePanel;
import org.Game.GameVariables;
import org.Game.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//root character class
public class Entity implements GameVariables{
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

    public boolean invincible = false;
    public int invincibleCounter;
    public BufferedImage image, image2, image3;
    public boolean collision = false;
    
    // CHARACTER STATUS
    public int maxLife;
    public int life;

    public Entity(GamePanel gP)
    {
        this.gP = gP;
    }

    public void update(){

        setAction();

        collisionOn = false;
        gP.cChecker.checkTile(this);
        gP.cChecker.checkEntity(this, gP.monster);
        boolean contactPlayer = gP.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer ){
            if(!gP.player.invincible){
                gP.player.life--;
                gP.player.invincible = true;
            }
        }
        if(!collisionOn){
            switch(direction){
                case "up":
                    worldY -= (int)speed;
                    break;
                case "down":
                    worldY += (int)speed;
                    break;
                case "left":
                    worldX -= (int)speed;
                    break;
                case "right":
                    worldX += (int)speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter > GameVariables.FPS/5){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void setAction(){}
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imageName +".png"));
            image = uTool.scaleImage(image, tileSize, tileSize);

        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gP.player.worldX + gP.player.screenX;
        int screenY = worldY - gP.player.worldY + gP.player.screenY;

        if(     worldX + tileSize > gP.player.worldX - gP.player.screenX &&
                worldX - tileSize < gP.player.worldX + gP.player.screenX &&
                worldY + tileSize > gP.player.worldY - gP.player.screenY &&
                worldY - tileSize < gP.player.worldY + gP.player.screenY){

            switch (direction){
                case "up":
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1) {
                        image = left1;
                    }
                    if(spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1) {
                        image = right1;
                    }
                    if(spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);

        }


    }
}
