package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.Agencia;
import ar.cristiangallo.jCable.entidades.Reglamento;
import ar.cristiangallo.jCable.entidades.User;
import ar.cristiangallo.jCable.negocio.ControladorAgencias;
import ar.cristiangallo.jCable.negocio.ControladorContenidos;
import ar.cristiangallo.jCable.negocio.ControladorUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="Administrar-reglamento", urlPatterns = {"/administrar-reglamento"})
public class AdministrarReglamentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        try {
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            } else if (!user.getIsSuperuser()) {
                throw new appException("No tienen acceso a modificar el reglamento.");

            }
            ControladorContenidos ctrlContenidos = new ControladorContenidos();
            request.setAttribute("reglamento", ctrlContenidos.getReglamento());

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("administrar-reglamento.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Integer dias_purga = null;
        Integer resultados_por_pagina = null;
        try {
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            } else if (!user.getIsSuperuser()) {
                throw new appException("No tienen acceso a modificar el reglamento.");

            }
            ControladorContenidos ctrlContenidos = new ControladorContenidos();
            Reglamento reglamento = ctrlContenidos.getReglamento();
            request.setAttribute("reglamento", reglamento);
            try {
                dias_purga = Integer.parseInt(request.getParameter("dias_purga"));
            } catch(NumberFormatException ex){
                throw new appException("El campo días de purga solo admite números enteros.");
            }
            try {
                resultados_por_pagina = Integer.parseInt(request.getParameter("resultados_por_pagina"));
            } catch(NumberFormatException ex){
                throw new appException("El campo resultados por página solo admite números enteros.");
            }

            reglamento.setDiasPurga(dias_purga);
            reglamento.setResultadosPorPagina(resultados_por_pagina);
            ctrlContenidos.saveReglamento();
            request.setAttribute("mensaje", "Los cambios se guardaron satisfactoriamente.");


        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("administrar-reglamento.jsp").forward(request, response);
    }
}
