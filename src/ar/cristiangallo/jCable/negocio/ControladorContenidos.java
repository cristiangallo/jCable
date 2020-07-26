package ar.cristiangallo.jCable.negocio;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.dataDB.CatalogoContenido;
import ar.cristiangallo.jCable.entidades.Cable;
import ar.cristiangallo.jCable.entidades.Contenido;
import ar.cristiangallo.jCable.entidades.Reglamento;
import ar.cristiangallo.jCable.entidades.User;

import java.util.ArrayList;

/**
 * Created by cgallo on 13/07/2020.
 */
public class ControladorContenidos {
    public Cable cable;
    public User user;
    public ControladorContenidos() {};

    private CatalogoContenido catContenido = CatalogoContenido.getInstance();
    private Reglamento reglamento = Reglamento.getInstance();

    public Reglamento getReglamento() {
        return reglamento;
    }

    public void saveReglamento() {
        reglamento.save();
    }

    public ArrayList<Contenido> allContenidos(User logued_user) {
        return catContenido.all(logued_user);
    }

    public Cable getCableById(Integer cable_id) throws appException {
        cable = catContenido.getCableById(cable_id);
        return cable;
    }
}
