package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.Agencia;
import ar.cristiangallo.jCable.entidades.User;
import ar.cristiangallo.jCable.negocio.ControladorAgencias;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="Eliminar-agencia", urlPatterns = {"/eliminar-agencia"})
public class EliminarAgenciaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User logued_user = (User) request.getSession().getAttribute("logued_user");

        try {
            if (logued_user == null) {
                response.sendRedirect("login.jsp");
                return;
            } else if (!logued_user.getIsSuperuser()) {
                throw new appException("No tienen acceso a la administración de agencias.");

            }
            ControladorAgencias ctrlAgencias = new ControladorAgencias();
            ctrlAgencias.delAgencia(Integer.parseInt(request.getParameter("agencia_id")));
            response.sendRedirect("administrar-agencias");
            return;

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("administrar-agencias.jsp").forward(request, response);

    }

}
