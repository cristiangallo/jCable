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

    public ArrayList<Agencia> all() {
        return DBAgencia.getInstancia().all();
    }

    public ArrayList<Agencia> agenciasActivas() { return DBAgencia.getInstancia().agenciasActivas(); }

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

    public Agencia addAgencia(String descripcion, String home_path, Integer dias_purga, boolean isActive) throws appException {
        return new Agencia(descripcion, home_path, dias_purga, isActive);
    }
}
