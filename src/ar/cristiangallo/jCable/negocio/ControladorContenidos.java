package ar.cristiangallo.jCable.negocio;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.dataDB.CatalogoContenido;
import ar.cristiangallo.jCable.entidades.*;

import java.util.ArrayList;

/**
 * Created by cgallo on 13/07/2020.
 */
public class ControladorContenidos {
    public Cable cable;
    public Produccion produccion;
    public User user;
    public Agencia agencia;

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

    public ArrayList<Contenido> allProducciones() {
        return catContenido.allProducciones(user);
    }

    public Contenido getContenido(Integer id) throws appException {
        Contenido contenido = catContenido.getContenido(id);
        if (contenido instanceof Produccion) {
            produccion = (Produccion) contenido;
        } else if (contenido instanceof Cable) {
            cable = (Cable) contenido;
        }
        return contenido;
    }

    public Agencia getAgencia(Integer id) throws appException {
        agencia = catContenido.getAgencia(id);
        return agencia;
    }

    public boolean toogleReserva() {
        return user.toogleReserva(cable);
    }

    public void deleteContenido() throws appException {
        if (produccion!=null) {
            produccion.delete();
        } else {
            throw new appException("Este contenido no puede ser eliminado.");
        }
    }

    public ArrayList<Contenido> allCables(Agencia agencia) {
        return catContenido.allCables(agencia);
    }
}
