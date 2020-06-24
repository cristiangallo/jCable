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
        // response.getWriter().append("Served at: ").append(request.getContextPath());
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControladorUsers ctrlUsers = new ControladorUsers();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = ctrlUsers.getUser(email, password);
            user.login();
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("errorL", e);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("user", "popo");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
}
