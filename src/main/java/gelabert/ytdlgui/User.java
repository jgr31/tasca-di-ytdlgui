package gelabert.ytdlgui;

/**
 * Minimal user model used by local JSON examples and simple UI bindings.
 */
public class User {
    private String name;

    public User() {}

    /**
     * Returns the display name of the user.
     *
     * @return user name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

