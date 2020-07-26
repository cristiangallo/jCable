<%@page import="ar.cristiangallo.jCable.entidades.*" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: cgallo
  Date: 26/7/20
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User logued_user = (User) session.getAttribute("logued_user");
    Contenido contenido = (Contenido) request.getAttribute("contenido");
    out.println("<div class='post-header font-alt'>");
    if (contenido instanceof Produccion) {
        out.println("<h2 class='post-title'><a href='#'>" + contenido.getTitulo() + "</a></h2>");
        out.println("<div class='post-meta'>Por&nbsp;<a href='#'>" +
                ((Produccion) contenido).getUser().getFullName() + "</a>| " +
                contenido.getDateTimeModificada() + "  | <a href='#'>Photography, " +
                "</a><a href='#'>Web Design</a>");
    } else {
        Cable cable = ((Cable) contenido);
        out.println("<h2 class='post-title'><span><a role='button' href='#'" +
                "cable_id='" + cable.getId() + "'");
        if (logued_user.getCableReservado(cable) != null) {
            out.println("class='fa fa-star star' reservado='true' title='¿Liberas este cable?'></a>");
        } else {
            out.println("class='fa fa-star star-off' reservado='false' title='¿Reservas este cable?'></a>");
        }
        out.println("<a href='" + cable.getAbsoluteURL() + "'>" + contenido.getTitulo() +
                "</a></span></h2>");

        out.println("<div class='post-meta'>Fuente&nbsp;<a href='#'>" +
                cable.getAgencia().getDescripcion() + "</a>| " +
                cable.getDateTimeModificada() +  " | " + cable.getTema() + " | " +
                cable.getUrgencia());
    }
    out.println("</div>");
    out.println("</div>");
    out.println("<div class='post-entry'>");
    out.println("<p>" + contenido.getTexto().split("\n")[0] + "</p>");
    out.println("</div>");
    out.println("<div class='post-more'><a class='more-link' href='#'>Leer más</a></div>");
%>