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

    public ArrayList<Agencia> allAgencias() {
        return catAgencias.all();
    }

    public ArrayList<Agencia> agenciasActivas() {
        return catAgencias.agenciasActivas();
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

    public Agencia addAgencia(String descripcion, String home_path, Integer dias_purga, boolean isActive) throws appException {

        return catAgencias.addAgencia(descripcion, home_path, dias_purga, isActive);
    }

}
