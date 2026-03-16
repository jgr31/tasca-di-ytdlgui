package gelabert.ytdlgui;

import java.io.*;
import java.nio.file.*;

/**
 * Utility class that stores or restores the remembered user session on disk.
 */
public class RememberHelper {

    private static final Path FILE = Paths.get(
            System.getProperty("user.home"),
            ".ytdlgui_token"
    );

    /**
     * Stores the remembered email and token in the local user home directory.
     *
     * @param email email address to remember
     * @param token authentication token associated with the session
     */
    public static void save(String email, String token) {
        try {
            Files.writeString(FILE, email + "\n" + token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the remembered credentials file if it exists.
     *
     * @return an array containing email and token, or {@code null} if nothing is stored
     */
    public static String[] load() {
        try {
            if (!Files.exists(FILE)) return null;
            String content = Files.readString(FILE);
            String[] parts = content.split("\n");
            if (parts.length == 2) return parts;
        } catch (Exception ignored) {}
        return null;
    }

    /**
     * Deletes the remembered credentials file if present.
     */
    public static void clear() {
        try {
            Files.deleteIfExists(FILE);
        } catch (IOException ignored) {}
    }
}

