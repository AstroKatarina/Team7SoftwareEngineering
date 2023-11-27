import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import java.util.Random;

public class MusicPlayer {
    

    public void startPlayingWAV() {

        //random number chosen and stored in trackSelector
        Random rand = new Random();
        int trackNum = rand.nextInt(3);
        trackNum = trackNum + 1;
        String file = ("src/Track0" + trackNum + ".wav");
        System.out.println(file);
        PlayMusic(file);
    }

    public void PlayMusic(String fileName)
    {
        try
        {
            File musicFile = new File(fileName);
            if(musicFile.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else
            {
                System.out.println("Cant read audio file");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
