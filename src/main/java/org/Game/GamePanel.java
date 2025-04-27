package org.Game;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    //default fps size
    public int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    AssetSetter aSetter = new AssetSetter(this);
    Sound sound = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;
    public UI ui = new UI(this);

    //Entity and Object
    public Player player = new Player(this, keyH);
    public Entity[] obj = new Entity[10];
    public Entity[] monster = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();

    // Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel()
    {
        //JPanel methods are called
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){

        aSetter.setMonster();
        playMusic(0);
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
        if(gameState == playState){
            player.update();

            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    monster[i].update();
                }
            }
        }

        if(gameState == pauseState){

        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Map
        tileM.draw(g2);

        // Add entities to list
        entityList.add(player);

        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                entityList.add(obj[i]);
            }
        }

        for(int i = 0; i < monster.length; i++){
            if(monster[i] != null){
                entityList.add(monster[i]);
            }
        }

        Collections.sort(entityList, new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {

                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            }
        });

        //draw entities
        for(int i = 0; i < entityList.size(); i++){
            entityList.get(i).draw(g2);
        }

        //empty the list
        entityList.clear();

        // UI
        ui.draw(g2);

        g2.dispose();

    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }

}
