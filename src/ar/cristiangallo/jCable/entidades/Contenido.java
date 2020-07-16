package ar.cristiangallo.jCable.entidades;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Contenido {

    protected int id;
    protected String titulo;
    protected String texto;
    protected Timestamp modificado = new Timestamp(System.currentTimeMillis());
    protected Timestamp creado = new Timestamp(System.currentTimeMillis());

    public int getId() {
        return id;
    }

    public final Timestamp getModified() {
        return creado;
    }

    public String getDateTimeModificada () {
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(modificado);
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

}