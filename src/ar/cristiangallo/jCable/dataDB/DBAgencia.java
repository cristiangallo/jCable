package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.conexionDB.ConexionDB;
import ar.cristiangallo.jCable.entidades.Agencia;
import ar.cristiangallo.jCable.entidades.Reglamento;
import ar.cristiangallo.jCable.entidades.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cgallo on 02/07/20.
 */

public class DBAgencia extends DBTable<Agencia> {

    private static DBAgencia instancia;
    private Reglamento reglamento;

    private DBAgencia() {}

    private DBAgencia(Reglamento reglamento) {
        this.reglamento = reglamento;
    }

    public static DBAgencia getInstancia() {
        if (instancia == null) {
            instancia = new DBAgencia(Reglamento.getInstance());
        }
        return instancia;
    }

    @Override
    public Agencia get(int agencia_id) throws appException {
        Agencia agencia = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select id, descripcion, home_path, dias_purga, is_active from agencias where id = ?"
            );
            stmt.setInt(1, agencia_id  );
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                Integer dias_purga = (Integer) rs.getObject("dias_purga");
                System.out.println("dias_purga:" + rs.getObject("dias_purga") + dias_purga);
                agencia = new Agencia(rs.getInt("id"), rs.getString("descripcion"), rs.getString("home_path"),
                        rs.getInt("dias_purga"), rs.getBoolean("is_active"));

            } else {
                throw new appException("Agencia no encontrada.");
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
        return agencia;
    }

    @Override
    public ArrayList<Agencia> all() {
        ArrayList<Agencia> all = new ArrayList<Agencia>();
        Agencia agencia = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select id, descripcion, home_path, dias_purga, is_active from agencias;"
            );
            rs = stmt.executeQuery();
            while (rs != null && rs.next()) {
                agencia = new Agencia(rs.getInt("id"), rs.getString("descripcion"), rs.getString("home_path"),
                        rs.getInt("dias_purga"), rs.getBoolean("is_active"));
                all.add(agencia);
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

    public ArrayList<Agencia> agenciasActivas() {
        ArrayList<Agencia> areActive = new ArrayList<Agencia>();
        Agencia agencia = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select id, descripcion, home_path, dias_purga, is_active from agencias where is_active = 1;"
            );
            rs = stmt.executeQuery();
            while (rs != null && rs.next()) {
                agencia = new Agencia(rs.getInt("id"), rs.getString("descripcion"), rs.getString("home_path"),
                        rs.getInt("dias_purga"), rs.getBoolean("is_active"));
                areActive.add(agencia);
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
        return areActive;
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
                        "update agencias set descripcion = ?, home_path = ?, dias_purga = ?, is_active = ? " +
                                "where id = ?", PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setInt(5, user_id);
            } else {
                System.out.println("save agencia");
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "insert into agencias(descripcion, home_path, dias_purga, is_active) values (?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
            }
            stmt.setString(1, agencia.getDescripcion());
            stmt.setString(2, agencia.getHomePath());
            stmt.setObject(3, agencia.getDiasPurga(), java.sql.Types.INTEGER);
            stmt.setBoolean(4, agencia.getIsActive());
            stmt.execute();
            rs = stmt.getGeneratedKeys();
            if (user_id == 0) {
                if(rs != null && rs.next()){
                    agencia.setId(rs.getInt(1));
                }
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
