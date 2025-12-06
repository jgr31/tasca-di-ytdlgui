package gelabert.ytdlgui;

import java.time.LocalDateTime;

public class MediaFile {

    private String name;           // Nom del fitxer (p. ex. "video.mp4")
    private long sizeBytes;        // Mida en bytes
    private String mimeType;       // p. ex. "audio/mpeg", "video/mp4"
    private LocalDateTime date;    // Data de descàrrega

    private String path;           // Ruta completa al fitxer

    public MediaFile(String name, long sizeBytes, String mimeType,
                     LocalDateTime date, String path) {
        this.name = name;
        this.sizeBytes = sizeBytes;
        this.mimeType = mimeType;
        this.date = date;
        this.path = path;
    }

    // Constructor buit per si el GUI Builder el necessita
    public MediaFile() {
    }

    // Getters i setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    public void setSizeBytes(long sizeBytes) {
        this.sizeBytes = sizeBytes;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // Aquest toString s'usarà per mostrar la JList
    @Override
    public String toString() {
        return name;
    }
}

