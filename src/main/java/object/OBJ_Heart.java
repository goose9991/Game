package object;

import entity.Entity;
import org.Game.GamePanel;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp){
        super(gp);

        name = "Heart";
        image = setup("/objects/heart_full", tileSize, tileSize);
        image2 = setup("/objects/heart_half", tileSize, tileSize);
        image3 = setup("/objects/heart_blank", tileSize, tileSize);

    }
}
