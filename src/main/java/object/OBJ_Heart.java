package object;

import entity.Entity;
import org.Game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp){
        super(gp);

        name = "Heart";
        image = setup("/objects/heart_full", gP.tileSize, gP.tileSize);
        image2 = setup("/objects/heart_half", gP.tileSize, gP.tileSize);
        image3 = setup("/objects/heart_blank", gP.tileSize, gP.tileSize);

    }
}
