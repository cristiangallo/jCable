package ar.cristiangallo.jCable.negocio;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.dataDB.CatalogoUsers;
import ar.cristiangallo.jCable.entidades.User;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by cgallo on 06/06/2020.
 */

public class ControladorUsers {
    public ControladorUsers(){};
    private CatalogoUsers catUsers = CatalogoUsers.getInstance();

    public User getUser(String email, String password) throws appException {
        User user = catUsers.getUser(email, password);
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

    public void addUser(String email, String password, String password2, String first_name, String last_name) throws appException {
        catUsers.addUser(email, password, password2, first_name, last_name);
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
}
