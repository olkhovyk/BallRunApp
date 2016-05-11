package com.olkandsvir.ballrunapp.brhelpers;

import com.badlogic.gdx.audio.Music;

/**
 * Created by ilya on 11.05.2016.
 */
public class SoundHandler {
    private static boolean soundOn = true;
    private static boolean musicOn = true;

    private static Music backgroundMusic = AssetLoader.musicBackground;
    private static Music menuMusic = AssetLoader.musicMenu;

    public SoundHandler() {
     //   this.backgroundMusic = AssetLoader.musicBackground;
    //    this.menuMusic = AssetLoader.musicMenu;

        //Не работает
        backgroundMusic.setLooping(true);
        menuMusic.setLooping(true);
    }

    public static void playMusicMenu(){
        menuMusic.play();

    }
    public static void stopMusicMenu(){
        menuMusic.stop();
    }
    public static void pauseMusicMenu(){
        menuMusic.pause();
    }

    public static void playMusicBackground(){

            backgroundMusic.play();

    }

    public static void stopMusicBackground(){
        backgroundMusic.stop();
    }

    public static void pauseMusicBackground(){
        backgroundMusic.pause();
    }

    public static void playSoundScored(){
        if(soundOn){
        AssetLoader.soundScored.play();
        }
    }

    public static void playSoundClicked(){
        if(soundOn){
        AssetLoader.soundClicked.play();
        }
    }

    public static void playSoundDefeat(){
        if(soundOn){
        AssetLoader.soundDefeat.play();
        }
    }

    public static void playSoundHighScore(){
        if(soundOn){
        AssetLoader.soundHighScore.play();
        }
    }

    public void setSoundOn(){
        if(soundOn){
            soundOn = false;
        }
        else if(!soundOn){
            soundOn = true;
        }
    }

    public void setMusicOn(){
        if(musicOn){
            musicOn = false;
            backgroundMusic.setVolume(0);
            menuMusic.setVolume(0);
        }
        else if(!musicOn){
            musicOn = true;
            backgroundMusic.setVolume(1);
            menuMusic.setVolume(1);

        }

    }
}
