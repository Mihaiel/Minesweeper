package minesweeper.Sounds;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;
import java.util.Random;

public class SoundManager {

    private static final HashMap<String, MediaPlayer> soundEffects = new HashMap<>();

    static {
        preloadSounds();
    }

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

    public static void playCellOpen() {
        playSound("ui_select");
    }

    public static void playMineOpen() {
        Random rand = new Random();
        int random_num = rand.nextInt(3);
        playSound("mine_open_" + random_num);
    }

    public static void playWideOpen() {
        playSound("cell_open");
    }

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
