package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.entidades.User;
import ar.cristiangallo.jCable.negocio.ControladorAgencias;
import ar.cristiangallo.jCable.negocio.ControladorContenidos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="Home", urlPatterns = {"/index", ""})
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // int offset = Integer.parseInt(request.getParameter("offset"));
        ControladorAgencias ctrlAgencias = new ControladorAgencias();
        ControladorContenidos ctrlContenidos = new ControladorContenidos();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.setAttribute("agenciasActivas", ctrlAgencias.agenciasActivas());
        request.setAttribute("contenidos", ctrlContenidos.allContenidos());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
