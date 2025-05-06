package monster;

import org.Game.GamePanel;

public class MON_X extends Monster  {
    public MON_X(GamePanel gp) {

        super(gp, "X");

        speed = 1;
        maxLife = 2;
        setLife(maxLife);
    }
}

