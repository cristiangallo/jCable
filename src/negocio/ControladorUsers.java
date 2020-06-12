package negocio;

import appExceptions.appException;
import dataDB.CatalogoUsers;
import entidades.User;

/**
 * Created by cgallo on 06/06/2020.
 */

public class ControladorUsers {
    public ControladorUsers(){};

    private CatalogoUsers catUsers = CatalogoUsers.getInstance();

    public User login(String email, String password) throws appException {
        User user = catUsers.login(email, password);
        return user;
    }
    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        try {
            ControladorUsers ctrlUsers = new ControladorUsers();
            User user = ctrlUsers.login("crgallo@frro.utn.edu.ar", "123");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
