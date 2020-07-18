package ar.cristiangallo.jCable.entidades;

import ar.cristiangallo.jCable.dataDB.DBAgencia;
import ar.cristiangallo.jCable.dataDB.DBReserva;

public class Reserva {
    Integer id;
    Cable cable;
    User user;

    public Reserva(Integer id, Cable cable, User user) {
        this.id = id;
        this.cable = cable;
        this.user = user;
    }

    public Reserva(Cable cable, User user) {
        this.cable = cable;
        this.user = user;
        DBReserva.getInstancia().save(this);
    }

    public Reserva() {

    }

    public User getUser() {
        return user;
    }

    public Cable getCable() {
        return cable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void delete() {
        DBReserva.getInstancia().delete(this);
    }
}
