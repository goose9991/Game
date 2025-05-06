package object;
import entity.Entity;
import org.Game.GamePanel;


public class OBJ_Crown extends Entity {

    public OBJ_Crown(GamePanel gp){
        super(gp);

        name = "Crown";
        image = setup("/objects/crown",tileSize,tileSize);

    }
}