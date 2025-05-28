package game;

import java.io.*;

public class Continue {
    private static final String SAVE_FILE = "save.txt";

    public static void saveGame(int level, int playerX, int playerY) {
        try (PrintWriter out = new PrintWriter(SAVE_FILE)) {
            out.println("level=" + level);
            out.println("playerX=" + playerX);
            out.println("playerY=" + playerY);
        } catch (Exception e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    public static SaveData loadGame() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) return null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int level = 1, x = 1, y = 1;
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("level=")) level = Integer.parseInt(line.substring(6));
                if (line.startsWith("playerX=")) x = Integer.parseInt(line.substring(8));
                if (line.startsWith("playerY=")) y = Integer.parseInt(line.substring(8));
            }
            return new SaveData(level, x, y);
        } catch (Exception e) {
            System.err.println("Error loading game: " + e.getMessage());
            return null;
        }
    }

    // 存檔資料結構
    public static class SaveData {
        public final int level;
        public final int playerX;
        public final int playerY;
        public SaveData(int level, int playerX, int playerY) {
            this.level = level;
            this.playerX = playerX;
            this.playerY = playerY;
        }
    }
} 