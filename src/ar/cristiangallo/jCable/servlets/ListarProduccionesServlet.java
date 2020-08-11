package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.User;
import ar.cristiangallo.jCable.negocio.ControladorContenidos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="Listar-producciones", urlPatterns = {"/listar-producciones"})
public class ListarProduccionesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User logued_user = (User) request.getSession().getAttribute("logued_user");
        if (logued_user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        ControladorContenidos ctrlContenidos = new ControladorContenidos();
        ctrlContenidos.user = logued_user;
        request.setAttribute("contenidos", ctrlContenidos.allProducciones());

        request.getRequestDispatcher("listar-contenidos.jsp").forward(request, response);
    }

}
