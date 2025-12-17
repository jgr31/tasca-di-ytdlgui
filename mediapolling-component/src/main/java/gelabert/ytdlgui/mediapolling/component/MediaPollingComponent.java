package gelabert.ytdlgui.mediapolling.component;

import javax.swing.*;
import java.awt.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean sense visuals (només un JLabel amb una icona/text)
 * que encapsula l'ApiClient i fa polling periòdic a la DI Media NET.
 */
public class MediaPollingComponent extends JPanel {

    // --- Propietats del component ---
    private String apiUrl = "https://dimedianetapi9.azurewebsites.net";
    private boolean running = false;
    private int pollingIntervalSeconds = 30;   // cada 30s per defecte
    private String token;
    private String lastChecked;               // representació textual

    // --- Internals ---
    private transient ApiClient apiClient;
    private transient javax.swing.Timer timer;
    private OffsetDateTime lastCheckedDateTime;

    // listeners personalitzats
    private final List<MediaPollingListener> listeners = new ArrayList<>();

    public MediaPollingComponent() {
        // Layout i "icona"
        setLayout(new BorderLayout());
        JLabel lbl = new JLabel("Media Polling", SwingConstants.CENTER);
        add(lbl, BorderLayout.CENTER);

        // Crear ApiClient inicial
        apiClient = new ApiClient(apiUrl);

        // Crear timer (no comença fins que running = true)
        timer = new javax.swing.Timer(pollingIntervalSeconds * 1000, e -> onTimerTick());
        timer.setRepeats(true);
        timer.stop();
    }

    // =====================
    //  GETTERS / SETTERS
    // =====================

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
        this.apiClient = new ApiClient(apiUrl);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
        if (running) {
            if (!timer.isRunning()) {
                // si no hi ha lastChecked encara, posem "ara - 1 hora"
                if (lastCheckedDateTime == null) {
                    lastCheckedDateTime = OffsetDateTime.now().minusHours(1);
                    lastChecked = lastCheckedDateTime.toString();
                }
                // Fem una primera comprovació immediata
                onTimerTick();
                // i després continuam amb el timer periòdic
                timer.start();
            }
        } else {
            timer.stop();
        }
    }

    public int getPollingIntervalSeconds() {
        return pollingIntervalSeconds;
    }

    public void setPollingIntervalSeconds(int pollingIntervalSeconds) {
        if (pollingIntervalSeconds <= 0) {
            pollingIntervalSeconds = 1;
        }
        this.pollingIntervalSeconds = pollingIntervalSeconds;
        timer.setDelay(pollingIntervalSeconds * 1000);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(String lastChecked) {
        this.lastChecked = lastChecked;
        try {
            this.lastCheckedDateTime = OffsetDateTime.parse(lastChecked);
        } catch (Exception ignored) {
            this.lastCheckedDateTime = null;
        }
    }

    // =====================
    //   WRAPPERS ApiClient
    // =====================

public String login(String email, String password) throws Exception {
    String t = apiClient.login(email, password);

    // Guardam el token al component
    setToken(t);

    // Activam el polling automàtic quan el login té èxit
    setRunning(true);

    return t;
}


    public Usuari getMe(String jwt) throws Exception {
        return apiClient.getMe(jwt);
    }

    public String getNickName(int userId, String jwt) throws Exception {
        return apiClient.getNickName(userId, jwt);
    }

    public java.util.List<Media> getAllMedia(String jwt) throws Exception {
        return apiClient.getAllMedia(jwt);
    }

    public java.util.List<Media> getMediaByUser(int userId, String jwt) throws Exception {
        return apiClient.getMediaByUser(userId, jwt);
    }

    public java.util.List<Media> getMyMedia(String jwt) throws Exception {
        return apiClient.getMyMedia(jwt);
    }

    public void download(int id, java.io.File destFile, String jwt) throws Exception {
        apiClient.download(id, destFile, jwt);
    }

    public String uploadFileMultipart(java.io.File file, String downloadedFromUrl, String jwt) throws Exception {
        return apiClient.uploadFileMultipart(file, downloadedFromUrl, jwt);
    }

    public java.util.List<Media> getMediaAddedSince(OffsetDateTime from, String jwt) throws Exception {
        return apiClient.getMediaAddedSince(from, jwt);
    }

    public java.util.List<Media> getMediaAddedSince(String isoFrom, String jwt) throws Exception {
        return apiClient.getMediaAddedSince(isoFrom, jwt);
    }

    // =====================
    //   TIMER / POLLING
    // =====================

    private void onTimerTick() {
        if (!running) return;
        if (token == null || token.isBlank()) return;

        try {
            OffsetDateTime from = lastCheckedDateTime;
            if (from == null) {
                from = OffsetDateTime.now().minusHours(1);
            }

            List<Media> newMedia = apiClient.getMediaAddedSince(from, token);
            // Actualitzem "lastChecked" a ara, tant si hi ha canvis com si no
            lastCheckedDateTime = OffsetDateTime.now();
            lastChecked = lastCheckedDateTime.toString();

            int count = (newMedia != null) ? newMedia.size() : 0;
            // 🔹 Missatge que vols veure a la consola (encara que no hi hagi canvis)
            System.out.println(">>> MediaPolling: trobats "
                    + count + " fitxers nous a les " + lastCheckedDateTime);

            // Només notificam listeners si realment hi ha nous fitxers
            if (newMedia != null && !newMedia.isEmpty()) {
                fireNewMediaFound(newMedia, lastCheckedDateTime);
            }

        } catch (Exception ex) {
            // De moment només ho mostrem per consola
            System.err.println("Error in polling: " + ex.getMessage());
        }
    }

    // =====================
    //   EVENTS
    // =====================

    public void addMediaPollingListener(MediaPollingListener l) {
        if (l != null && !listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void removeMediaPollingListener(MediaPollingListener l) {
        listeners.remove(l);
    }

    protected void fireNewMediaFound(List<Media> newMedia, OffsetDateTime when) {
        MediaPollingEvent evt = new MediaPollingEvent(this, newMedia, when);
        for (MediaPollingListener l : new ArrayList<>(listeners)) {
            l.newMediaFound(evt);
        }
    }
}
