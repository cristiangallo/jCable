package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.Agencia;
import ar.cristiangallo.jCable.entidades.User;
import ar.cristiangallo.jCable.negocio.ControladorContenidos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="Cables-agencias", urlPatterns = {"/cables-agencias"})
public class ListarCablesServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer agencia_id = null;
        User logued_user = (User) request.getSession().getAttribute("logued_user");
        ControladorContenidos ctrlContenidos = new ControladorContenidos();

        if (logued_user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        if (request.getParameter("agencia_id")!=null) {
            try {
                agencia_id = Integer.parseInt(request.getParameter("agencia_id"));
                Agencia agencia = ctrlContenidos.getAgencia(agencia_id);
                request.setAttribute("contenidos", ctrlContenidos.allCables(agencia));
            } catch (NumberFormatException e) {
                response.sendRedirect("404.jsp");
                return;
            } catch (appException e) {
                request.setAttribute("error", e);
            }
        }



        request.getRequestDispatcher("listar-contenidos.jsp").forward(request, response);
    }
}
