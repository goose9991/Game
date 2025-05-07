package entity;

import org.Game.GamePanel;
import org.Game.GameVariables;
import org.Game.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// Abstract/root class for monsters, npcs and player
public abstract class Entity implements GameVariables{
    public GamePanel gP;
    public int worldX, worldY;
    public double speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage upAttack1, upAttack2, downAttack1, downAttack2, leftAttack1, leftAttack2,
    rightAttack1, rightAttack2;
    public String direction = "down";
    public int actionLockCounter = 0;
    public int type; // 0 = player, 1 = npc, 2 = monster
    public String name;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public boolean invincible = false;
    public int invincibleCounter;
    public BufferedImage image, image2, image3;

    public Entity(GamePanel gP)
    {
        this.gP = gP;
    }

    // CHARACTER STATUS & Encapsulation
    public int maxLife;
    private int life;

    // Getter and Setter methods for life, as well as increment and decrement methods (damage/heal)
    public int getLife()
    {
        return life;
    }
    public void setLife(int l)
    {
        life = l;
    }
    public void damage(int d)
    {
        life -= d;
    }
    public void heal(int h){
        life += h;
    }


    public void update(){

        setAction();

        collisionOn = false;
        gP.cChecker.checkTile(this);
        gP.cChecker.checkEntity(this, gP.monster);
        boolean contactPlayer = gP.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer ){
            if(!gP.player.invincible){
                gP.player.damage(1);
                gP.player.invincible = true;
            }
        }
        if(!collisionOn){
            switch(direction){
                case "up":   {worldY -= (int)speed; break;}
                case "down": {worldY += (int)speed; break;}
                case "left": {worldX -= (int)speed; break;}
                case "right":{worldX += (int)speed; break;}
            }
        }

        spriteCounter++;
        if(spriteCounter > FPS / 3){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void setAction(){}
    public BufferedImage setup(String imageName, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imageName +".png"));
            image = uTool.scaleImage(image, width, height);

        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gP.player.worldX + GameVariables.screenX;
        int screenY = worldY - gP.player.worldY + GameVariables.screenY;

        if(     worldX + tileSize > gP.player.worldX - GameVariables.screenX &&
                worldX - tileSize < gP.player.worldX + GameVariables.screenX &&
                worldY + tileSize > gP.player.worldY - GameVariables.screenY &&
                worldY - tileSize < gP.player.worldY + GameVariables.screenY){

            switch (direction){
                case "up":
                    if(spriteNum == 1) {image = up1;}
                    if(spriteNum == 2) {image = up2;}
                    break;
                case "down":
                    if(spriteNum == 1) {image = down1;}
                    if(spriteNum == 2) {image = down2;}
                    break;
                case "left":
                    if(spriteNum == 1) {image = left1;}
                    if(spriteNum == 2) {image = left2;}
                    break;
                case "right":
                    if(spriteNum == 1) {image = right1;}
                    if(spriteNum == 2) {image = right2;}
                    break;
            }
            g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);

        }


    }
}
