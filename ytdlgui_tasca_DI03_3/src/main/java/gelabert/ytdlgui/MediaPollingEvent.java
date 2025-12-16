package gelabert.ytdlgui;

import java.time.OffsetDateTime;
import java.util.EventObject;
import java.util.List;

public class MediaPollingEvent extends EventObject {

    private final List<Media> newMedia;
    private final OffsetDateTime discoveredAt;

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
