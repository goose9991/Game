package monster;

import org.Game.GamePanel;


public class MON_Google extends Monster {

    public MON_Google(GamePanel gp) {
        super(gp, "Google");

        speed = 1;
        maxLife = 3;
        setLife(maxLife);
    }
}

