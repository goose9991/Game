package object;
import entity.Entity;
import org.Game.GamePanel;


public class OBJ_Skull extends Entity {

    public OBJ_Skull(GamePanel gp){
        super(gp);

        name = "Skull";
        image = setup("/objects/deathSkull",tileSize,tileSize);

    }
}