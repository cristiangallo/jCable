package ar.cristiangallo.jCable.dataDB;

import ar.cristiangallo.jCable.conexionDB.ConexionDB;
import ar.cristiangallo.jCable.entidades.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBReserva {
    private static DBReserva instancia;

    private DBReserva() {}

    public static DBReserva getInstancia() {
        if (instancia == null) {
            instancia = new DBReserva();
        }
        return instancia;
    }

    public ArrayList<Reserva> all(Integer... parametros) {
        Integer user_id = parametros.length > 0 ? parametros[0] : null;
        ArrayList<Reserva> all = new ArrayList<Reserva>();
        Reserva reserva = null;
        Cable cable;
        User user;
        Agencia agencia;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sqlSelect = "select R.id, R.cable_id, R.user_id, titulo, texto, modificado, creado, urgencia, tema, purga, " +
                    "A.id as agencia_id, A.descripcion as desc_agencia, home_path, dias_purga, A.is_active as agencia_activa, " +
                    "email, nombre, apellido, password, is_staff, U.is_active as usuario_activo, is_superuser, last_login, " +
                    "U.creado as usuario_creado from reservas R inner join usuarios U on R.user_id=U.id " +
                    "inner join contenidos CO on CO.id=R.cable_id inner join cables CA on CO.id=CA.contenido_id " +
                    "inner join agencias A on A.id=CA.agencia_id";
            if (user_id!=null){
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        sqlSelect + " where user_id = ?;");
                stmt.setInt(1, user_id  );
            } else {
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(sqlSelect);
            }
            rs = stmt.executeQuery();
            while (rs != null && rs.next()) {
                agencia = new Agencia(rs.getInt("agencia_id"), rs.getString("desc_agencia"),
                        rs.getString("home_path"), rs.getInt("dias_purga"), rs.getBoolean("agencia_activa"));
                cable = new Cable(rs.getInt("cable_id"), rs.getString("titulo"), rs.getString("texto"),
                        rs.getTimestamp("modificado"), rs.getTimestamp("creado"), rs.getTimestamp("purga"),
                        agencia, rs.getString("urgencia"), rs.getString("tema"));
                user = new User(user_id, rs.getString("email"), rs.getString("password"),
                        rs.getString("nombre"), rs.getString("apellido"), rs.getBoolean("is_staff"),
                        rs.getBoolean("usuario_activo"), rs.getBoolean("is_superuser"),
                        rs.getTimestamp("last_login"), rs.getTimestamp("usuario_creado"));
                reserva = new Reserva(rs.getInt("id"), cable, user);
                all.add(reserva);
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

    public void save(Reserva reserva) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer reserva_id = reserva.getId();

        try {
            if (reserva_id != null) {
                System.out.println("update reserva");
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "update reservas set cable_id = ?, usuario_id = ? where id = ?",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setInt(3, reserva_id);
            } else {
                System.out.println("save reserva");
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "insert into reservas (cable_id, user_id) values (?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
            }
            stmt.setInt(1, reserva.getCable().getId());
            stmt.setInt(2, reserva.getUser().getId());
            stmt.execute();
            if (reserva_id == null) {
                rs = stmt.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    reserva.setId(rs.getInt(1));
                }
            }
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

    public void delete(Reserva reserva) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "delete from reservas where id = ?;");
            stmt.setInt(1, reserva.getId());
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
