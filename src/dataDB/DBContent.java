package dataDB;

import conexionDB.ConexionDB;
import entidades.Content;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


/**
 * Created by cgallo on 06/06/20.
 */

public class DBContent {

    public static Content getById(int id) {
        Content content = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "SELECT id, news_agency_id, user_id, modified, created FROM content where id = ?"
            );
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                content = new Content(rs.getInt("id"), rs.getInt("news_agency_id"), rs.getInt("user_id"),
                        rs.getDate("modified"), rs.getDate("created"));

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
        return content;
    }

    public static void save(Content content) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String stmtSQL;
        int content_id = content.getId();
        try {
            stmt.setInt(1, content.getNewsAgencyId());
            stmt.setInt(2, content.getUserId());
            // stmt.setDate(3, content.getModified());
            // stmt.setDate(3, content.getCreated());
            if (content_id > 0) {
                stmtSQL = "insert into content(news_agency_id, user_id) values (?,?,?)";
            } else {
                stmtSQL = "update content set news_agency_id = ?, user_id = ?) where id = ?";
                stmt.setInt(4, content_id);
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

    public static void delete(Content content) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "delete from content where id = ?;");
            stmt.setInt(1, content.getId());
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
