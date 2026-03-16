package gelabert.ytdlgui;

/**
 * Simple data model used to represent a media category loaded from JSON.
 */
public class Category {
    private int id;
    private String name;

    public Category() {} // Jackson needs empty constructor

    /**
     * Returns the identifier of the category.
     *
     * @return category id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
