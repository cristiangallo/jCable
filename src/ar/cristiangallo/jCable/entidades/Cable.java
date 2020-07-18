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
    private boolean pseudoReservado = false;
    private ArrayList<Reserva> reservas;

    public Cable(int id, String titulo, String texto, Timestamp modificado, Timestamp creado, Timestamp purga,
                 Agencia agencia, String urgencia, String tema, boolean pseudoReservado) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.modificado = modificado;
        this.creado = creado;
        this.purga = purga;
        this.agencia = agencia;
        this.urgencia = urgencia;
        this.tema = tema;
        this.pseudoReservado = pseudoReservado;
        reservas = DBReserva.getInstancia().all(id);
    }

    public Timestamp getFechaPurga() {
        return purga;
    }

    public String getAbsoluteURL () {
        return "/cables?cable_id=" + id;
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

    public boolean toogleReserva(User user) {
        boolean estaba_reservado = false;
        for (Reserva reserva : reservas) {
            if (reserva.getUser() == user) {
                estaba_reservado = true;
                reservas.remove(reserva);
                reserva.delete();
                break;
            }
        }
        if (!estaba_reservado) {
            Reserva reserva = new Reserva(this, user);
            reservas.add(reserva);
        }
        return !estaba_reservado;
    }

    public String getTema() { return tema; }

    public String getUrgencia() { return urgencia; }

    public boolean getPseudoReservado() { return pseudoReservado; }
}
