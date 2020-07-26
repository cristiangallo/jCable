package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.Cable;
import ar.cristiangallo.jCable.entidades.User;
import ar.cristiangallo.jCable.negocio.ControladorContenidos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="Reservar-cable", urlPatterns = {"/reservar-cable"})
public class ReservarCableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControladorContenidos ctrlContenidos = new ControladorContenidos();
        User logued_user = (User) request.getSession().getAttribute("logued_user");

        if (logued_user == null) { return; }
        ctrlContenidos.user = logued_user;
        try {
            Cable cable = (Cable) ctrlContenidos.getContenido(Integer.parseInt(request.getParameter("cable_id")));
            ctrlContenidos.toogleReserva();
            request.setAttribute("contenido", cable);
            // logued_user.toogleReserva(cable);

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("estampilla.jsp").forward(request, response);

    }
}
