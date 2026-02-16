package gelabert.ytdlgui;

public class Ytdlgui {

    public static void main(String[] args) {

    // DI04 - crash logging global
    CrashLogger.install();

    java.awt.EventQueue.invokeLater(() -> {
        new MainFrame().setVisible(true);
    });
}

}

