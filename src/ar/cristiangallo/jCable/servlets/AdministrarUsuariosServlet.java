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

            String user_id = request.getParameter("user_id");
            if (user_id == null) {
                request.setAttribute("allUsers", ctrlUsers.allUsers());
            } else {
                User administrarUser = ctrlUsers.getUserById(Integer.parseInt(user_id));
                request.setAttribute("administrarUser", administrarUser);
            }

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("administrar-usuarios.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        try {
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            } else if (!user.getIsSuperuser()) {
                throw new appException("No tienen acceso a la administración de perfiles.");

            }

            String user_id = request.getParameter("user_id");
            if (user_id == null) {
                throw new appException("No hay usuario que administrar.");
            }
            ControladorUsers ctrlUsers = new ControladorUsers();
            User administrarUser = ctrlUsers.getUserById(Integer.parseInt(user_id));
            administrarUser.setNombre(request.getParameter("nombre"));
            administrarUser.setApellido(request.getParameter("apellido"));
            boolean isSuperuser = request.getParameterValues("isSuperuser") != null;
            boolean isStaff = request.getParameterValues("isStaff") != null;
            boolean isActive = request.getParameterValues("isActive") != null;
            administrarUser.setIsSuperuser(isSuperuser);
            administrarUser.setIsStaff(isStaff);
            administrarUser.setIsActive(isActive);
            ctrlUsers.saveUser(administrarUser);
            request.setAttribute("mensaje", "Los cambios se guardaron satisfactoriamente.");
            request.setAttribute("administrarUser", administrarUser);

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
