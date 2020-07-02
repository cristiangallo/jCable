package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.conexionDB.ConexionDB;
import ar.cristiangallo.jCable.entidades.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cgallo on 06/06/20.
 */

public class DBUser extends DBTable<User> {

    private static DBUser instancia;

    private DBUser() {}

    public static DBUser getInstancia() {
        if (instancia == null) {
            instancia = new DBUser();
        }
        return instancia;
    }

    @Override
    public ArrayList<User> all() {
        ArrayList<User> all = new ArrayList<User>();
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select id, email, password, first_name, last_name, is_staff, is_active, " +
                            "is_superuser, last_login, date_joined from users"
            );
            rs = stmt.executeQuery();
            while (rs != null && rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("email"),
                        rs.getString("password"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getBoolean("is_staff"),
                        rs.getBoolean("is_active"), rs.getBoolean("is_superuser"),
                        rs.getTimestamp("last_login"), rs.getTimestamp("date_joined"));
                all.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.cancel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionDB.getInstancia().releaseConexion();
        }
        return all;
    }

    @Override
    public User get(int user_id) {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select id, email, password, first_name, last_name, is_staff, is_active, " +
                            "is_superuser, last_login, date_joined from user where id = ?"
            );
            stmt.setInt(1, user_id);
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("email"),
                        rs.getString("password"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getBoolean("is_staff"),
                        rs.getBoolean("is_active"), rs.getBoolean("is_superuser"),
                        rs.getTimestamp("last_login"), rs.getTimestamp("date_joined"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.cancel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionDB.getInstancia().releaseConexion();
        }
        return user;
    }

    public static User getUser(String email, String password) {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select id, email, password, first_name, last_name, is_staff, is_active, " +
                            "is_superuser, last_login, date_joined from users where email = ? and password = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("email"),
                        rs.getString("password"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getBoolean("is_staff"),
                        rs.getBoolean("is_active"), rs.getBoolean("is_superuser"),
                        rs.getTimestamp("last_login"), rs.getTimestamp("date_joined"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.cancel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionDB.getInstancia().releaseConexion();
        }
        return user;
    }

    public static User getUser(String email) {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select id, email, password, first_name, last_name, is_staff, is_active, " +
                            "is_superuser, last_login, date_joined from users where email = ?"
            );
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("email"),
                        rs.getString("password"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getBoolean("is_staff"),
                        rs.getBoolean("is_active"), rs.getBoolean("is_superuser"),
                        rs.getTimestamp("last_login"), rs.getTimestamp("date_joined"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.cancel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionDB.getInstancia().releaseConexion();
        }
        return user;
    }

    @Override
    public void save(User user) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer user_id = user.getId();

        try {
            if (user_id > 0) {
                System.out.println("update user");
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "update users set email = ?, password = ?, first_name = ?, last_name = ?, is_staff = ?, " +
                                "is_active = ?, is_superuser = ?, last_login = ?, date_joined = ? where id = ?",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setInt(10, user_id);
            } else {
                System.out.println("save user");
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "insert into users(email, password, first_name, last_name, is_staff, is_active, " +
                                "is_superuser, last_login, date_joined) values (?,?,?,?,?,?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
            }
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setBoolean(5, user.getIsStaff());
            stmt.setBoolean(6, user.getIsActive());
            stmt.setBoolean(7, user.getIsSuperuser());
            stmt.setTimestamp(8, user.getLastLogin());
            stmt.setTimestamp(9, user.getDateJoined());
            stmt.execute();
            rs = stmt.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.cancel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionDB.getInstancia().releaseConexion();
        }
    }

    @Override
    public void delete(User user) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "delete from users where id = ?;");
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.cancel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionDB.getInstancia().releaseConexion();
        }
    }

}
