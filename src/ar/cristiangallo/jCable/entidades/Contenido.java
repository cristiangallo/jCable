package ar.cristiangallo.jCable.entidades;

import java.sql.Timestamp;
import java.util.Calendar;

public abstract class Contenido {

    private int id;
    private String titulo;
    private String texto;
    private Timestamp purga = null;
    private Timestamp creado = new Timestamp(System.currentTimeMillis());

    public int getId() {
        return id;
    }

    public Timestamp getModified() {
        return creado;
    }

    public Timestamp getCreated() {
        return creado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Timestamp getFechaPurga() {
        return purga;
    }

    public void setFechaPurga(Timestamp fecha_purga) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(creado);
        cal.add(Calendar.DAY_OF_WEEK, 1);
        this.purga = new Timestamp(cal.getTime().getTime());
    }
}