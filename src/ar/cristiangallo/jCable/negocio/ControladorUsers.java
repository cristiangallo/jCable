package ar.cristiangallo.jCable.negocio;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.dataDB.CatalogoContenido;
import ar.cristiangallo.jCable.dataDB.CatalogoUsers;
import ar.cristiangallo.jCable.entidades.User;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by cgallo on 06/06/2020.
 */

public class ControladorUsers {
    private String email;
    private String password;
    private User user;

    public ControladorUsers(){};
    private CatalogoUsers catUsers = CatalogoUsers.getInstance();
    private CatalogoContenido catContenidos = CatalogoContenido.getInstance();

    public ControladorUsers(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User getUser() throws appException {
        user = catUsers.getUser(email, password);
        user.setReservas(catContenidos.allReservas(user));
        return user;
    }

    public User getUser(String email) throws appException {
        User user = catUsers.getUser(email);
        return user;
    }

    public void login(User user) throws appException {
        user.login();
    }
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {

        System.out.println();
    }


    public void olvideMiPassword(String email) throws appException {
        System.out.println("controlador");
        catUsers.olvideMiPassword(email);
    }

    public void addUser(String email, String password, String password2, String nombre, String apellido) throws appException {
        catUsers.addUser(email, password, password2, nombre, apellido);
    }

    public void activarUser(User user) throws appException {
        catUsers.activarUser(user);
    }

    public void saveUser(User user) throws appException {
        catUsers.save(user);
    }

    public void changePasswordUser(User user, String password, String new_password, String new_password2) throws appException {
        catUsers.changePasswordUser(user, password, new_password, new_password2);
    }

    public ArrayList<User> allUsers() {
        return catUsers.all();
    }

    public User getUserById(int user_id) throws appException {
        return catUsers.getUserById(user_id);
    }
}
