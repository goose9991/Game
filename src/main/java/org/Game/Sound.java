package org.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;
import java.util.ArrayList;

public class Sound {
    private Clip clip;
    private URL[] soundURL = new URL[30];
    private FloatControl volumeControl;
    public static boolean mute = false;
    private ArrayList<Clip> activeClips = new ArrayList<>();

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/game_music.wav");
        soundURL[1] = getClass().getResource("/sound/laser_sword.wav");
        soundURL[2] = getClass().getResource("/sound/hurt.wav");
        soundURL[3] = getClass().getResource("/sound/game_over.wav");
    }

    public void setFile(int i){

        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            activeClips.add(clip);
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                applyMute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

    public void stopAllSounds(){
        for(Clip c : activeClips){
            c.stop();
        }
    }

    public void applyMute() {
        for (Clip c : activeClips) {
            if (c.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl vc = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
                if (mute) {
                    vc.setValue(vc.getMinimum());
                } else {
                    float min = vc.getMinimum();
                    float max = vc.getMaximum();
                    float volume = 0.8f; // 80% volume
                    float dB = min + (max - min) * volume;
                    vc.setValue(dB);
                }
            }
        }
    }


}
