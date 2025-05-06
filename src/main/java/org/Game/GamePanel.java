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
//interface GameVariables keeps track of all public final variables
public class GamePanel extends JPanel implements Runnable, GameVariables{

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

    public GamePanel()
    {
        //JPanel methods are called
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        //allow painting
        this.setDoubleBuffered(true);
        //allow key input response
        this.setFocusable(true);
        //responds to key input response
        this.addKeyListener(keyH);
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
        // 1 second in nanoseconds divided by frames per second(frame intervals)
        double drawInterval = (double) 1000000000 / FPS;
        //tracks how many frame intervals have passed.
        double delta = 0;
        //stores the timestamp of the previous loop cycle
        long lastTime = System.nanoTime();
        //will hold the current timestamp.
        long currentTime;

        while(gameThread != null){
            //capture current time
            currentTime = System.nanoTime();
            //delta is added by (time since last loop)/frame intervals
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            //once delta accumulator hits 1 or higher, then draw screen
            if(delta >= 1){
                update(); //logic updater for movement/collision
                repaint(); //redraws screen
                delta--; //avoid accumulation for accuracy
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

        if(gameState == gameOverState)
        {

        }

        if (gameState == victoryState)
        {

        }

    }

    @Override
    public void paintComponent(Graphics g){
        //pass Graphics class to JPanel
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
        //sort all entities based on sizes
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
        sound.stopAllSounds();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }

}
