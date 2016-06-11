package com.olkandsvir.ballrunapp.brhelpers;

import com.badlogic.gdx.audio.Music;

/**
 * Plays sounds in the game.
 * @since 11.05.2016.
 */
public class SoundHandler {
    private boolean soundOn = AssetLoader.isSoundOn();
    private boolean musicOn = AssetLoader.isMusicOn();

    private Music backgroundMusic;
    private Music menuMusic;

    public SoundHandler() {

        soundOn = AssetLoader.isSoundOn();
        musicOn = AssetLoader.isMusicOn();

        backgroundMusic = AssetLoader.musicBackground;
        menuMusic = AssetLoader.musicMenu;

        setMusicVolume();

        backgroundMusic.setLooping(true);
        menuMusic.setLooping(true);
    }

    public void playSoundScored(){
        if(soundOn){
            AssetLoader.soundScored.play();
        }
    }

    public void playSoundClicked(){
        if(soundOn){
            AssetLoader.soundClicked.play();
        }
    }

    public void playSoundDefeat(){
        if(soundOn){
            AssetLoader.soundDefeat.play();
        }
    }

    public void playSoundHighScore(){
        if(soundOn){
            AssetLoader.soundHighScore.play();
        }
    }

    public void changeSound(){
        soundOn = !soundOn;
        AssetLoader.putSoundValue(soundOn);
    }

    public void setMusicVolume() {
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

    public void playMusic(Music music){
        music.play();
    }

    public void stopMusic(Music music){
        music.stop();
    }

    public void pauseMusic(Music music){
        music.pause();
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public Music getMenuMusic() {
        return menuMusic;
    }
}
