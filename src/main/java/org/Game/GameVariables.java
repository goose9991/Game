package org.Game;

public interface GameVariables {
    // Tile Sizing
    int originalTileSize = 16;
    int scale = 3;
    int tileSize = originalTileSize * scale;

    // Screen Sizing
    int maxScreenCol = 16;
    int maxScreenRow = 12;
    int screenWidth = tileSize * maxScreenCol;
    int screenHeight = tileSize * maxScreenRow;

    // Max Size
    int maxWorldCol = 50;
    int maxWorldRow = 50;

    // FPS
    int FPS = 120;

    // Game States
    int playState = 1;
    int pauseState = 2;
}
