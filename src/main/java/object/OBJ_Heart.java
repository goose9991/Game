package object;

import org.Game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject{
    GamePanel gP;
    public OBJ_Heart(GamePanel gP){

        this.gP = gP;
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));

            uTool.scaleImage(image, gP.tileSize, gP.tileSize);
            uTool.scaleImage(image2, gP.tileSize, gP.tileSize);
            uTool.scaleImage(image3, gP.tileSize, gP.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
