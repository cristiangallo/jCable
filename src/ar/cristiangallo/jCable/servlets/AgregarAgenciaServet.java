package ar.cristiangallo.jCable.servlets;

import ar.cristiangallo.jCable.appExceptions.appException;
import ar.cristiangallo.jCable.entidades.Agencia;
import ar.cristiangallo.jCable.entidades.User;
import ar.cristiangallo.jCable.negocio.ControladorAgencias;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="Agregar-agencia", urlPatterns = {"/agregar-agencia"})
public class AgregarAgenciaServet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            } else if (!user.getIsSuperuser()) {
                throw new appException("No tienen acceso a la administración de agencias.");

            }

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("agregar-agencia.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer dias_purga = null;
        ControladorAgencias ctrlAgencia = new ControladorAgencias();
        User user = (User) request.getSession().getAttribute("user");
        try {
            dias_purga = Integer.parseInt(request.getParameter("dias_purga"));
        } catch(NumberFormatException ex){
        }
        String descripcion = request.getParameter("descripcion");
        String home_path = request.getParameter("home_path");
        boolean isActive = request.getParameterValues("isActive") != null;

        try {
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            } else if (!user.getIsSuperuser()) {
                throw new appException("No tienen acceso a la administración de agencias.");
            }
            request.setAttribute("descripcion", descripcion);
            request.setAttribute("home_path", home_path);
            request.setAttribute("dias_purga", dias_purga);
            request.setAttribute("isActive", isActive);
            Agencia agencia = ctrlAgencia.addAgencia(descripcion, home_path, dias_purga, isActive);
            request.setAttribute("agencia", agencia);
            response.sendRedirect("/administrar-agencias?agencia_id=" + agencia.getId());
            return;

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("agregar-agencia.jsp").forward(request, response);
    }
}
