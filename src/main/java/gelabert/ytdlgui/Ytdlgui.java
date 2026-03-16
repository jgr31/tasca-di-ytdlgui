package gelabert.ytdlgui;

/**
 * Entry point of the ytdlgui desktop application.
 * Initializes global crash logging and opens the main Swing window.
 */
public class Ytdlgui {


/**
 * Starts the application on the Swing event dispatch thread.
 *
 * @param args command line arguments passed to the application
 */
public static void main(String[] args) {

    // DI04 - crash logging global
    CrashLogger.install();

    java.awt.EventQueue.invokeLater(() -> {
        new MainFrame().setVisible(true);
    });
}

}

