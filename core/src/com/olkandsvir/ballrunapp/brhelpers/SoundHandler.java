package com.olkandsvir.ballrunapp.brhelpers;

import com.badlogic.gdx.audio.Music;

/**
 * Plays sounds in the game.
 * @since 11.05.2016.
 */
public class SoundHandler {
    private static boolean soundOn = AssetLoader.isSoundOn();
    private static boolean musicOn = AssetLoader.isMusicOn();

    private static Music backgroundMusic = AssetLoader.musicBackground;
    private static Music menuMusic = AssetLoader.musicMenu;

    public SoundHandler() {
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

    public void changeSound(){
        soundOn = !soundOn;
        AssetLoader.putSoundValue(soundOn);
    }

    public static void setMusicVolume() {
        if(!musicOn){
            backgroundMusic.setVolume(0);
            menuMusic.setVolume(0);
        } else {
            backgroundMusic.setVolume(1);
            menuMusic.setVolume(1);
        }
    }

    public void changeMusic(){
        musicOn = !musicOn;
        setMusicVolume();
        AssetLoader.putMusicValue(musicOn);
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

    public static Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public static Music getMenuMusic() {
        return menuMusic;
    }

    public static void setLooping(Music music, boolean val) {
        music.setLooping(val);
    }
}
