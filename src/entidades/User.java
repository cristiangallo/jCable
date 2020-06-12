package entidades;

import java.util.Date;

/**
 * Created by cgallo on 05/06/20.
 */


public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private boolean is_staff;
    private boolean is_active;
    private boolean is_superuser;
    private Date last_login;
    private Date date_joined;

    // constructor para usuario guardado
    public User(int id, String email, String password, String first_name, String last_name, boolean is_staff,
                boolean is_active, boolean is_superuser, Date last_login, Date date_joined) {
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

    public java.sql.Date getLastLogin() {
        return (java.sql.Date) last_login;
    }

    public void setLastLogin(java.sql.Date last_login) {
        this.last_login = last_login;
    }

    public java.sql.Date getDateJoined() {
        return (java.sql.Date) date_joined;
    }

    public void setDateJoined(java.sql.Date date_joined) {
        this.date_joined = date_joined;
    }

    public int getId() {
        return id;
    }

}

