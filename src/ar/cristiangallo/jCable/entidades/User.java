package ar.cristiangallo.jCable.entidades;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.dataDB.CatalogoContenido;
import ar.cristiangallo.jCable.dataDB.DBUser;
// import org.apache.commons.mail.EmailException;
// import utiles.Mailer;
import utiles.RandomString;
import utiles.SendHtmlEmail;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by cgallo on 05/06/20.
 */

public class User {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private boolean is_staff = false;
    private boolean is_active = false;
    private boolean is_superuser = false;
    private Timestamp last_login = null;
    private Timestamp creado = new Timestamp(System.currentTimeMillis());
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    // constructor para usuario guardado
    public User(int id, String email, String password, String nombre, String apellido, boolean is_staff,
                boolean is_active, boolean is_superuser, Timestamp last_login, Timestamp creado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.is_staff = is_staff;
        this.is_active = is_active;
        this.is_superuser = is_superuser;
        this.last_login = last_login;
        this.creado = creado;
    }

    public User(String email, String password, String password2, String nombre, String apellido) throws appException {
        if (email == "") throw new appException("Ingresa un email.");
        User user = DBUser.getInstancia().getUser(email);
        if (user != null) throw new appException("Existe un usuario registrado con ese email, intenta recuperar la " +
                "contraseña.");
        if (!password.equals(password2)) throw new appException("Las contraseñas no coinciden.");
        if (password == "") throw new appException("Ingresa una contraseña.");
        if (nombre == "") throw new appException("Ingresa tu nombre.");
        if (apellido == "") throw new appException("Ingresa tu apellido.");

        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        DBUser.getInstancia().save(this);
        /*
        Mailer.send(email,"Activar cuenta en jCable","Hola " + nombre + ", haz <a href='http://127.0." +
                "0.1:8080/activar-usuario'>click aqui</a> para activar tu cuenta en jCable");
        */

        SendHtmlEmail.send(email, "Activar cuenta en jCable", "<html>Hola " + nombre + " , haz " +
                "<a href='http://127.0.0.1:8080/activar-usuario?email=" + email + "'>click aqui</a> para activar " +
                "tu perfil en jCable.</html>");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws appException {
        if (password == "") throw new appException("El campo contraseña es obligatorio.");
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws appException {
        if (nombre == "") throw new appException("El campo nombre es obligatorio.");
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFullName() {
        return nombre + " " + apellido;
    }

    public void setApellido(String apellido) throws appException {
        if (apellido == "") throw new appException("El campo apellido es obligatorio.");
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws appException {
        if (email == "") throw new appException("El campo email es obligatorio.");
        this.email = email;
    }

    public boolean getIsStaff() {
        return is_staff;
    }

    public void setIsStaff(boolean is_staff) {
        this.is_staff = is_staff;
    }

    public boolean getIsActive() {
        return is_active;
    }

    public void setIsActive(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean getIsSuperuser() {
        return is_superuser;
    }

    public void setIsSuperuser(boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    public Timestamp getLastLogin() {
        return last_login;
    }

    // public void setLastLogin(java.sql.Date last_login) { this.last_login = last_login; }

    public Timestamp getCreado() { return creado; }

    // public void setDateJoined(java.sql.Date date_joined) { this.date_joined = date_joined; }

    public int getId() {
        return id;
    }

    public void login() throws appException {
        if (!this.getIsActive()) {
            throw new appException("Usuario inactivo.");
        }
        last_login = new Timestamp(System.currentTimeMillis());
        DBUser.getInstancia().save(this);
    }

    public void olvideMiPassword() throws appException {
        setPassword(RandomString.randomAlfaString(8));
        DBUser.getInstancia().save(this);
        // Mailer.send(email,"Nuevo password para jCable","Hola " + nombre + "!tu password provisorio es: " + password);

        SendHtmlEmail.send(email, "Nuevo password para jCable", "<html>Hola " + nombre + "!, " +
                "tu password provisorio es: "+ password + ".</html>");

    }

    public void activarUser() throws appException {
        if (!is_active){
            setIsActive(true);
            DBUser.getInstancia().save(this);

        } else {
            throw new appException("Este usuario ya fue activado.");
        }
    }

    public void changePassword(String password, String new_password, String new_password2) throws appException {
        if (!password.equals(this.password)) throw new appException("La contraseña actual es incorrecta.");
        if (new_password.equals(this.password)) throw new appException("La nueva contraseña es igual a tu contraseña actual.");
        if (!new_password.equals(new_password2)) throw new appException("Las contraseñas no coinciden.");
        if (new_password == "") throw new appException("Debes ingresar una nueva contraseña.");
        setPassword(new_password);
        DBUser.getInstancia().save(this);

    }

    public void save() {
        DBUser.getInstancia().save(this);
    }

    public void setReservas(ArrayList<Reserva> allReservas) {
        reservas = allReservas;
    }

    public Reserva getCableReservado(Cable cable) {
        for (Reserva reserva:reservas ) {
            System.out.println(reserva.getId());
            if (reserva.getCable().getId() == cable.getId()) {
                return reserva;
            }
        }
        return null;
    }

    public boolean toogleReserva(Cable cable) {
        Reserva reserva = getCableReservado(cable);
        if (reserva==null) {
            reserva = new Reserva(cable, this);
            reservas.add(reserva);
        } else {
            reservas.remove(reserva);
            reserva.delete();
            reserva = null;
        }
        return reserva == null ? false: true;
    }

    public String getAbsoluteURL() {
        return "/productores?productor_id=" + id;
    }

}
