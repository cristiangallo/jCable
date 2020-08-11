<%--
  Created by IntelliJ IDEA.
  User: cgallo
  Date: 20/6/20
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ar.cristiangallo.jCable.entidades.*" %>
<% User logued_user = (User) session.getAttribute("logued_user"); %>
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#custom-collapse">
                <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span>
                <span class="icon-bar"></span></button><a class="navbar-brand" href="/">jCable</a>
        </div>
        <div class="collapse navbar-collapse" id="custom-collapse">
            <ul class="nav navbar-nav navbar-right">
                <% if (logued_user!=null){
                    if (logued_user.getIsProductor()){
                        out.println("<li class='dropdown'><a class='dropdown-toggle' href='#' " +
                                "data-toggle='dropdown'>Producciones</a><ul class='dropdown-menu'>");
                        out.println("<li><a href='/nueva-produccion'>Nueva producción</a></li>");
                        out.println("<li><a id='mis-producciones' href='/listar-producciones'>Mis producciones</a></li>");
                    };
                    out.println("</ul></li>");
                    if (logued_user.getIsSuperuser()){
                        out.println("<li class='dropdown'><a class='dropdown-toggle' href='#' " +
                        "data-toggle='dropdown'>Administrar</a><ul class='dropdown-menu'>");
                        out.println("<li><a href='/administrar-agencias'>Administrar agencias</a></li>");
                        out.println("<li><a href='/administrar-usuarios'>Administrar usuarios</a></li>");
                        out.println("<li><a href='/administrar-reglamento'>Administrar reglamento</a></li>");
                    };
                    out.println("</ul></li>");
                    out.println("<li class='dropdown'><a class='dropdown-toggle' href='#' " +
                        "data-toggle='dropdown'>Hola "+ logued_user.getNombre() + "!</a><ul class='dropdown-menu'>");
                    out.println("<li><a href='/mi-perfil'>Mi perfil</a></li>");
                    out.println("<li><a href='/cambiar-password'>Cambiar contraseña</a></li>");
                    out.println("<li><a href='/logout'>Cerrar sesi&oacute;n</a></li></ul></li>");
                } %>
            </ul>
        </div>
    </div>
</nav>
