package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.*;

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

    public Contenido getContenido(int id) throws appException {
        return DBContenido.getInstancia().get(id);
    }

    public ArrayList<Contenido> all(User logued_user) {
        return DBContenido.getInstancia().all(logued_user);
    }

    public ArrayList<Reserva> allReservas(User logued_user) {
        return DBReserva.getInstancia().all(logued_user);
    }

    public ArrayList<Contenido> allProducciones(User logued_user) {
        return DBContenido.getInstancia().allProducciones(logued_user);
    }

    public ArrayList<Contenido> allCables(Agencia agencia) {
        return DBContenido.getInstancia().allCables(agencia);
    }


    public Agencia getAgencia(Integer id) throws appException {
        return DBAgencia.getInstancia().get(id);
    }
}
