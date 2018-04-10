package Tanchiki;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Music {
    static String musicFile = "1.mp3";
    static int counter = 1;
    static MediaPlayer mediaPlayer;


    public static void changeMusic() {
        try {
            mediaPlayer.stop();
        } catch (NullPointerException e) {
        }
        if (counter == 12) {
            String musicFile1 = "12.mp3";
            musicFile = musicFile1;
            counter ++;
        } if (counter == 11) {
            String musicFile1 = "11.mp3";
            musicFile = musicFile1;
            counter ++;
        } if (counter == 10) {
            String musicFile1 = "10.mp3";
            musicFile = musicFile1;
            counter ++;
        }
        if (counter == 9) {
            String musicFile1 = "9.mp3";
            musicFile = musicFile1;
            counter ++;
        }
        if (counter == 8) {
            String musicFile1 = "8.mp3";
            musicFile = musicFile1;
            counter++;
        }
        if (counter == 7) {
            String musicFile1 = "7.mp3";
            musicFile = musicFile1;
            counter++;
        }
        if (counter == 6) {
            String musicFile1 = "6.mp3";
            musicFile = musicFile1;
            counter++;
        }
        if (counter == 5) {
            String musicFile1 = "5.mp3";
            musicFile = musicFile1;
            counter++;
        }
        if (counter == 4) {
            String musicFile1 = "4.mp3";
            musicFile = musicFile1;
            counter++;
        }
        if (counter == 3) {
            String musicFile1 = "3.mp3";
            musicFile = musicFile1;
            counter++;
        }
        if (counter == 2) {
            String musicFile1 = "2.mp3";
            musicFile = musicFile1;
            counter++;
        }
        if (counter == 1) {
            String musicFile1 = "1.mp3";
            musicFile = musicFile1;
            counter++;
        }
        if(counter==13){
            counter=1;
        }
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }


}
