package org.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener, GameVariables {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState =  (gp.gameState == playState) ? pauseState : playState; // Switches between pause and play using Esc Key
            if(gp.gameState == pauseState ){
                gp.sound.stop();
            } else {
                gp.sound.play();
            }
        }
        if(code == KeyEvent.VK_M){

            Sound.mute = !Sound.mute;
            gp.sound.applyMute();
        }
        if(code == KeyEvent.VK_SPACE)
        {
            spacePressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SPACE)
        {
            spacePressed = false;
        }
    }
}
