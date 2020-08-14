<%--
  Created by IntelliJ IDEA.
  User: cgallo
  Date: 14/8/20
  Time: 08:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ar.cristiangallo.jCable.entidades.*" %>
<%@ page import="java.util.ArrayList" %>
<% User logued_user = (User) session.getAttribute("logued_user"); %>
<jsp:include page="header.jsp" />
<div class="main">
    <section class="module">
        <div class="container">
            <div class="row">
                <div class="col-sm-8">
                    <form class="form" role="form" action="" method="post">
                        <% Produccion produccion = (Produccion) request.getAttribute("contenido");
                            out.println("<div class='form-group'>");
                            if (produccion!=null) {
                                out.println("<input class='form-control input-lg' name='titulo' type='text' placeholder='Título' value='" + produccion.getTitulo() + "'>");
                            } else {
                                out.println("<input class='form-control input-lg' name='titulo' type='text' placeholder='Título'>");
                            }
                            out.println("</div>");
                            out.println("<div class='form-group'>");
                            if (produccion!=null) {
                                out.println("<textarea class='form-control' name='texto' row='7' placeholder='Texto'>" + produccion.getTexto() + "</textarea>");
                            } else {
                                out.println("<textarea class='form-control' name='texto' row='7' placeholder='Texto'></textarea>");
                            }
                            out.println("</div>");

                            out.println("<div class='form-group'>");
                            out.println("<button class='btn btn-border-d btn-round' type='button' " +
                                    "onclick=\"window.history.go(-1); return false;\">volver</button>");
                            out.println("<a class='btn btn-border-d btn-round' role='button' " +
                                    "onclick=\"return confirm('are you sure?');\" href='/eliminar-contenido?" +
                                    "contenido_id=" + produccion.getId() + "'>Eliminar</a>");
                            out.println("<a class='btn btn-border-d btn-round' role='button' " +
                                    "onclick=\"return confirm('are you sure?');\" href='/eliminar-contenido?" +
                                    "contenido_id=" + produccion.getId() + "'>Publicar</a>");
                            out.println("<a class='btn btn-round btn-b'  role='button' href='/editar-contenido?" +
                                    "contenido_id=" + produccion.getId() + "' type='submit'>Guardar</a>");
                            out.println("</div>");
                        %>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
