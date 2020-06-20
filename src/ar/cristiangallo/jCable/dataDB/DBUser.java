package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.conexionDB.ConexionDB;
import ar.cristiangallo.jCable.entidades.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cgallo on 06/06/20.
 */

public class DBUser {

    public static User getUser(String email, String password) {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select id, email, password, first_name, last_name, is_staff, is_active, " +
                            "is_superuser, last_login, date_joined from user where email = ? and password = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("email"),
                        rs.getString("password"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getBoolean("is_staff"),
                        rs.getBoolean("is_active"), rs.getBoolean("is_superuser"),
                        rs.getDate("last_login"), rs.getDate("date_joined"));

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
                            "is_superuser, last_login, date_joined from user where email = ?"
            );
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("email"),
                        rs.getString("password"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getBoolean("is_staff"),
                        rs.getBoolean("is_active"), rs.getBoolean("is_superuser"),
                        rs.getDate("last_login"), rs.getDate("date_joined"));

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

    public static void save(User user) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String stmtSQL;
        Integer user_id = user.getId();

        try {
            if (user_id != null) {
                System.out.println("update");
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "update user set email = ?, password = ?, first_name = ?, last_name = ?, is_staff = ?, " +
                                "is_active = ?, is_superuser = ?, last_login = ?, date_joined = ? where id = ?",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setInt(10, user_id);
            } else {
                System.out.println("save");
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "insert into user(email, password, first_name, last_name, is_staff, is_active, " +
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
            stmt.setDate(8, user.getLastLogin());
            stmt.setDate(9, user.getDateJoined());

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

    public static void delete(User user) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "delete from user where id = ?;");
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
