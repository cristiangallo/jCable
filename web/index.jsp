<%@page import="ar.cristiangallo.jCable.entidades.*" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
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
    <!-- Main stylesheet and color file-->
    <link href="static/css/style.css" rel="stylesheet">
    <link id="color-scheme" href="static/css/colors/default.css" rel="stylesheet">
</head>
<body data-spy="scroll" data-target=".onpage-navigation" data-offset="60">
<main>
    <div class="page-loader">
        <div class="loader">Loading...</div>
    </div>
    <% User logued_user = (User) session.getAttribute("logued_user"); %>
    <jsp:include page="header.jsp" />
    <div class="main">
        <section class="module">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8">
                        <% if (request.getAttribute("contenidos")!=null){
                            ArrayList<Contenido> contenidos = (ArrayList<Contenido>) request.getAttribute("contenidos");
                            for (Contenido contenido : contenidos) {
                                if (contenido instanceof Produccion && (((Produccion) contenido).getPublicado()) || (contenido instanceof Produccion &&
                                        !((Produccion) contenido).getPublicado()) && ((Produccion) contenido).getUser().getId()==logued_user.getId()) {
                                    out.println("<div class='post' id=\"" + contenido.getId() + "\">");
                                    out.println("<div class='post-header font-alt'>");
                                    out.println("<h2 class='post-title'><a href='" + contenido.getAbsoluteURL() + "'>" + contenido.getTitulo() + "</a></h2>");
                                    out.println("<div class='post-meta'>Por&nbsp;<a href='#'>" +
                                            ((Produccion) contenido).getUser().getFullName() + "</a>| " +
                                            contenido.getDateTimeModificada() + "  | <a href='#'>Photography, " +
                                            "</a><a href='#'>Web Design</a>");
                                    out.println("</div>");
                                    out.println("</div>");
                                    out.println("<div class='post-entry'>");
                                    out.println(contenido.getBajada());
                                    out.println("</div>");
                                    out.println("<div class='post-more'><a class='more-link' href='" + contenido.getAbsoluteURL() + "'>Leer más</a></div>");
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
                            out.println("</div>");
                        } %>
                        <div class="col-sm-4 col-md-3 col-md-offset-1 sidebar">
                        <div class="widget">
                            <form role="form">
                                <div class="search-box">
                                    <input class="form-control" type="text" placeholder="Search...">
                                    <button class="search-btn" type="submit"><i class="fa fa-search"></i></button>
                                </div>
                            </form>
                        </div>
                        <%  if (request.getAttribute("agenciasActivas")!=null){
                                ArrayList<Agencia> agenciasActivas = (ArrayList<Agencia>) request.getAttribute("agenciasActivas");
                                out.println("<div class='widget'>");
                                out.println("<h5 class='widget-title font-alt'>Agencias de noticias</h5>");
                                out.println("<ul class='icon-list'>");
                                for (Agencia agencia : agenciasActivas) {
                                    out.println("<li><a href='/cables-agencias?agencia_id=" + agencia.getId() +"'> " +
                                            agencia.getDescripcion() + "</a></li>");
                                }
                                out.println("</ul>");
                                out.println("</div>");
                            } %>

                        <div class="widget">
                            <h5 class="widget-title font-alt">Popular Posts</h5>
                            <ul class="widget-posts">
                                <li class="clearfix">
                                    <div class="widget-posts-image"><a href="#"><img src="static/images/rp-1.jpg" alt="Post Thumbnail"></a></div>
                                    <div class="widget-posts-body">
                                        <div class="widget-posts-title"><a href="#">Designer Desk Essentials</a></div>
                                        <div class="widget-posts-meta">23 january</div>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <div class="widget-posts-image"><a href="#"><img src="static/images/rp-2.jpg" alt="Post Thumbnail"></a></div>
                                    <div class="widget-posts-body">
                                        <div class="widget-posts-title"><a href="#">Realistic Business Card Mockup</a></div>
                                        <div class="widget-posts-meta">15 February</div>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <div class="widget-posts-image"><a href="#"><img src="static/images/rp-3.jpg" alt="Post Thumbnail"></a></div>
                                    <div class="widget-posts-body">
                                        <div class="widget-posts-title"><a href="#">Eco bag Mockup</a></div>
                                        <div class="widget-posts-meta">21 February</div>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <div class="widget-posts-image"><a href="#"><img src="static/images/rp-4.jpg" alt="Post Thumbnail"></a></div>
                                    <div class="widget-posts-body">
                                        <div class="widget-posts-title"><a href="#">Bottle Mockup</a></div>
                                        <div class="widget-posts-meta">2 March</div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="widget">
                            <h5 class="widget-title font-alt">Recent Comments</h5>
                            <ul class="icon-list">
                                <li>Maria on <a href="#">Designer Desk Essentials</a></li>
                                <li>John on <a href="#">Realistic Business Card Mockup</a></li>
                                <li>Andy on <a href="#">Eco bag Mockup</a></li>
                                <li>Jack on <a href="#">Bottle Mockup</a></li>
                                <li>Mark on <a href="#">Our trip to the Alps</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </section>
        <%@include  file="footer.html" %>
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
<script>
    $(document).ready(function(){
        $(".fa-star").click(function(ev){
            ev.preventDefault();
            let cable = $(this);
            if (confirm(cable.attr('title'))) {
                $.ajaxSetup({ cache: false });
                $.ajax(
                    {
                        url: "/reservar-cable?cable_id=" + cable.attr("cable_id"),
                        type: "POST",
                        data: {},
                        success: function (data) {
                            $("#"+cable.attr("cable_id")).html(data);
                        },
                        error: function (data) {
                            alert("¡Ups, ocurrió un error reservando este cable!");
                        }
                    });
            }
        });
    });
</script>
</body>
</html>
