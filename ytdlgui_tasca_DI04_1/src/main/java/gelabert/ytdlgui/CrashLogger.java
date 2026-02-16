package gelabert.ytdlgui;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;

public final class CrashLogger {

    private CrashLogger() {}

    public static void install() {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            try {
                writeCrashLog(thread, throwable);
            } catch (Exception ignored) {
                // Last resort: do nothing
            }

            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                    null,
                    "Unexpected error. A crash log has been generated at: logs/crash.log",
                    "Application Error",
                    JOptionPane.ERROR_MESSAGE
            ));
        });
    }

    private static void writeCrashLog(Thread thread, Throwable t) throws IOException {
        Path logsDir = Paths.get("logs");
        Files.createDirectories(logsDir);

        Path logFile = logsDir.resolve("crash.log");

        try (Writer w = new BufferedWriter(new OutputStreamWriter(
                Files.newOutputStream(logFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING),
                StandardCharsets.UTF_8
        ))) {
            w.write("=== ytdlgui crash log ===\n");
            w.write("Time: " + LocalDateTime.now() + "\n");
            w.write("Thread: " + thread.getName() + "\n\n");
            w.write(stackTraceToString(t));
        }
    }

    private static String stackTraceToString(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}

