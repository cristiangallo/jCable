<%--
  Created by IntelliJ IDEA.
  User: cgallo
  Date: 24/6/20
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@page import="ar.cristiangallo.jCable.appExceptions.*" %>
<%@page import="ar.cristiangallo.jCable.entidades.*" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ar.cristiangallo.jCable.appExceptions.appException.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="es-AR" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--
    Document Title
    =============================================
    -->
    <title>jCable | JAVA - FRRO - UTN</title>
    <!--
    Favicons
    =============================================
    -->
    <!--
    Stylesheets
    =============================================

    -->
    <!-- Default stylesheets-->
    <link href="static/lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Template specific stylesheets-->
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Volkhov:400i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
    <link href="static/lib/components-font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/lib/et-line-font/et-line-font.css" rel="stylesheet">
    <!-- Main stylesheet and color file-->
    <link href="static/css/style.css" rel="stylesheet">
    <link id="color-scheme" href="static/css/colors/default.css" rel="stylesheet">
</head>
<body data-spy="scroll" data-target=".onpage-navigation" data-offset="60">
<main>
    <div class="page-loader">
        <div class="loader">Loading...</div>
    </div>
    <jsp:include page="header.jsp" />
    <div class="main">
        <section class="module">
            <div class="container">
                <div class="row">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-8 col-sm-offset-2 et-icons">
                                <h4 class="font-alt mb-0">Administrar usuarios</h4>
                                <hr class="divider-w mt-10 mb-20">
                                <% if (request.getAttribute("mensaje")!=null){
                                    String mensaje = (String) request.getAttribute("mensaje");
                                    out.println("<div class='alert alert-success' role='alert'>" + mensaje + "</div>");}
                                else if (request.getAttribute("error")!=null){
                                    appException error = (appException)request.getAttribute("error");
                                    out.println("<div class='alert alert-danger' role='alert'>" + error.getMessage() + "</div>");}%>
                                <% if (request.getAttribute("allUsers")!=null){
                                    ArrayList<User> allUsers = (ArrayList<User>) request.getAttribute("allUsers");
                                    for (User user : allUsers) {
                                        out.println("<a href='/administrar-usuarios?user_id=" + user.getId() +
                                                "'><span class='box1 ");
                                        if (user.getIsActive()) {
                                            out.println("alert-success ");
                                        } else {
                                            out.println("alert-danger ");
                                        }
                                        out.println("'><span class=");
                                        if (user.getIsSuperuser()) {
                                            out.println("'icon-gears'");
                                        } else {
                                            if (user.getIsStaff()) {
                                                out.println("'icon-strategy'");
                                            } else {
                                                out.println("'icon-profile-male'");
                                            }
                                        }
                                        out.println("aria-hidden='true'></span> " + user.getFullName() + "</span></a>");
                                    }
                                } %>
                                <% if (request.getAttribute("administrarUser")!=null) {
                                    User administrarUser = (User) request.getAttribute("administrarUser");
                                    out.println("<form class='form' name='registrarse' method='post' action=''>");
                                    out.println("<div class='form-group'>");
                                    out.println("<input class='form-control' type='text' name='first_name' value='" +
                                            administrarUser.getFirstName() + "' placeholder='Nombre' >");
                                    out.println("</div>");
                                    out.println("<div class='form-group'>");
                                    out.println("<input class='form-control' type='text' name='last_name' " +
                                            "value='" + administrarUser.getLastName() + "' placeholder='Apellido' >");
                                    out.println("</div>");
                                    out.println("<div class='form-group'>");
                                    out.println("<input class='form-control' type='email' disabled name='email' " +
                                            "value='" + administrarUser.getEmail() + "' placeholder='Email' >");
                                    out.println("</div>");
                                    out.println("<div class='form-group'>");
                                    out.println("<span class='box2'><input type='checkbox' name='isSuperuser' id='isSuperuser' ");
                                    if (administrarUser.getIsSuperuser()){ out.println("checked=\"checked\"");};
                                    out.println("><label style='padding-left: 10px;' for='isSuperuser'>Admin</label>");
                                    out.println("</span></div>");
                                    out.println("<div class='form-group'>");
                                    out.println("<span class='box2'><input type='checkbox' name='isStaff' id='isStaff'" );
                                    if (administrarUser.getIsStaff()){ out.println("checked=\"checked\"");};
                                    out.println("><label style='padding-left: 10px;' for='isStaff'>Productor</label>");
                                    out.println("</span></div>");
                                    out.println("<div class='form-group'>");
                                    out.println("<span class='box2'><input type='checkbox' name='isActive' id='isActive' ");
                                    if (administrarUser.getIsActive()){ out.println("checked=\"checked\"");};
                                    out.println("><label for='isActive' style='padding-left: 10px;'>Activo</label>");
                                    out.println("</span></div><br><br>");
                                    out.println("<div class='form-group'>");
                                    out.println("<button class='btn btn-border-d btn-round' type='button' " +
                                            "onclick=\"document.location.href = '/administrar-usuarios';\">volver</button>");
                                    out.println("<button class='btn btn-round btn-b' type='submit'>Guardar cambios</button>");
                                    out.println("</div>");
                                    out.println("</form>");
                                } %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <hr class="divider-d">
        <footer class="footer bg-dark">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <p class="copyright font-alt">&copy; 2020&nbsp;<a href="https://github.com/cristiangallo/jCable.git">jCable</a>,
                            All Rights Reserved</p>
                    </div>
                    <div class="col-sm-6">
                        <div class="footer-social-links">
                            <a href="#"><i class="fa fa-github"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </div>
    <div class="scroll-up"><a href="#totop"><i class="fa fa-angle-double-up"></i></a></div>
</main>
<!--
JavaScripts
=============================================
-->
<script src="static/lib/jquery/dist/jquery.js"></script>
<script src="static/lib/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="static/js/plugins.js"></script>
<script src="static/js/main.js"></script>
</body>
</html>
