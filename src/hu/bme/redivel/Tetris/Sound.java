package hu.bme.redivel.Tetris;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    protected String url;
    protected Clip clip;
    protected int loop;

    public Sound(String url) {
        this.url = url;
        try {
            clip = AudioSystem.getClip();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        loop = 0;
    }

    public void play(){
        try {
            AudioInputStream music = AudioSystem.getAudioInputStream(new File(url));
            clip.open(music);
            clip.loop(loop);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
