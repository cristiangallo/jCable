package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.conexionDB.ConexionDB;
import ar.cristiangallo.jCable.entidades.Agencia;
import ar.cristiangallo.jCable.entidades.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cgallo on 02/07/20.
 */

public class DBAgencia extends DBTable<Agencia> {

    private static DBAgencia instancia;

    private DBAgencia() {}

    public static DBAgencia getInstancia() {
        if (instancia == null) {
            instancia = new DBAgencia();
        }
        return instancia;
    }

    @Override
    public Agencia get(int id) throws appException {
        return null;
    }

    @Override
    public ArrayList<Agencia> all() throws appException {
        return null;
    }

    @Override
    public void save(Agencia agencia) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer user_id = agencia.getId();

        try {
            if (user_id > 0) {
                System.out.println("update agencia");
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "update agencias set descripcion = ?, home_path = ?, dias_purga = ?, is_active = ?, " +
                                "where id = ?", PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setInt(5, user_id);
            } else {
                System.out.println("save agencia");
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "insert into agencias(descripcion, home_path, dias_purga, is_active) values (?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
            }
            stmt.setString(1, agencia.getDescription());
            stmt.setString(2, agencia.getHomePath());
            stmt.setInt(3, agencia.getDiasPurga());
            stmt.setBoolean(4, agencia.getIsActive());
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
    public void delete(Agencia agencia) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement("delete from agencias where id = ?;");
            stmt.setInt(1, agencia.getId());
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
