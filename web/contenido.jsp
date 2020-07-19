<%@ page import="ar.cristiangallo.jCable.entidades.Contenido" %>
<%@ page import="ar.cristiangallo.jCable.entidades.Produccion" %>
<%@ page import="ar.cristiangallo.jCable.entidades.Cable" %>
<%@ page import="ar.cristiangallo.jCable.entidades.User" %><%--
  Created by IntelliJ IDEA.
  User: cgallo
  Date: 19/7/20
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User logued_user = (User) session.getAttribute("logued_user"); %>
<% Contenido contenido = (Contenido) request.getAttribute("contenidos");
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
    out.println("<p>A wonderful serenity has taken possession of my entire soul, like " +
    "these sweet mornings of spring which I enjoy with my whole heart. I am alone" +
    ", and feel the charm of existence in this spot, which was created for the " +
    "bliss of souls like mine.</p>");
    out.println("</div>");
    out.println("<div class='post-more'><a class='more-link' href='#'>Read more</a></div>");
%>