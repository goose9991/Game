package org.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    private Clip clip;
    private URL[] soundURL = new URL[30];
    private FloatControl volumeControl;
    public static boolean mute = false;

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/game_music.wav");
        soundURL[1] = getClass().getResource("/sound/laser_sword.wav");
    }

    public void setFile(int i){

        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

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

    public void setVolume(float volume) {
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float dB = min + (max - min) * volume; // volume is expected between 0.0 and 1.0
            volumeControl.setValue(dB);
        }
    }

    public void applyMute(){
        if(volumeControl != null){
            if(mute){
                volumeControl.setValue(volumeControl.getMinimum());
            } else{
                setVolume(.8f);
            }
        }
    }

}
