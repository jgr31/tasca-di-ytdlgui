package gelabert.ytdlgui;

import java.time.OffsetDateTime;
import java.util.EventObject;
import java.util.List;

/**
 * Event fired when the polling component detects newly added media items.
 */
public class MediaPollingEvent extends EventObject {

    private final List<Media> newMedia;
    private final OffsetDateTime discoveredAt;

    /**
     * Creates a new polling event.
     *
     * @param source component that generated the event
     * @param newMedia list of newly discovered media items
     * @param discoveredAt instant when the media was detected
     */
    public MediaPollingEvent(Object source, List<Media> newMedia, OffsetDateTime discoveredAt) {
        super(source);
        this.newMedia = newMedia;
        this.discoveredAt = discoveredAt;
    }

    public List<Media> getNewMedia() {
        return newMedia;
    }

    public OffsetDateTime getDiscoveredAt() {
        return discoveredAt;
    }
}
