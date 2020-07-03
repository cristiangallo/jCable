package ar.cristiangallo.jCable.negocio;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.dataDB.CatalogoAgencias;
import ar.cristiangallo.jCable.entidades.Agencia;

import java.util.ArrayList;

/**
 * Created by cgallo on 02/07/2020.
 */
public class ControladorAgencias {
    public ControladorAgencias(){};

    private CatalogoAgencias catAgencias = CatalogoAgencias.getInstance();

    public ArrayList<Agencia> allAgencias() throws appException {
        return catAgencias.all();
    }

    public Agencia getAgenciaById(int agencia_id) throws appException {
        return catAgencias.getAgenciaById(agencia_id);
    }

    public void saveAgencia(Agencia agencia) {
        catAgencias.save(agencia);
    }

    public void delAgencia(int agencia_id) throws appException {
        catAgencias.delAgencia(agencia_id);
    }
}
