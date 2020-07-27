
<%@page contentType="text/html;charset=UTF-8" language="java" %>
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
  <jsp:include page="header.jsp" />
    <main>
      <div class="page-loader">
        <div class="loader">Loading...</div>
      </div>
      <section class="home-section home-parallax home-fade home-full-height bg-dark bg-dark-30" id="home" data-background="static/images/bg_agencias_noticias.jpg">
        <div class="titan-caption">
          <div class="caption-content">
            <div class="font-alt mb-30 titan-title-size-4">Página no encontrada</div>
            <div class="font-alt">La url solicitada no se encontro en el servidor.
            </div>
            <div class="font-alt mt-30"><a class="btn btn-border-w btn-round" href="/">volver a la home</a></div>
          </div>
        </div>
      </section>
      <%@include file="footer.html" %>
    </main>
    <!--  
    JavaScripts
    =============================================
    -->
    <script src="static/lib/jquery/dist/jquery.js"></script>
    <script src="static/lib/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="static/lib/wow/dist/wow.js"></script>
    <script src="static/js/plugins.js"></script>
    <script src="static/js/main.js"></script>
  </body>
</html>