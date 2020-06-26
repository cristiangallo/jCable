package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.User;
import ar.cristiangallo.jCable.negocio.ControladorUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="Administrar-usuarios", urlPatterns = {"/administrar-usuarios"})
public class AdministrarUsuariosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        try {
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            } else if (!user.getIsSuperuser()) {
                throw new appException("No tienen acceso a la administración de perfiles.");

            }
            ControladorUsers ctrlUsers = new ControladorUsers();
            request.setAttribute("allUsers", ctrlUsers.allUsers());
        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("administrar-usuarios.jsp").forward(request, response);
    }

}
