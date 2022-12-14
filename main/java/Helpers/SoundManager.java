package Helpers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {

    public SoundManager() {
    }
    
    public void playScoreSound(){   
        playSound("/beep_sound.wav");
    }
    
    public void playGameOverSound(){   
        playSound("/gameover_sound.wav");
    }
    
    public void playBallHitSound(){   
        playSound("/ballhit_sound.wav");
    }
    
    public void playSelectSound(){   
        playSound("/select_sound.wav");
    }
    
    public void playWinSound(){   
        playSound("/win_sound.wav");
    }
    
    private void playSound(String soundName){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(SoundManager.class.getResourceAsStream(soundName)));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

