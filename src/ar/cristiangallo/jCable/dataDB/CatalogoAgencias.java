package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.Agencia;

import java.util.ArrayList;

/**
 * Created by cgallo on 02/07/2020.
 */
public class CatalogoAgencias {
    // singleton
    private static CatalogoAgencias instancia;

    public static CatalogoAgencias getInstance() {
        if(instancia == null){
            instancia = new CatalogoAgencias();
        }
        return instancia;
    }

    // hago privado el constructor para que nadie pueda instanciarlo
    private CatalogoAgencias() {}

    public ArrayList<Agencia> all() throws appException {
        return DBAgencia.getInstancia().all();
    }

    public Agencia getAgenciaById(int agencia_id) throws appException {
        return DBAgencia.getInstancia().get(agencia_id);
    }

    public void save(Agencia agencia) {
        agencia.save();
    }

    public void delAgencia(int agencia_id) throws appException {
        Agencia agencia = getAgenciaById(agencia_id);
        agencia.delete();
    }
}
