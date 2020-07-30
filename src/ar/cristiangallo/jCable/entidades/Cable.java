package ar.cristiangallo.jCable.entidades;

import ar.cristiangallo.jCable.dataDB.DBReserva;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class Cable extends Contenido {
    private Agencia agencia;
    private String urgencia;
    private String tema;
    private Timestamp purga = null;
    private Reserva reserva = null;
    // private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public Cable(int id, String titulo, String texto, Timestamp modificado, Timestamp creado, Timestamp purga,
                 Agencia agencia, String urgencia, String tema, Reserva reserva) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.modificado = modificado;
        this.creado = creado;
        this.purga = purga;
        this.agencia = agencia;
        this.urgencia = urgencia;
        this.tema = tema;
        this.reserva = reserva;
        // reservas = DBReserva.getInstancia().all(this);
    }

    public Timestamp getFechaPurga() {
        return purga;
    }

    public String getAbsoluteURL () { return "/contenidos?cable_id=" + id; }

    @Override
    public void delete() {

    }

    public void setFechaPurga(Timestamp fecha_purga) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(creado);
        cal.add(Calendar.DAY_OF_WEEK, 1);
        this.purga = new Timestamp(cal.getTime().getTime());
    }

    public Agencia getAgencia() {
        return agencia;
    }


    /*
    public boolean toogleReserva(User user) {
        if (!getReservado()) {
            reserva = new Reserva(this, user);
        } else {
            reserva.delete();
            reserva = null;
        }
        return reserva == null ? false: true;
    }

    public boolean getReservado() { return reserva != null ? true:false; }
     */
    public String getTema() { return tema; }

    public String getUrgencia() { return urgencia; }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
