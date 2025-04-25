package org.Game;

import monster.MON_Google;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setMonster(){

        gp.monster[0] = new MON_Google(gp);
        gp.monster[0].worldX = gp.tileSize * 23;
        gp.monster[0].worldY = gp.tileSize * 36;

        gp.monster[1] = new MON_Google(gp);
        gp.monster[1].worldX = gp.tileSize * 25;
        gp.monster[1].worldY = gp.tileSize * 32;
    }
}
