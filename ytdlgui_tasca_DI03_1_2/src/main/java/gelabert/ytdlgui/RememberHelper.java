package gelabert.ytdlgui;

import java.io.*;
import java.nio.file.*;

public class RememberHelper {

    private static final Path FILE = Paths.get(
            System.getProperty("user.home"),
            ".ytdlgui_token"
    );

    public static void save(String email, String token) {
        try {
            Files.writeString(FILE, email + "\n" + token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] load() {
        try {
            if (!Files.exists(FILE)) return null;
            String content = Files.readString(FILE);
            String[] parts = content.split("\n");
            if (parts.length == 2) return parts;
        } catch (Exception ignored) {}
        return null;
    }

    public static void clear() {
        try {
            Files.deleteIfExists(FILE);
        } catch (IOException ignored) {}
    }
}

