package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.Contenido;
import ar.cristiangallo.jCable.entidades.User;
import ar.cristiangallo.jCable.negocio.ControladorContenidos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="Administrar-contenido", urlPatterns = {"/contenidos"})
public class AdministrarContenidoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer contenido_id;
        User logued_user = (User) request.getSession().getAttribute("logued_user");
        if (request.getParameter("cable_id")!=null) {
            contenido_id = Integer.parseInt(request.getParameter("cable_id"));
        } else if (request.getParameter("produccion_id")!=null) {
            contenido_id = Integer.parseInt(request.getParameter("produccion_id"));
        } else {
            // raise 404
            return;
        }
        try {
            if (logued_user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            ControladorContenidos ctrlContenidos = new ControladorContenidos();
            Contenido contenido = ctrlContenidos.getContenido(contenido_id);

            request.setAttribute("contenido", contenido);

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("contenido.jsp").forward(request, response);
    }
}
