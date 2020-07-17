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
    private ArrayList<Reserva> reservas;

    public Cable(int id, String titulo, String texto, Timestamp modificado, Timestamp creado, Timestamp purga,
                 Agencia agencia, String urgencia, String tema) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.modificado = modificado;
        this.creado = creado;
        this.purga = purga;
        this.agencia = agencia;
        this.urgencia = urgencia;
        this.tema = tema;
        reservas = DBReserva.getInstancia().all(id);
    }

    public Timestamp getFechaPurga() {
        return purga;
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

    public void toogleReserva(User user) {
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
    }
}
