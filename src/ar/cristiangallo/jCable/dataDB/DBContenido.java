package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.conexionDB.ConexionDB;
import ar.cristiangallo.jCable.entidades.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBContenido extends DBTable<Contenido> {
    private static DBContenido instancia;

    private DBContenido() {}

    public static DBContenido getInstancia() {
        if (instancia == null) {
            instancia = new DBContenido();
        }
        return instancia;
    }

    @Override
    public Contenido get(int id) throws appException {
        Agencia agencia;
        User user;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select CO.*, CA.contenido_id as cable_id, urgencia, tema, purga, PR.contenido_id as " +
                            "produccion_id, publicado, A.id as agencia_id, A.descripcion as desc_agencia, " +
                            "home_path, dias_purga, A.is_active as agencia_activa, U.id as user_id, email, nombre, " +
                            "apellido, password, is_staff, U.is_active as usuario_activo, is_superuser from " +
                            "contenidos CO left join cables CA on CO.id=CA.contenido_id left join producciones PR on CO.id=PR.contenido_id " +
                            "left join agencias A on A.id=CA.agencia_id, last_login, U.creado as usuario_creado left join usuarios U on U.id=PR.usuario_id " +
                            "where CO.id = ?;"
            );
            stmt.setInt(1, id  );
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                Integer agencia_id = (Integer) rs.getObject("agencia_id");
                if (agencia_id > 0) {
                    agencia = new Agencia(agencia_id, rs.getString("desc_agencia"),
                            rs.getString("home_path"), rs.getInt("dias_purga"), rs.getBoolean("agencia_activa"));
                    Cable cable = new Cable(rs.getInt("id"), rs.getString("titulo"), rs.getString("texto"),
                            rs.getTimestamp("modificado"), rs.getTimestamp("creado"), rs.getTimestamp("purga"),
                            agencia, rs.getString("urgencia"), rs.getString("tema"));
                    return cable;
                }
                Integer user_id = (Integer) rs.getObject("user_id");
                if (user_id > 0) {
                    user = new User(user_id, rs.getString("email"), rs.getString("password"),
                            rs.getString("nombre"), rs.getString("apellido"), rs.getBoolean("is_staff"),
                            rs.getBoolean("usuario_activo"), rs.getBoolean("is_superuser"),
                            rs.getTimestamp("last_login"), rs.getTimestamp("usuario_creado"));
                    Produccion produccion = new Produccion(rs.getInt("id"), rs.getString("titulo"),
                            rs.getString("texto"), rs.getTimestamp("modificado"), rs.getTimestamp("creado"),
                            user, rs.getBoolean("publicado"));
                    return produccion;

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
        return null;
    }

    @Override
    public ArrayList all() throws appException {
        return null;
    }

    @Override
    public void save(Contenido instancia) throws appException {

    }

    @Override
    public void delete(Contenido instancia) throws appException {

    }
}
