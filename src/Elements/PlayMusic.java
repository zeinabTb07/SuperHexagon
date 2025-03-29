package Elements;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class PlayMusic {
    private Clip musicClip ;
    private int pausePosition ;
    public PlayMusic(String path){
        try {
            pausePosition = 0;
            File musicFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            musicClip.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void playMusic (){
        if(musicClip!=null){
            musicClip.setFramePosition(pausePosition);
            musicClip.start();
        }
    }
    public void stopMusic(){
        if (musicClip != null) {
            pausePosition = musicClip.getFramePosition();
            musicClip.stop();
        }
    }
//    public void setVolume(float volume) {
//        if (musicClip != null) {
//            // Obtain the MASTER_GAIN control from the clip
//            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
//
//            if (volume <= 0.0f) {
//                // Avoid log(0): set to the minimum allowed dB value for complete silence
//                gainControl.setValue(gainControl.getMinimum());
//            } else {
//                // Convert linear volume (0.0 to 1.0) to decibels
//                float dB = (float) (20.0 * Math.log10(volume));
//
//                // Clamp the dB value within the control's allowable range if needed
//                if (dB < gainControl.getMinimum()) {
//                    dB = gainControl.getMinimum();
//                } else if (dB > gainControl.getMaximum()) {
//                    dB = gainControl.getMaximum();
//                }
//                gainControl.setValue(dB);
//            }
//        }
//    }
}
