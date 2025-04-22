package tile;

import org.Game.GamePanel;
import org.Game.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gP;
    public Tile[] tile;
    public int[][] mapTileNum;
    public TileManager(GamePanel gP){
        this.gP = gP;
        tile = new Tile[10];
        getTileImage();
        mapTileNum = new int[gP.maxWorldCol][gP.maxWorldRow];
        loadMap("/maps/world01.txt");
    }

    public void getTileImage(){
        setup(0, "grass", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3, "sand", false);
        setup(4, "tree", true);
        setup(5, "sand", false);
    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gP.tileSize, gP.tileSize);
            tile[index].collision = collision;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream iS = getClass().getResourceAsStream(filePath);
            BufferedReader bR = new BufferedReader(new InputStreamReader(iS));
            int col = 0;
            int row = 0;
            while(col < gP.maxWorldCol && row < gP.maxWorldRow){
                String line = bR.readLine();

                while(col < gP.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gP.maxWorldCol) {
                    col = 0;
                    row++;
                }

            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gP.maxWorldCol && worldRow < gP.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gP.tileSize;
            int worldY = worldRow * gP.tileSize;
            int screenX = worldX - gP.player.worldX + gP.player.screenX;
            int screenY = worldY - gP.player.worldY + gP.player.screenY;

            if (worldX + gP.tileSize > gP.player.worldX - gP.player.screenX &&
                    worldX - gP.tileSize< gP.player.worldX + gP.player.screenX &&
                    worldY + gP.tileSize > gP.player.worldY - gP.player.screenY &&
                    worldY - gP.tileSize< gP.player.worldY + gP.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            }
            worldCol++;
            if(worldCol == gP.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }

        }
    }
}
