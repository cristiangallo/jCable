package dataDB;

import conexionDB.ConexionDB;
import entidades.NewsAgency;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by cgallo on 06/06/20.
 */

public class DBNewsAgency {

    public static NewsAgency getById(int id) {
        NewsAgency news_agency = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "SELECT id, description, home_path, is_active FROM news_agency where id = ?"
            );
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                news_agency = new NewsAgency(rs.getInt("id"), rs.getString("description"),
                        rs.getString("home_path"), rs.getBoolean("is_active"));

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
        return news_agency;
    }

    public static void save(NewsAgency news_agency) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String stmtSQL;
        int news_agency_id = news_agency.getId();
        try {
            stmt.setString(1, news_agency.getDescription());
            stmt.setString(2, news_agency.getHomePath());
            stmt.setBoolean(3, news_agency.getIsActive());
            if (news_agency_id > 0) {
                stmtSQL = "insert into news_agency(description, home_path, is_active) values (?,?,?)";
            } else {
                stmtSQL = "update news_agency set description = ?, home_path = ?, is_active = ?) where id = ?";
                stmt.setInt(4, news_agency_id);
            }
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(stmtSQL,
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
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

    public static void delete(NewsAgency news_agency) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "delete from news_agency where id = ?;");
            stmt.setInt(1, news_agency.getId());
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
