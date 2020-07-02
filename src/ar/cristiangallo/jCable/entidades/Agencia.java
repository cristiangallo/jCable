package ar.cristiangallo.jCable.entidades;

public class Agencia {
    private int id;
    private String descripcion;
    private String home_path;
    private int dias_purga = 7;
    private boolean is_active;

    public Agencia(int id, String description, String home_path, boolean is_active) {
        this.id = id;
        this.descripcion = description;
        this.home_path = home_path;
        this.is_active = is_active;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return descripcion;
    }

    public void setDescription(String description) {
        this.descripcion = description;
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

    public int getDiasPurga() {
        return dias_purga;
    }
}
