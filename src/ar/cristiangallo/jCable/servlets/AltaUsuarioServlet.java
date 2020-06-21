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

@WebServlet(name="AltaUsuario", urlPatterns = {"/alta-usuario"})
public class AltaUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        ControladorUsers ctrlUsers = new ControladorUsers();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");

        try {
            ctrlUsers.addUser(email, password, password2, first_name, last_name);
            request.setAttribute("email", email);
            request.setAttribute("first_name", first_name);
            request.setAttribute("last_name", last_name);
            request.setAttribute("password", password);
            request.setAttribute("password2", password2);
            request.setAttribute("mensajeR", "Activa tu cuenta en jCable a través del link que te enviamos por " +
                    "correo electrónico");

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("errorR", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);

    }
}
