<%--
  Created by IntelliJ IDEA.
  User: cgallo
  Date: 20/6/20
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ar.cristiangallo.jCable.entidades.*" %>
<% User user = (User) session.getAttribute("user"); %>
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#custom-collapse">
                <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span>
                <span class="icon-bar"></span></button><a class="navbar-brand" href="/">jCable</a>
        </div>
        <div class="collapse navbar-collapse" id="custom-collapse">
            <ul class="nav navbar-nav navbar-right">
                <% if (user!=null){out.println("<li class='dropdown'><a class='dropdown-toggle' href='#' " +
                        "data-toggle='dropdown'>Hola "+ user.getFirstName() + "!</a><ul class='dropdown-menu'>");
                    if (user.getIsSuperuser()){out.println("<li><a href='/listar-usuarios'>Administrar usuarios</a></li>");};
                    out.println("<li><a href='/mi-cuenta'>Mi cuenta</a></li>");
                    out.println("<li><a href='/logout'>Cerrar sesi&oacute;n</a></li></ul></li>");
                }
                %>
            </ul>
        </div>
    </div>
</nav>
