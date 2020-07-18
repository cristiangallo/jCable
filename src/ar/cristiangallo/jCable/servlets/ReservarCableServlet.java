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
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) { return; }
        ControladorContenidos ctrlContenidos = new ControladorContenidos();
        Integer cable_id = Integer.parseInt(request.getParameter("cable_id"));

        try {
            Cable cable = ctrlContenidos.getCableById(cable_id);
            cable.toogleReserva(user);

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        boolean reservado = ctrlContenidos.cable.toogleReserva(user);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (reservado) {
                out.println("<span><i class=\"fa fa-star star\"></i></span>");
            } else {
                out.println("<span><i class=\"fa fa-star\"></i></span>");
            }
        }
    }
}
