package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.User;

import java.util.ArrayList;

/**
 * Created by cgallo on 06/06/20.
 */

public class CatalogoUsers {
    // singleton
    private static CatalogoUsers instancia;

    public static CatalogoUsers getInstance() {
        if (instancia == null) { instancia = new CatalogoUsers(); }
        return instancia;
    }

    // hago privado el constructor para que nadie pueda instanciarlo
    private CatalogoUsers() {}

    public User getUser(String email, String password) throws appException  {
        User user = DBUser.getInstancia().getUser(email, password);
        if (user == null) throw new appException("Nombre de usuario o contraseña inválida.");
        return user;
    }

    public User login(String email, String password) throws appException {
        User user = this.getUser(email, password);
        user.login();
        return user;
    }

    public void save(User user) {
        user.save();
    }

    public void olvideMiPassword(String email) throws appException {
        User user = getUser(email);
        if (user == null) throw new appException("No te has registrado con este email.");
        user.olvideMiPassword();
    }

    public User addUser(String email, String password, String password2, String nombre, String apellido) throws appException {
        return new User(email, password, password2, nombre, apellido);
    }

    public User getUser(String email) {
        return DBUser.getInstancia().getUser(email);
    }

    public void activarUser(User user) throws appException {
        user.activarUser();
    }

    public void changePasswordUser(User user, String password, String new_password, String new_password2) throws appException {
        user.changePassword(password, new_password, new_password2);
    }

    public ArrayList<User> all() { return DBUser.getInstancia().all(); }

    public User getUserById(int user_id) { return DBUser.getInstancia().get(user_id); }

}
