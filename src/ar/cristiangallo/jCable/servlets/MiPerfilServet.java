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
            request.setAttribute("nombre", user.getNombre());
            request.setAttribute("apellido", user.getApellido());
            request.getRequestDispatcher("mi-perfil.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControladorUsers ctrlUsers = new ControladorUsers();

        User user = (User) request.getSession().getAttribute("user");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        request.removeAttribute("nombre");
        request.removeAttribute("apellido");

        try {
            System.out.println(nombre);
            System.out.println(apellido);
            request.setAttribute("nombre", nombre);
            request.setAttribute("apellido", apellido);
            user.setNombre(nombre);
            user.setApellido(apellido);
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
