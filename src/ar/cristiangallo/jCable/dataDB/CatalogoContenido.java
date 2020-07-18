package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.Cable;
import ar.cristiangallo.jCable.entidades.Contenido;
import ar.cristiangallo.jCable.entidades.User;

import java.util.ArrayList;

/**
 * Created by cgallo on 13/07/2020.
 */
public class CatalogoContenido {
    // singleton
    private CatalogoContenido() {}

    private static CatalogoContenido instancia;

    public static CatalogoContenido getInstance() {
        if(instancia == null){
            instancia = new CatalogoContenido();
        }
        return instancia;
    }

    public Cable getCableById(int cable_id) throws appException {
        return (Cable) DBContenido.getInstancia().get(cable_id);
    }

    public ArrayList<Contenido> all(User logued_user) {
        return DBContenido.getInstancia().all(logued_user);
    }
}
