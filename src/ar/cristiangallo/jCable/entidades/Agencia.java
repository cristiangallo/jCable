package ar.cristiangallo.jCable.entidades;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.dataDB.DBAgencia;

public class Agencia {
    private int id;
    private String descripcion;
    private String home_path;
    private Integer dias_purga = 7;
    private boolean is_active;

    public Agencia(int id, String Descripcion, String home_path, boolean is_active) {
        this.id = id;
        this.descripcion = Descripcion;
        this.home_path = home_path;
        this.is_active = is_active;
    }

    // constructor para la agencia guardada en la db
    public Agencia(int id, String descripcion, String home_path, Integer dias_purga, boolean is_active) {
        this.id = id;
        this.descripcion = descripcion;
        this.home_path = home_path;
        this.dias_purga = dias_purga;
        this.is_active = is_active;
    }

    public Agencia(String descripcion, String home_path, Integer dias_purga, boolean is_active) throws appException {
        if (descripcion == "") throw new appException("Ingresa una descripción.");
        if (home_path == "") throw new appException("Ingresa la ubicación de los archivos de los cables.");
        this.descripcion = descripcion;
        this.home_path = home_path;
        this.dias_purga = dias_purga;
        this.is_active = is_active;
        DBAgencia.getInstancia().save(this);
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
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

    public Integer getDiasPurga() {
        return dias_purga;
    }

    public void save() {
        DBAgencia.getInstancia().save(this);
    }

    public void setDiasPurga(Integer dias_purga) {
        this.dias_purga = dias_purga;
    }

    public void delete() {
        /* ToDo revisar que no tenga cables la agencia para poder eliminarla */
        DBAgencia.getInstancia().delete(this);
    }

}
