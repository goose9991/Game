package org.Game;

public interface GameVariables {
    // Tile Sizing
    int originalTileSize = 16;
    int scale = 3;
    int tileSize = originalTileSize * scale;

    // Screen Sizing
    int maxScreenCol = 24;
    int maxScreenRow = 16;
    int screenWidth = tileSize * maxScreenCol;
    int screenHeight = tileSize * maxScreenRow;

    // Previously in Player Class
    int screenX = screenWidth/2 - (tileSize/2);
    int screenY = screenHeight/2 - (tileSize/2);

    // Max Size
    int maxWorldCol = 50;
    int maxWorldRow = 50;

    // FPS
    int FPS = 120;

    // Game States
    int playState = 1;
    int pauseState = 2;
    int gameOverState = 3;
    int victoryState = 4;

    int killsNeededToWin = 4;
}
