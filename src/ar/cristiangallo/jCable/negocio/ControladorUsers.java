package ar.cristiangallo.jCable.negocio;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.dataDB.CatalogoUsers;
import ar.cristiangallo.jCable.entidades.User;

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

    public void login(User user) throws appException {
        user.login();
    }
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        int largo = 8;
        Random random = new Random();
        String alfanumerico = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder( largo );
        for( int i = 0; i < largo; i++ )
            sb.append( alfanumerico.charAt( random.nextInt(alfanumerico.length()) ) );

        System.out.println(sb.toString());

    }


    public void olvideMiPassword(String email) throws appException {
        System.out.println("controlador");
        catUsers.olvideMiPassword(email);
    }
}
