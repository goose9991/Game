package entity;

import org.Game.GamePanel;
import org.Game.KeyHandler;
import org.Game.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//character class
public class Player extends Entity{


    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int standCounter = 0;
    boolean moving = false;
    int pixelCounter = 0;


    public Player(GamePanel gP, KeyHandler keyH){
        super(gP);
        this.keyH = keyH;

        screenX = gP.screenWidth/2 - (gP.tileSize/2);
        screenY = gP.screenHeight/2 - (gP.tileSize/2);

        solidArea = new Rectangle(1,1, 46, 46);

        setDefaultValues();
        getPlayerImg();
    }

    public void setDefaultValues(){
        worldX = gP.tileSize * 23;
        worldY = gP.tileSize * 21;
        speed = 4 * (60.0/gP.FPS);
        direction = "down";

        maxLife = 8;
        life = maxLife;
    }

    public void getPlayerImg(){

        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");

    }

    public void update() {
        if (!moving) {
            if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
                if (keyH.upPressed) {
                    direction = "up";
                } else if (keyH.downPressed) {
                    direction = "down";
                } else if (keyH.leftPressed) {
                    direction = "left";
                } else if (keyH.rightPressed) {
                    direction = "right";
                }

                // Reset collision flag at the start of movement decision
                collisionOn = false;

                // Check for collisions
                gP.cChecker.checkTile(this);
                int monsterIndex = gP.cChecker.checkEntity(this, gP.monster);
                contactMonster(monsterIndex);

                gP.eHandler.checkEvent();

                // Only set moving to true if there is no collision
                if (!collisionOn) {
                    moving = true;
                }
            } else {
                standCounter++;
                if (standCounter == 20) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
        }

        if (moving) {
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }

                pixelCounter += speed; // Only update if actually moved
            }

            // Animate sprite
            spriteCounter++;
            if (spriteCounter > gP.FPS / 5) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }

            // End movement after tile-sized movement
            if (pixelCounter >= 48 || collisionOn) {
                moving = false;
                pixelCounter = 0;
            }
        }

        // Invincibility timer
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > gP.FPS) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }


    public void contactMonster(int i){
        if(i != 999){

            if(!invincible){
                life -= 1;
                invincible = true;
            }

        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
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
        if(invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, screenX, screenY, null);

        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

}
