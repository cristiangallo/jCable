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


@WebServlet(name="ActivarUsuario", urlPatterns = {"/activar-usuario"})
public class ActivarUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        ControladorUsers ctrlUsers = new ControladorUsers();
        try {
            User user = ctrlUsers.getUser(email);
            ctrlUsers.activarUser(user);
            request.setAttribute("mensaje", "Activaste tu perfil, ya puedes utilizar jCable.");

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}
