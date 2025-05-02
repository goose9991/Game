package monster;

import entity.Entity;
import org.Game.GamePanel;
import java.awt.*;
import java.util.Random;

public class MON_Google extends Entity{

    public MON_Google(GamePanel gp)
    {
        super(gp);

        name = "Google";
        type = 2; // sets type to monster
        speed = 1;
        maxLife = 4;
        setLife(maxLife);

        solidArea = new Rectangle(1,1,46,46); // Put same values as player for hitbox Rectangle, may need changes
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage()
    {
        up1 = setup("/monster/Google",tileSize, tileSize); // sets the image to be Google at all times.
        up2 = up1;

        down1 = setup("/monster/Google",tileSize, tileSize);
        down2 = down1;

        right1 = setup("/monster/Google",tileSize, tileSize);
        right2 = right1;

        left1 = setup("/monster/Google",tileSize, tileSize);
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
