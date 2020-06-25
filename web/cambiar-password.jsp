<%--
  Created by IntelliJ IDEA.
  User: cgallo
  Date: 25/6/20
  Time: 08:45
  To change this template use File | Settings | File Templates.
--%>
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
<% User user = (User) session.getAttribute("user"); %>
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
                    <div class="col-sm-5 col-sm-offset-1 mb-sm-40">
                        <h4 class="font-alt">Cambiar contraseña</h4>
                        <hr class="divider-w mb-10">
                        <form class="form" name="registrarse" method="post" action="cambiar-password">
                            <% if (request.getAttribute("mensaje")!=null){
                                String mensaje = (String) request.getAttribute("mensaje");
                                out.println("<div class='alert alert-success' role='alert'>" + mensaje + "</div>");}
                            else if (request.getAttribute("error")!=null){
                                appException error = (appException)request.getAttribute("error");
                                out.println("<div class='alert alert-danger' role='alert'>" + error.getMessage() + "</div>");}%>

                            <div class="form-group">
                                <% String password = (String) request.getAttribute("password");
                                    out.println("<input class='form-control' type='password' name='password' " +
                                            "placeholder='Contraseña actual'");
                                    if (password!=null){out.println(" value="+password);}
                                    out.println(" >");
                                %>
                            </div>
                            <div class="form-group">
                                <% String new_password = (String) request.getAttribute("new_password");
                                    out.println("<input class='form-control' type='password' name='new_password' " +
                                            "placeholder='Nueva contraseña'");
                                    if (password!=null){out.println(" value="+new_password);}
                                    out.println(" >");
                                %>
                            </div>
                            <div class="form-group">
                                <% String new_password2 = (String) request.getAttribute("new_password2");
                                    out.println("<input class='form-control' type='password' name='new_password2' " +
                                            "placeholder='Repetir nueva contraseña'");
                                    if (new_password!=null){out.println(" value="+new_password2);}
                                    out.println(" >");
                                %>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-block btn-round btn-b" type="submit">Cambiar contraseña</button>
                            </div>
                        </form>
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
