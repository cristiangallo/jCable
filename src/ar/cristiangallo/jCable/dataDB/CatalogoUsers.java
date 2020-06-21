package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.User;

/**
 * Created by cgallo on 06/06/20.
 */

public class CatalogoUsers {
    // singleton
    private static CatalogoUsers instancia;

    public static CatalogoUsers getInstance() {
        if(instancia == null){
            instancia = new CatalogoUsers();
        }
        return instancia;
    }

    // hago privado el constructor para que nadie pueda instanciarlo
    private CatalogoUsers() {}

    public User getUser(String email, String password) throws appException  {
        User user = DBUser.getUser(email, password);
        if (user == null) throw new appException("Nombre de usuario o contraseña inválida.");
        return user;
    }

    public User login(String email, String password) throws appException {
        User user = this.getUser(email, password);
        user.login();
        return user;
    }

    public void save(User user) {
        DBUser.save(user);
    }

    public void olvideMiPassword(String email) throws appException {
        User user = DBUser.getUser(email);
        if (user == null) throw new appException("No te has registrado con este email.");
        user.olvideMiPassword();
    }

    public User addUser(String email, String password, String password2, String first_name, String last_name) throws appException {
        User user = DBUser.getUser(email);

        if (user != null) throw new appException("Existe un usuario registrado con ese email, intenta recuperar la " +
                "contraseña.");
        if (!password.equals(password2)) throw new appException("Las contraseñas no coinciden.");
        if (password == null) throw new appException("Ingresa una contraseña.");
        if (first_name == null) throw new appException("Ingresa tu nombre.");
        if (last_name == null) throw new appException("Ingresa tu apellido.");
        return new User(email, password, first_name, last_name);
    }
}
