package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.Contenido;

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

    public Contenido getContenidoById(int contenido_id) throws appException {
        return DBContenido.getInstancia().get(contenido_id);
    }



}
