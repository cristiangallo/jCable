package ar.cristiangallo.jCable.entidades;

import java.sql.Timestamp;
import java.util.Calendar;

public class Cable extends Contenido {
    private Agencia agencia;
    private String urgencia;
    private String tema;
    private Timestamp purga = null;

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
}
