<%@page import="ar.cristiangallo.jCable.entidades.*" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<% User logued_user = (User) session.getAttribute("logued_user"); %>
<% if (request.getAttribute("contenidos")!=null){
ArrayList<Contenido> contenidos = (ArrayList<Contenido>) request.getAttribute("contenidos");
    for (Contenido contenido : contenidos) {
    if (contenido instanceof Produccion && (((Produccion) contenido).getPublicado()) || (contenido instanceof Produccion &&
    !((Produccion) contenido).getPublicado()) && ((Produccion) contenido).getUser().getId()==logued_user.getId()) {
    out.println("<div class='post' id=\"" + contenido.getId() + "\">");
    out.println("<div class='post-header font-alt'>");
        out.println("<h2 class='post-title'>");
            if (((Produccion) contenido).getPublicado()) {
            out.print("<a href='" + contenido.getAbsoluteURL() + "'>" + contenido.getTitulo() + "</a></h2>");
        } else {
        out.print("<a href='/editar-contenido?contenido_id=" + contenido.getId() + "'>" + contenido.getTitulo() + "</a></h2>");
        }

        out.println("<div class='post-meta'>Por&nbsp;<a href='" + ((Produccion) contenido).getUser().getAbsoluteURL() + "'>" +
            ((Produccion) contenido).getUser().getFullName() + "</a>| " +
            contenido.getDateTimeModificada());
            out.println("</div>");
        out.println("</div>");
    out.println("<div class='post-entry'>");
        out.println(contenido.getBajada());
        out.println("</div>");
    out.println("<div class='post-more'>");
        if (((Produccion) contenido).getPublicado()) {
        out.print("<a class='more-link' href='"+ contenido.getAbsoluteURL() + "'>Leer más</a></div>");
    } else {
    out.print("<a class='more-link' href='/editar-contenido?contenido_id=" + contenido.getId() + "'>Continuar edición</a></div>");
    }
    out.println("</div>");
    } else if (contenido instanceof Cable) {
    Cable cable = ((Cable) contenido);
    out.println("<div class='post' id=\"" + contenido.getId() + "\">");
    out.println("<div class='post-header font-alt'>");
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
            out.println("</div>");
        out.println("</div>");
    out.println("<div class='post-entry'>");
        out.println(contenido.getBajada());
        out.println("</div>");
    out.println("<div class='post-more'><a class='more-link' href='" + contenido.getAbsoluteURL() + "'>Leer más</a></div>");
    out.println("</div>");
    }
    }

    out.println("<div class='pagination font-alt'><a href='#'><i class='fa fa-angle-left'>" +
        "</i></a><a class='active' href='#'>1</a><a href='#'>2</a><a href='#'>3</a>" +
        "<a href='#'>4</a><a href='#'><i class='fa fa-angle-right'></i></a></div>");
    } %>