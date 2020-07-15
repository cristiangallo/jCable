package ar.cristiangallo.jCable.negocio;

import ar.cristiangallo.jCable.dataDB.CatalogoContenido;
import ar.cristiangallo.jCable.entidades.Contenido;
import ar.cristiangallo.jCable.entidades.Reglamento;

import java.util.ArrayList;

/**
 * Created by cgallo on 13/07/2020.
 */
public class ControladorContenidos {
    public ControladorContenidos() {};

    private CatalogoContenido catContenido = CatalogoContenido.getInstance();
    private Reglamento reglamento = Reglamento.getInstance();

    public Reglamento getReglamento() {
        System.out.println(reglamento.getId());
        return reglamento;
    }

    public void saveReglamento() {
        reglamento.save();
    }

    public ArrayList<Contenido> allContenidos() {
        return catContenido.all();
    }
}
