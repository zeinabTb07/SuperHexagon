package Elements;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class PlayMusic {
    private boolean playMusic ;
    private Clip musicClip ;
    public PlayMusic(String path){
        try {
            File musicFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop indefinitely
            musicClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void playMusic (){
        if(playMusic && musicClip!=null){
            musicClip.start();
        }
    }
    public void stopMusic(){
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
        }
    }

    public void setPlayMusic(boolean playMusic){
        this.playMusic = playMusic;
    }
}
