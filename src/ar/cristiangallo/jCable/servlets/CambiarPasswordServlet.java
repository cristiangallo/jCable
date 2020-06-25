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


@WebServlet(name="CambiarPassword", urlPatterns = {"/cambiar-password"})
public class CambiarPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            request.getRequestDispatcher("cambiar-password.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControladorUsers ctrlUsers = new ControladorUsers();

        User user = (User) request.getSession().getAttribute("user");
        String password = request.getParameter("password");
        String new_password = request.getParameter("new_password");
        String new_password2 = request.getParameter("new_password2");

        try {
            request.setAttribute("password", password);
            request.setAttribute("new_password", new_password);
            request.setAttribute("new_password2", new_password2);
            ctrlUsers.changePasswordUser(user, password, new_password, new_password2);
            request.setAttribute("mensaje", "Tu contraseña se cambió exitosamente.");

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("cambiar-password.jsp").forward(request, response);

    }
}
