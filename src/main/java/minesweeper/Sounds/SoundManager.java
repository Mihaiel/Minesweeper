package minesweeper.Sounds;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;
import java.util.Random;

/**
 * Manages the sound effects and music for the Minesweeper game.
 * Provides methods to preload, play, and stop various sounds and music tracks.
 */
public class SoundManager {

    /** A map containing sound effects identified by their names. */
    private static final HashMap<String, MediaPlayer> soundEffects = new HashMap<>();

    /** The MediaPlayer instance for the title screen music. */
    private static MediaPlayer titleScreenMusicPlayer;

    // Static initializer block to preload sounds and music
    static {
        preloadSounds();
        preloadMusic();
    }

    /**
     * Preloads sound effects into the soundEffects map.
     * This ensures that sound effects are ready to play during the game.
     */
    private static void preloadSounds() {
        try {
            soundEffects.put("cell_open", new MediaPlayer(new Media(SoundManager.class.getResource("/sounds/effects/cell_open.wav").toExternalForm())));
            soundEffects.put("ui_select", new MediaPlayer(new Media(SoundManager.class.getResource("/sounds/effects/ui_select.wav").toExternalForm())));
            soundEffects.put("mine_open_0", new MediaPlayer(new Media(SoundManager.class.getResource("/sounds/effects/mine_open_0.wav").toExternalForm())));
            soundEffects.put("mine_open_1", new MediaPlayer(new Media(SoundManager.class.getResource("/sounds/effects/mine_open_1.wav").toExternalForm())));
            soundEffects.put("mine_open_2", new MediaPlayer(new Media(SoundManager.class.getResource("/sounds/effects/mine_open_2.wav").toExternalForm())));
        } catch (Exception e) {
            System.err.println("Error preloading sounds.");
            e.printStackTrace();
        }
    }

    /**
     * Preloads the title screen music and prepares it for looping playback.
     * This ensures smooth transitions when playing the title screen music.
     */
    private static void preloadMusic() {
        try {
            Media titleMusic = new Media(SoundManager.class.getResource("/sounds/effects/music/title_screen_music.mp3").toExternalForm());
            titleScreenMusicPlayer = new MediaPlayer(titleMusic);
            titleScreenMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music indefinitely
            titleScreenMusicPlayer.setVolume(0.5); // Set volume to 50% (optional)
        } catch (Exception e) {
            System.err.println("Error loading title screen music.");
            e.printStackTrace();
        }
    }

    /**
     * Plays the title screen music. If the music is already playing, this does nothing.
     */
    public static void playTitleScreenMusic() {
        if (titleScreenMusicPlayer != null) {
            titleScreenMusicPlayer.play(); // Start playing the music
        }
    }

    /**
     * Stops the title screen music if it is currently playing.
     */
    public static void stopTitleScreenMusic() {
        if (titleScreenMusicPlayer != null) {
            titleScreenMusicPlayer.stop(); // Stop the music
        }
    }

    /**
     * Plays the sound effect associated with opening a cell.
     */
    public static void playCellOpen() {
        playSound("ui_select");
    }

    /**
     * Plays a random sound effect associated with opening a mine.
     */
    public static void playMineOpen() {
        Random rand = new Random();
        int random_num = rand.nextInt(3);
        playSound("mine_open_" + random_num);
    }

    /**
     * Plays the sound effect associated with a wide-open action.
     */
    public static void playWideOpen() {
        playSound("cell_open");
    }

    /**
     * Plays a sound effect specified by its name.
     *
     * @param soundName The name of the sound effect to play.
     */
    private static void playSound(String soundName) {
        MediaPlayer mediaPlayer = soundEffects.get(soundName);
        if (mediaPlayer != null) {
            mediaPlayer.stop(); // Reset playback position
            mediaPlayer.play();
        } else {
            System.err.println("Sound not found: " + soundName);
        }
    }
}
