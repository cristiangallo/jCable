<%--
  Created by IntelliJ IDEA.
  User: cgallo
  Date: 4/7/20
  Time: 01:02
  To change this template use File | Settings | File Templates.
--%>
<%@page import="ar.cristiangallo.jCable.appExceptions.*" %>
<%@page import="ar.cristiangallo.jCable.entidades.*" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ar.cristiangallo.jCable.appExceptions.appException.*" %>
<%@ page import="java.net.Inet4Address" %>
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
                    <div class="col-sm-8 col-sm-offset-2 et-icons">
                        <h4 class="font-alt mb-0">Agregar agencia de noticias</h4>
                        <hr class="divider-w mt-10 mb-20">
                        <% if (request.getAttribute("mensaje")!=null){
                            String mensaje = (String) request.getAttribute("mensaje");
                            out.println("<div class='alert alert-success' role='alert'>" + mensaje + "</div>");}
                        else if (request.getAttribute("error")!=null){
                            appException error = (appException)request.getAttribute("error");
                            out.println("<div class='alert alert-danger' role='alert'>" + error.getMessage() + "</div>");}%>
                        <form class='form' name='form' method='post' action=''>
                            <div class="form-group">
                                <% String descripcion = (String) request.getAttribute("descripcion");
                                    out.println("<label for='descripcion'>Descripción</label>");
                                    out.println("<input class='form-control' type='text' id='descripcion' " +
                                            "name='descripcion' placeholder='Descripción'");
                                    if (descripcion!=null){out.println(" value="+descripcion);}
                                    out.println(" >");
                                %>
                            </div>
                            <div class="form-group">
                                <% String home_path = (String) request.getAttribute("home_path");
                                    out.println("<label for='home_path'>Home</label>");
                                    out.println("<input class='form-control' type='text' id='home_path' " +
                                            "name='home_path' placeholder='Ubicación de donde levantar los cables de " +
                                            "la agencia'");
                                    if (home_path!=null){out.println(" value="+home_path);}
                                    out.println(" >");
                                %>
                            </div>
                            <div class="form-group">
                                <% Integer dias_purga = (Integer) request.getAttribute("dias_purga");
                                    out.println("<label for='dias_purga'>Purga (en días)</label>");
                                    out.println("<input class='form-control' type='text' id='dias_purga' " +
                                            "name='dias_purga' placeholder='Días para purgarse'");
                                    if (dias_purga!=null){out.println(" value="+dias_purga);}
                                    out.println(" >");
                                %>
                            </div>
                            <div class="form-group">
                                <% boolean isActive = request.getAttribute("isActive") != null;
                                    out.println("<span class='box2'><input type='checkbox' name='isActive' id='isActive' ");
                                    if (isActive){out.println(" checked");}
                                    out.println("><label for='isActive' style='padding-left: 10px;'>Activa</label>");
                                    out.println("</span>");
                                %>
                            </div><br><br>
                            <div class='form-group'>
                                <a class='btn btn-border-d btn-round' role='button' href='/administrar-agencias'>volver</a>
                                <button class='btn btn-round btn-b' type='submit'>Agregar</button>
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