package ar.cristiangallo.jCable.negocio;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.dataDB.CatalogoUsers;
import ar.cristiangallo.jCable.entidades.User;

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

    public void login(User user) throws appException {
        user.login();
    }
    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        try {
            ControladorUsers ctrlUsers = new ControladorUsers();
            User user = ctrlUsers.getUser("crgallo@frro.utn.edu.ar", "123");
            user.login();
            System.out.println(user.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
