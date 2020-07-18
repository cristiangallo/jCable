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

@WebServlet(name="Login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User logued_user = (User) request.getSession().getAttribute("logued_user");

        if (logued_user != null) {
            response.sendRedirect("/");
            return;
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControladorUsers ctrlUsers = new ControladorUsers();

        String email = request.getParameter("emailL");
        String password = request.getParameter("passwordL");
        request.setAttribute("emailL", email);
        request.setAttribute("passwordL", password);
        try {
            User user = ctrlUsers.getUser(email, password);
            user.login();
            request.getSession().setAttribute("logued_user", user);

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("errorL", e);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // request.getRequestDispatcher("index.jsp").forward(request, response);
        response.sendRedirect("/");
    }
}
