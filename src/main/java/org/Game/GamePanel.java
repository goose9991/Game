package org.Game;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

//inherit JPanel, an empty container
//interface Runnable inherited to use run method for thread timer
public class GamePanel extends JPanel implements Runnable{

    //bitmap default tile size
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;  // currently unused
    public final int worldHeight = tileSize * maxWorldRow; // also unused

    //default fps size
    public int FPS = 60;

    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    AssetSetter aSetter = new AssetSetter(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    public Player player = new Player(this, keyH);


    public Entity monsters[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel()
    {
        //JPanel methods are called
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame()
    {
        // aSetter.setObject(); -- Readd when wanting to add object to game scene
        aSetter.setMonster();

        gameState = playState;
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; //16666666.66
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }

    }

    public void update(){
        if (gameState == playState) // Game Active
        {
            // PLAYER
            player.update();

            // MONSTER
            for (int i = 0; i < monsters.length; i++)
            {
                if(monsters[i] != null)
                {
                    monsters[i].update();
                }
            }

        }
        if (gameState == pauseState) // Game Paused
        {
            // do nothing
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Map
        tileM.draw(g2);

        // PLAYER
        player.draw(g2);

        // MONSTERS
        for (int i = 0; i < monsters.length; i++)
        {
            if(monsters[i] != null)
            {
                monsters[i].draw(g2);
            }
        }

        // UI
        ui.draw(g2);

        g2.dispose();

    }
}
