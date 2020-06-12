package entidades;

/**
 * Created by cgallo on 06/06/20.
 */

public class NewsAgency {
    private int id;
    private String description;
    private String home_path;
    private boolean is_active;

    public NewsAgency(int id, String description, String home_path, boolean is_active) {
        this.id = id;
        this.description = description;
        this.home_path = home_path;
        this.is_active = is_active;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomePath() {
        return home_path;
    }

    public void setHomePath(String home_path) {
        this.home_path = home_path;
    }

    public boolean getIsActive() {
        return is_active;
    }

    public void setIsActive(boolean is_active) {
        this.is_active = is_active;
    }
}
