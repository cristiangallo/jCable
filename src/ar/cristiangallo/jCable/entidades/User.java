package ar.cristiangallo.jCable.entidades;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.dataDB.DBUser;
import org.apache.commons.mail.EmailException;
import utiles.Mailer;
import utiles.RandomString;
import utiles.SendHtmlEmail;

import java.sql.Timestamp;

/**
 * Created by cgallo on 05/06/20.
 */

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private boolean is_staff = false;
    private boolean is_active = false;
    private boolean is_superuser = false;
    private Timestamp last_login = null;
    private Timestamp date_joined = new Timestamp(System.currentTimeMillis());

    // constructor para usuario guardado
    public User(int id, String email, String password, String first_name, String last_name, boolean is_staff,
                boolean is_active, boolean is_superuser, Timestamp last_login, Timestamp date_joined) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.is_staff = is_staff;
        this.is_active = is_active;
        this.is_superuser = is_superuser;
        this.last_login = last_login;
        this.date_joined = date_joined;
    }

    public User(String email, String password, String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;

        DBUser.save(this);
        /*
        Mailer.send(email,"Activar cuenta en jCable","Hola " + first_name + ", haz <a href='http://127.0." +
                "0.1:8080/activar-usuario'>click aqui</a> para activar tu cuenta en jCable");
        */

        SendHtmlEmail.send(email, "Activar cuenta en jCable", "<html>Hola " + first_name + " , haz " +
                    "<a href='http://127.0.0.1:8080/activar-usuario/" + email + "'>click aqui</a> para activar tu perfil en " +
                    "jCable.</html>");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    public Timestamp getDateJoined() { return date_joined; }

    // public void setDateJoined(java.sql.Date date_joined) { this.date_joined = date_joined; }

    public int getId() {
        return id;
    }

    public void login() throws appException {
        if (!this.getIsActive()) {
            throw new appException("Usuario inactivo.");
        }
        last_login = new Timestamp(System.currentTimeMillis());
        DBUser.save(this);
    }

    public void olvideMiPassword() {
        setPassword(RandomString.randomAlfaString(8));
        DBUser.save(this);
        Mailer.send(email,"Nuevo password para jCable","Hola " + first_name + "!tu password provisorio es: " + password);

        SendHtmlEmail.send(email, "Nuevo password para jCable", "<html>Hola " + first_name + "!, " +
                "tu password provisorio es: "+ password + ".</html>");

    }
}
