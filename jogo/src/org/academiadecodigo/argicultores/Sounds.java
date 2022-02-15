package org.academiadecodigo.argicultores;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {
    private String path;
    public Sounds(String path){
        this.path = path;
    }

    public void play() {
    try{
        File filePath = new File(path);

        if (filePath.exists()) {
            AudioInputStream audioInput = null;
            try {
                audioInput = AudioSystem.getAudioInputStream(filePath);
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            }
            clip.open(audioInput);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if(path == "resources/bgmusic.wav") {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.start();
        } else {
            System.out.println("Incorrect audio file path!");
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (LineUnavailableException e) {
        e.printStackTrace();
    }
    }

}
