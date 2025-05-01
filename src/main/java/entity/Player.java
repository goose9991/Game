package entity;

import org.Game.GamePanel;
import org.Game.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;


//character class
public class Player extends Entity{


    KeyHandler keyH;

    public int standCounter = 0;

    boolean attacking = false;
    public int timeSinceAttack = 100;
    public int attackCooldown = 100; // Determines number of frames before a new attack can happen
    boolean moving = false;
    int pixelCounter = 0;


    public Player(GamePanel gP, KeyHandler keyH){
        super(gP);
        this.keyH = keyH;

        solidArea = new Rectangle(1,1, 46, 46);

        setDefaultValues();
        getPlayerImg();
        getPlayerAttackImg();
    }

    public void setDefaultValues(){
        worldX = tileSize * 23;
        worldY = tileSize * 21;
        speed = 4 * (60.0/FPS);
        direction = "down";

        maxLife = 8;
        setLife(maxLife);
    }

    public void getPlayerImg(){

        up1 = setup("/player/joselito_up1", gP.tileSize, gP.tileSize);
        up2 = setup("/player/joselito_up2", gP.tileSize, gP.tileSize);
        down1 = setup("/player/joselito_down1", gP.tileSize, gP.tileSize);
        down2 = setup("/player/joselito_down2", gP.tileSize, gP.tileSize);
        left1 = setup("/player/joselito_left1", gP.tileSize, gP.tileSize);
        left2 = setup("/player/joselito_left2", gP.tileSize, gP.tileSize);
        right1 = setup("/player/joselito_right1", gP.tileSize, gP.tileSize);
        right2 = setup("/player/joselito_right2", gP.tileSize, gP.tileSize);

    }
    public void getPlayerAttackImg()
    {
        upAttack1 = setup("/player/joselito_attack_up1", gP.tileSize, gP.tileSize*2);
        upAttack2 = setup("/player/joselito_attack_up2",gP.tileSize, gP.tileSize*2);
        downAttack1 = setup("/player/joselito_attack_down1",gP.tileSize, gP.tileSize*2);
        downAttack2 = setup("/player/joselito_attack_down2",gP.tileSize, gP.tileSize*2);
        leftAttack1 = setup("/player/joselito_attack_left1",gP.tileSize*2, gP.tileSize);
        leftAttack2 = setup("/player/joselito_attack_left2",gP.tileSize*2, gP.tileSize);
        rightAttack1 = setup("/player/joselito_attack_right1",gP.tileSize*2, gP.tileSize);
        rightAttack2 = setup("/player/joselito_attack_right2",gP.tileSize*2, gP.tileSize);
    }
    @Override
        public void update() {

            if (attacking && (timeSinceAttack >= attackCooldown)) {attack();}
            else {
                timeSinceAttack = (timeSinceAttack >= attackCooldown) ? attackCooldown : timeSinceAttack+1;
            }

            if (!moving){
                if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.spacePressed) {
                    if (keyH.upPressed) {
                        direction = "up";
                    } else if (keyH.downPressed) {
                        direction = "down";
                    } else if (keyH.leftPressed) {
                        direction = "left";
                    } else if (keyH.rightPressed) {
                        direction = "right";
                    }
                    else if (keyH.spacePressed) {
                        attacking = timeSinceAttack >= attackCooldown;
                    }


                    // Reset collision flag at the start of movement decision
                    collisionOn = false;

                    // Check for collisions
                    gP.cChecker.checkTile(this);
                    int monsterIndex = gP.cChecker.checkEntity(this, gP.monster);
                    contactMonster(monsterIndex);

//                gP.eHandler.checkEvent();

                    // Only set moving to true if there is no collision
                    if (!collisionOn) {
                        moving = true;
                    }
                } else {
                    //sets direction when not moving
                    standCounter++;
                    if (standCounter == 20) {
                        spriteNum = 1;
                        standCounter = 0;
                    }
                }
            }

            if (moving && !collisionOn && !attacking)
            {
                    switch (direction) {
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

                    pixelCounter += (int)speed; // Only update if actually moved


            // Animate sprite
            spriteCounter++;
            if (spriteCounter > FPS / 3) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
                // Animate sprite
                spriteCounter++;
                if (spriteCounter > FPS / 3) {
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
                if (invincibleCounter > 120) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
        }
    public void attack()
    {
        spriteCounter++;
        moving = false;

        if (spriteCounter < 25)
        {
            spriteNum = 1;
        }
        if (spriteCounter < 75)
        {
            spriteNum = 2;
        }
        else // spriteCounter >= 75
        {
            spriteCounter = 0;
            spriteNum = 1;
            attacking = false;
            collisionOn = false;
            timeSinceAttack = 0;
        }
    }

    public void contactMonster(int i){
        if(i != 999){
            if(!invincible) {
                damage(1);
                invincible = true;
            }
        }
    }
    @Override
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (attacking)
                {
                    if(spriteNum == 1) {image = upAttack1;}
                    if(spriteNum == 2) {image = upAttack2;}
                }
                else {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                break;
            case "down":
                if (attacking)
                {
                    if(spriteNum == 1) {image = downAttack1;}
                    if(spriteNum == 2) {image = downAttack2;}
                }
                else {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                break;
            case "left":
                if (attacking)
                {
                    if(spriteNum == 1) {image = leftAttack1;}
                    if(spriteNum == 2) {image = leftAttack2;}
                }
                else {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                break;
            case "right":
                if (attacking)
                {
                    if(spriteNum == 1) {image = rightAttack1;}
                    if(spriteNum == 2) {image = rightAttack2;}
                }
                else {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
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
