package hu.bme.redivel.Tetris;

import javax.sound.sampled.Clip;

public class SoundLooped extends Sound{
    public SoundLooped(String url) {
        super(url);
        loop = Clip.LOOP_CONTINUOUSLY;
    }
}
