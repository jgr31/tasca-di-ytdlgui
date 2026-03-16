package gelabert.ytdlgui;

/**
 * Listener notified whenever {@link MediaPollingComponent} discovers new media.
 */
public interface MediaPollingListener {
    void newMediaFound(MediaPollingEvent evt);
}
