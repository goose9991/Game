package monster;

import entity.Entity;
import org.Game.GamePanel;
import java.awt.*;
import java.util.Random;

public class MON_X extends Monster  {
    public MON_X(GamePanel gp) {

        super(gp, "X");

        speed = 1;
        maxLife = 4;
        setLife(maxLife);
    }
}

