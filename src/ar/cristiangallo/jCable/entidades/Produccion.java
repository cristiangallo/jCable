package ar.cristiangallo.jCable.entidades;

import java.sql.Timestamp;

public class Produccion extends Contenido {
    private User user;
    private boolean publicado = false;

    public Produccion(int id, String titulo, String texto, Timestamp modificado, Timestamp creado, User user,
                      boolean publicado) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.modificado = modificado;
        this.creado = creado;
        this.user = user;
        this.publicado = publicado;
    }

    public String getAbsoluteURL () {
        return "/contenidos?produccion_id=" + id;
    }

    public User getUser(){
        return user;
    }
    public boolean getPublicado(){
        return publicado;
    }
}
