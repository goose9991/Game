package monster;

import entity.Entity;
import org.Game.GamePanel;
import java.awt.*;
import java.util.Random;

abstract class Monster extends Entity  {
    public Monster(GamePanel gp, String monName){
        super(gp);

        name = monName;
        type = 2; // Type = Monster

        solidArea = new Rectangle(1,1,46,46); // Put same values as player for hitbox Rectangle, may need changes
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage(name);
    }

    public void getImage(String monsterName)
    {
        up1 = setup("/monster/" + monsterName,tileSize, tileSize); // sets the image to be X at all times.
        up2 = up1;

        down1 = setup("/monster/" + monsterName,tileSize, tileSize);
        down2 = down1;

        right1 = setup("/monster/" + monsterName,tileSize, tileSize);
        right2 = right1;

        left1 = setup("/monster/" + monsterName,tileSize, tileSize);
        left2 = left1;
    }

    public void setAction()
    {
        actionLockCounter++;

        if(actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(4) + 1; // grabs random number between 1-4

            switch(i)
            {
                case 1: direction = "up"; break;
                case 2: direction = "down"; break;
                case 3: direction = "right"; break;
                case 4: direction = "left"; break;
            }

            actionLockCounter = 0;
        }
    }

}

