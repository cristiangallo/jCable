package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.appExceptions.appException;

import java.util.ArrayList;

/**
 * Created by cgallo on 01/07/20.
 */
public abstract class DBTable<Clase> {

    public abstract Clase get(int id) throws appException;

    public abstract ArrayList<Clase> all() throws appException;

    public abstract void save(Clase instancia) throws appException;

    public abstract void delete(Clase instancia) throws appException;

}
