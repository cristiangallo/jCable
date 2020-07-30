package ar.cristiangallo.jCable.entidades;

import ar.cristiangallo.jCable.appExceptions.appException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public abstract class Contenido {

    protected int id;
    protected String titulo;
    protected String texto;
    protected Timestamp modificado = new Timestamp(System.currentTimeMillis());
    protected Timestamp creado = new Timestamp(System.currentTimeMillis());

    public int getId() {
        return id;
    }

    public abstract String getAbsoluteURL ();

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

    public String getTextoHTML() {
        String texto_html = "";
        for (String parrafo : this.texto.split("\n")) {
            texto_html += "<p>" + parrafo + "</p>";
        }
        return texto_html;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public final String getBajada() {
        return "<p>" + texto.split("\n")[0] + "</p>";
    }

    public abstract void delete() throws appException;
}