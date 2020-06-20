package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.User;
import ar.cristiangallo.jCable.negocio.ControladorUsers;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="OlvidePassword", urlPatterns = {"/olvide-mi-password"})
public class OlvidePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("olvide-mi-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        ControladorUsers ctrlUsers = new ControladorUsers();

        String email = request.getParameter("email");

        try {
            ctrlUsers.olvideMiPassword(email);
            request.getSession().setAttribute("mensaje", "Te enviamos un correo con instrucciones para que " +
                    "recuperes tu contraseña");

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("olvide-mi-password.jsp").forward(request, response);

    }
}
