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


@WebServlet(name="Administrar-agencias", urlPatterns = {"/administrar-agencias"})
public class AdministrarAgenciasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User logued_user = (User) request.getSession().getAttribute("logued_user");

        try {
            if (logued_user == null) {
                response.sendRedirect("login.jsp");
                return;
            } else if (!logued_user.getIsSuperuser()) {
                throw new appException("No tienen acceso a la administración de agencias.");

            }
            ControladorAgencias ctrlAgencias = new ControladorAgencias();
            String agencia_id = request.getParameter("agencia_id");
            if (agencia_id == null) {
                request.setAttribute("allAgencias", ctrlAgencias.allAgencias());
            } else {
                Agencia administrarAgencia = ctrlAgencias.getAgenciaById(Integer.parseInt(agencia_id));
                request.setAttribute("administrarAgencia", administrarAgencia);
            }

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("administrar-agencias.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer dias_purga = null;
        User logued_user = (User) request.getSession().getAttribute("logued_user");
        try {
            dias_purga = Integer.parseInt(request.getParameter("dias_purga"));
        } catch(NumberFormatException ex){
        }
        try {
            if (logued_user == null) {
                response.sendRedirect("login.jsp");
                return;
            } else if (!logued_user.getIsSuperuser()) {
                throw new appException("No tienen acceso a la administración de perfiles.");

            }

            String agencia_id = request.getParameter("agencia_id");
            if (agencia_id == null) {
                throw new appException("No hay agencias de noticias que administrar.");
            }
            ControladorAgencias ctrlAgencias = new ControladorAgencias();
            Agencia administrarAgencia = ctrlAgencias.getAgenciaById(Integer.parseInt(agencia_id));
            administrarAgencia.setDescripcion(request.getParameter("descripcion"));
            administrarAgencia.setHomePath(request.getParameter("home_path"));
            administrarAgencia.setDiasPurga(dias_purga);
            boolean isActive = request.getParameterValues("isActive") != null;
            administrarAgencia.setIsActive(isActive);
            ctrlAgencias.saveAgencia(administrarAgencia);
            request.setAttribute("mensaje", "Los cambios se guardaron satisfactoriamente.");
            request.setAttribute("administrarAgencia", administrarAgencia);

        } catch (appException e) {
            System.out.println(e);
            request.setAttribute("error", e);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("administrar-agencias.jsp").forward(request, response);
    }
}
