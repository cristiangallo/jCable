package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.Cable;
import ar.cristiangallo.jCable.entidades.Contenido;
import ar.cristiangallo.jCable.entidades.User;
import ar.cristiangallo.jCable.negocio.ControladorContenidos;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReservarCableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return;
        }
        Integer contenido_id = Integer.parseInt(request.getParameter("contenido_id"));
        ControladorContenidos ctrlContenidos = new ControladorContenidos();
        try {
            Cable cable = ctrlContenidos.getCableById(contenido_id);
            ctrlContenidos.cable.toogleReserva(user);

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }



    }
}
