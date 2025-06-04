package util;

import javazoom.jl.player.Player;
import java.io.InputStream;

public class AudioPlayer {
    private Player player;
    private Thread playThread;
    private volatile boolean running = false;

    public void playLoop(String resourcePath) {
        stop();
        running = true;
        playThread = new Thread(() -> {
            while (running) {
                try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
                    if (is == null) {
                        System.err.println("音樂檔案找不到: " + resourcePath);
                        break;
                    }
                    player = new Player(is);
                    player.play();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        playThread.start();
    }

    public void stop() {
        running = false;
        if (player != null) {
            player.close();
        }
        if (playThread != null && playThread.isAlive()) {
            playThread.interrupt();
        }
    }
}