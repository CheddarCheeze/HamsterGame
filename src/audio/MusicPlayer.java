/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author macandcheese
 */
public class MusicPlayer implements Runnable {
    
    private ArrayList<String> musicFiles;
    private int currentSongIndex;
    private int totalSounds;
    
    public MusicPlayer(String... files){
        musicFiles = new ArrayList<String>();
        
        for(String file : files){
            musicFiles.add("resources/audio/" + file + ".wav");
            totalSounds++;
        }
        
    }
    
    private void playSound(String fileName){
        try{
            File soundFile = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            
            //Temp volume change
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10);
            
            //Play sound
            clip.start();
            currentSongIndex = (currentSongIndex + 1) % totalSounds;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        playSound(musicFiles.get(currentSongIndex));
    }
}
