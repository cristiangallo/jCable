package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.Contenido;
import ar.cristiangallo.jCable.entidades.Produccion;
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
        Contenido contenido = null;
        User logued_user = (User) request.getSession().getAttribute("logued_user");
        if (logued_user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        if (request.getParameter("cable_id")!=null) {
            try {
                contenido_id = Integer.parseInt(request.getParameter("cable_id"));
            } catch (NumberFormatException e) {
                response.sendRedirect("404.jsp");
                return;
            }
        } else if (request.getParameter("produccion_id")!=null) {
            try {
                contenido_id = Integer.parseInt(request.getParameter("produccion_id"));
            } catch (NumberFormatException e) {
                response.sendRedirect("404.jsp");
                return;
            }
        } else {
            response.sendRedirect("404.jsp");
            return;
        }
        try {
            ControladorContenidos ctrlContenidos = new ControladorContenidos();
            contenido = ctrlContenidos.getContenido(contenido_id);
            if (contenido==null) {
                response.sendRedirect("404.jsp");
                return;
            }
            request.setAttribute("contenido", contenido);

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (contenido instanceof Produccion && ((Produccion) contenido).getUser().getId()==logued_user.getId()) {
            request.getRequestDispatcher("editar-contenido.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("contenido.jsp").forward(request, response);
        }
    }
}
