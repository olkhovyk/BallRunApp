package com.olkandsvir.ballrunapp.brhelpers;

import com.badlogic.gdx.audio.Music;

/**
 * Plays sounds in the game.
 * @since 11.05.2016.
 */
public class SoundHandler {
    private static boolean soundOn = true;
    private static boolean musicOn = true;

    private static Music backgroundMusic = AssetLoader.musicBackground;
    private static Music menuMusic = AssetLoader.musicMenu;

    public SoundHandler() {
      //  backgroundMusic.setLooping(true);
     //   menuMusic.setLooping(true);

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
        soundOn = !soundOn;
    }

    public void setMusicOn(){
        if(musicOn){
            musicOn = false;
            backgroundMusic.setVolume(0);
            menuMusic.setVolume(0);

        } else {
            musicOn = true;
            backgroundMusic.setVolume(1);
            menuMusic.setVolume(1);

        }
    }

    public static void playMusic(Music music){
        music.play();
    }

    public static void stopMusic(Music music){
        music.stop();
    }

    public static void pauseMusic(Music music){
        music.pause();
    }
}
