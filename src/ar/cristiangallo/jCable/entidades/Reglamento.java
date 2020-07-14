package ar.cristiangallo.jCable.entidades;

import ar.cristiangallo.jCable.conexionDB.ConexionDB;
import ar.cristiangallo.jCable.dataDB.CatalogoAgencias;
import ar.cristiangallo.jCable.dataDB.DBAgencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reglamento {
    // singleton
    private Integer id = 1;
    private Integer dias_purga = 7;
    private Integer resultados_por_pagina = 20;
    private static Reglamento instancia;

    private Reglamento(Integer id, Integer dias_purga, Integer resultados_por_pagina) {
        this.id = 1;
        this.dias_purga = dias_purga;
        this.resultados_por_pagina = resultados_por_pagina;
    }

    private Reglamento() {}

    public static Reglamento getInstance() {
        if(instancia == null){
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "select id, dias_purga, resultados_por_pagina from reglamento where id = 1;"
                );
                rs = stmt.executeQuery();
                if (rs != null && rs.next()) {
                    Integer id = (Integer) rs.getObject("id");
                    Integer dias_purga = (Integer) rs.getObject("dias_purga");
                    Integer resultados_por_pagina = (Integer) rs.getObject("resultados_por_pagina");

                    instancia = new Reglamento(id, dias_purga, resultados_por_pagina);

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
        return instancia;
    }



    private void delete() {};

    public void save() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            System.out.println("update reglamento");
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "update reglamento set dias_purga = ?, resultados_por_pagina = ? where id = 1;",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, dias_purga);
            stmt.setInt(2, resultados_por_pagina);
            stmt.execute();
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

    public Integer getDiasPurga() {
        return dias_purga;
    }

    public Integer getResultadoPorPagina() {
        return resultados_por_pagina;
    }

    public void setDiasPurga(Integer dias_purga) {
        this.dias_purga = dias_purga;
    }

    public void setResultadosPorPagina(Integer resultados_por_pagina) {
        this.resultados_por_pagina = resultados_por_pagina;
    }

    public Integer getId() {
        return id;
    }
}

