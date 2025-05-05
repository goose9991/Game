package monster;

import entity.Entity;
import org.Game.GamePanel;
import java.awt.*;
import java.util.Random;

public class MON_Google extends Monster {

    public MON_Google(GamePanel gp) {
        super(gp, "Google");

        speed = 1;
        maxLife = 4;
        setLife(maxLife);
    }
}

