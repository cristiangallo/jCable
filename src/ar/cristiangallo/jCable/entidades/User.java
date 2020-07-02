package ar.cristiangallo.jCable.entidades;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.dataDB.DBUser;
// import org.apache.commons.mail.EmailException;
// import utiles.Mailer;
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

    public User(String email, String password, String password2, String first_name, String last_name) throws appException {
        if (email == "") throw new appException("Ingresa un email.");
        User user = DBUser.getInstancia().getUser(email);
        if (user != null) throw new appException("Existe un usuario registrado con ese email, intenta recuperar la " +
                "contraseña.");
        if (!password.equals(password2)) throw new appException("Las contraseñas no coinciden.");
        if (password == "") throw new appException("Ingresa una contraseña.");
        if (first_name == "") throw new appException("Ingresa tu nombre.");
        if (last_name == "") throw new appException("Ingresa tu apellido.");

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        DBUser.getInstancia().save(this);
        /*
        Mailer.send(email,"Activar cuenta en jCable","Hola " + first_name + ", haz <a href='http://127.0." +
                "0.1:8080/activar-usuario'>click aqui</a> para activar tu cuenta en jCable");
        */

        SendHtmlEmail.send(email, "Activar cuenta en jCable", "<html>Hola " + first_name + " , haz " +
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

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) throws appException {
        if (first_name == "") throw new appException("El campo nombre es obligatorio.");
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getFullName() {
        return first_name + " " + last_name;
    }

    public void setLastName(String last_name) throws appException {
        if (last_name == "") throw new appException("El campo apellido es obligatorio.");
        this.last_name = last_name;
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
        DBUser.getInstancia().save(this);
    }

    public void olvideMiPassword() throws appException {
        setPassword(RandomString.randomAlfaString(8));
        DBUser.getInstancia().save(this);
        // Mailer.send(email,"Nuevo password para jCable","Hola " + first_name + "!tu password provisorio es: " + password);

        SendHtmlEmail.send(email, "Nuevo password para jCable", "<html>Hola " + first_name + "!, " +
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
}
