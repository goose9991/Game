package org.Game;

import entity.Entity;

public class CollisionChecker implements GameVariables{

    GamePanel gP;

    public CollisionChecker(GamePanel gP){
        this.gP = gP;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/ tileSize;
        int entityRightCol = entityRightWorldX/ tileSize;
        int entityTopRow = entityTopWorldY/ tileSize;
        int entityBottomRow = entityBottomWorldY/ tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - (int)entity.speed)/ tileSize;
                tileNum1 = gP.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gP.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gP.tileM.tile[tileNum1].collision ||
                        gP.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + (int)entity.speed)/ tileSize;
                tileNum1 = gP.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gP.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gP.tileM.tile[tileNum1].collision ||
                        gP.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - (int)entity.speed)/ tileSize;
                tileNum1 = gP.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gP.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gP.tileM.tile[tileNum1].collision ||
                        gP.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + (int)entity.speed)/ tileSize;
                tileNum1 = gP.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gP.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gP.tileM.tile[tileNum1].collision ||
                        gP.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                }
                break;
        }
    }

    //NPC or Monster collison
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;

        for(int i = 0; i < target.length; i++){
            if(target[i] != null){
                //get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get object solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= (int)entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += (int)entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= (int)entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += (int)entity.speed;
                        break;
                }

                if(entity.solidArea.intersects(target[i].solidArea)){
                    if(target[i] != entity){
                        //entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity){

            boolean contactPlayer =  false;

            //get entity solid area position
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;

            //get object solid area position
            gP.player.solidArea.x = gP.player.worldX + gP.player.solidArea.x;
            gP.player.solidArea.y = gP.player.worldY + gP.player.solidArea.y;

            switch(entity.direction){
                case "up":
                    entity.solidArea.y -= (int)entity.speed;
                    break;
                case "down":
                    entity.solidArea.y += (int)entity.speed;
                    break;
                case "left":
                    entity.solidArea.x -= (int) entity.speed;
                    break;
                case "right":
                    entity.solidArea.x += (int)entity.speed;
                    break;
            }
        if(entity.solidArea.intersects(gP.player.solidArea)){
            //entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gP.player.solidArea.x = gP.player.solidAreaDefaultX;
        gP.player.solidArea.y = gP.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
