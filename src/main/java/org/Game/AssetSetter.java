package org.Game;

import monster.MON_Google;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

//    public void setObject()
//    {
//        // put object info here
//    }
//    public void setNPC()
//    {
//        // put NPC info here
//    }
    public void setMonster()
    {
        gp.monsters[0] = new MON_Google(gp);
        gp.monsters[0].worldX = gp.tileSize*23;
        gp.monsters[0].worldY = gp.tileSize*36;

        gp.monsters[1] = new MON_Google(gp);
        gp.monsters[1].worldX = gp.tileSize*23;
        gp.monsters[1].worldY = gp.tileSize*37;

    }
}
