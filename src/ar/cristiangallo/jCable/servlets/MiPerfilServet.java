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

@WebServlet(name="MiPerfil", urlPatterns = {"/mi-perfil"})
public class MiPerfilServet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("first_name", user.getFirstName());
            request.setAttribute("last_name", user.getLastName());
            request.getRequestDispatcher("mi-perfil.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControladorUsers ctrlUsers = new ControladorUsers();

        User user = (User) request.getSession().getAttribute("user");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        request.removeAttribute("first_name");
        request.removeAttribute("last_name");

        try {
            System.out.println(first_name);
            System.out.println(last_name);
            request.setAttribute("first_name", first_name);
            request.setAttribute("last_name", last_name);
            user.setFirstName(first_name);
            user.setLastName(last_name);
            ctrlUsers.saveUser(user);
            request.setAttribute("mensaje", "Los cambios se guardaron exitosamente.");

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("mi-perfil.jsp").forward(request, response);

    }
}
