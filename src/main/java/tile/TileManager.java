package tile;

import org.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
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
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
        } catch (IOException e){
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

                g2.drawImage(tile[tileNum].image, screenX, screenY, gP.tileSize, gP.tileSize, null);

            }
            worldCol++;
            if(worldCol == gP.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }

        }
    }
}
