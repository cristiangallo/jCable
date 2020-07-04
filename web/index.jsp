
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
    <jsp:include page="header.jsp" />
    <div class="main">
        <section class="module">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8">
                        <div class="post">
                            <div class="post-thumbnail"><a href="#"><img src="static/images/post-1.jpg" alt="Blog-post Thumbnail"></a></div>
                            <div class="post-header font-alt">
                                <h2 class="post-title"><a href="#">Our trip to the Alps</a></h2>
                                <div class="post-meta">By&nbsp;<a href="#">Mark Stone</a>| 23 November | 3 Comments | <a href="#">Photography, </a><a href="#">Web Design</a>
                                </div>
                            </div>
                            <div class="post-entry">
                                <p>A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine.</p>
                            </div>
                            <div class="post-more"><a class="more-link" href="#">Read more</a></div>
                        </div>
                        <div class="post #">
                            <div class="post-header font-alt">
                                <h2 class="post-title"><a href="Post with text only">Mark Stone</a></h2>
                                <div class="post-meta">By&nbsp;<a href="#">| 23 November | 3 Comments | </a>Marketing, ,Web Design<a href="#">A</a><a href="#"> </a><a href="#">w</a><a href="#">o</a><a href="#">n</a><a href="#">d</a><a href="#">e</a><a href="#">r</a><a href="#">f</a><a href="#">u</a><a href="#">l</a><a href="#"> </a><a href="#">s</a><a href="#">e</a><a href="#">r</a><a href="#">e</a><a href="#">n</a><a href="#">i</a><a href="#">t</a><a href="#">y</a><a href="#"> </a><a href="#">h</a><a href="#">a</a><a href="#">s</a><a href="#"> </a><a href="#">t</a><a href="#">a</a><a href="#">k</a><a href="#">e</a><a href="#">n</a><a href="#"> </a><a href="#">p</a><a href="#">o</a><a href="#">s</a><a href="#">s</a><a href="#">e</a><a href="#">s</a><a href="#">s</a><a href="#">i</a><a href="#">o</a><a href="#">n</a><a href="#"> </a><a href="#">o</a><a href="#">f</a><a href="#"> </a><a href="#">m</a><a href="#">y</a><a href="#"> </a><a href="#">e</a><a href="#">n</a><a href="#">t</a><a href="#">i</a><a href="#">r</a><a href="#">e</a><a href="#"> </a><a href="#">s</a><a href="#">o</a><a href="#">u</a><a href="#">l</a><a href="#">,</a><a href="#"> </a><a href="#">l</a><a href="#">i</a><a href="#">k</a><a href="#">e</a><a href="#"> </a><a href="#">t</a><a href="#">h</a><a href="#">e</a><a href="#">s</a><a href="#">e</a><a href="#"> </a><a href="#">s</a><a href="#">w</a><a href="#">e</a><a href="#">e</a><a href="#">t</a><a href="#"> </a><a href="#">m</a><a href="#">o</a><a href="#">r</a><a href="#">n</a><a href="#">i</a><a href="#">n</a><a href="#">g</a><a href="#">s</a><a href="#"> </a><a href="#">o</a><a href="#">f</a><a href="#"> </a><a href="#">s</a><a href="#">p</a><a href="#">r</a><a href="#">i</a><a href="#">n</a><a href="#">g</a><a href="#"> </a><a href="#">w</a><a href="#">h</a><a href="#">i</a><a href="#">c</a><a href="#">h</a><a href="#"> </a><a href="#">I</a><a href="#"> </a><a href="#">e</a><a href="#">n</a><a href="#">j</a><a href="#">o</a><a href="#">y</a><a href="#"> </a><a href="#">w</a><a href="#">i</a><a href="#">t</a><a href="#">h</a><a href="#"> </a><a href="#">m</a><a href="#">y</a><a href="#"> </a><a href="#">w</a><a href="#">h</a><a href="#">o</a><a href="#">l</a><a href="#">e</a><a href="#"> </a><a href="#">h</a><a href="#">e</a><a href="#">a</a><a href="#">r</a><a href="#">t</a><a href="#">.</a><a href="#"> </a><a href="#">I</a><a href="#"> </a><a href="#">a</a><a href="#">m</a><a href="#"> </a><a href="#">a</a><a href="#">l</a><a href="#">o</a><a href="#">n</a><a href="#">e</a><a href="#">,</a><a href="#"> </a><a href="#">a</a><a href="#">n</a><a href="#">d</a><a href="#"> </a><a href="#">f</a><a href="#">e</a><a href="#">e</a><a href="#">l</a><a href="#"> </a><a href="#">t</a><a href="#">h</a><a href="#">e</a><a href="#"> </a><a href="#">c</a><a href="#">h</a><a href="#">a</a><a href="#">r</a><a href="#">m</a><a href="#"> </a><a href="#">o</a><a href="#">f</a><a href="#"> </a><a href="#">e</a><a href="#">x</a><a href="#">i</a><a href="#">s</a><a href="#">t</a><a href="#">e</a><a href="#">n</a><a href="#">c</a><a href="#">e</a><a href="#"> </a><a href="#">i</a><a href="#">n</a><a href="#"> </a><a href="#">t</a><a href="#">h</a><a href="#">i</a><a href="#">s</a><a href="#"> </a><a href="#">s</a><a href="#">p</a><a href="#">o</a><a href="#">t</a><a href="#">,</a><a href="#"> </a><a href="#">w</a><a href="#">h</a><a href="#">i</a><a href="#">c</a><a href="#">h</a><a href="#"> </a><a href="#">w</a><a href="#">a</a><a href="#">s</a><a href="#"> </a><a href="#">c</a><a href="#">r</a><a href="#">e</a><a href="#">a</a><a href="#">t</a><a href="#">e</a><a href="#">d</a><a href="#"> </a><a href="#">f</a><a href="#">o</a><a href="#">r</a><a href="#"> </a><a href="#">t</a><a href="#">h</a><a href="#">e</a><a href="#"> </a><a href="#">b</a><a href="#">l</a><a href="#">i</a><a href="#">s</a><a href="#">s</a><a href="#"> </a><a href="#">o</a><a href="#">f</a><a href="#"> </a><a href="#">s</a><a href="#">o</a><a href="#">u</a><a href="#">l</a><a href="#">s</a><a href="#"> </a><a href="#">l</a><a href="#">i</a><a href="#">k</a><a href="#">e</a><a href="#"> </a><a href="#">m</a><a href="#">i</a><a href="#">n</a><a href="#">e</a><a href="#">.</a>
                                </div>
                            </div>
                            <div class="post-entry">
                                <p></p>
                            </div>
                            <div class="post-more"><a class="more-link" href="Post with text only">Read more</a></div>
                        </div>
                        <div class="post">
                            <div class="post-quote">
                                <blockquote class="font-serif">
                                    <p>" The languages only differ in their grammar, their pronunciation and their most common words. Everyone realizes why a new common language would be desirable: one could refuse to pay expensive translators. "</p>
                                    <p class="font-inc font-uppercase">- Thomas Anderson</p>
                                </blockquote>
                            </div>
                        </div>
                        <div class="post">
                            <div class="post-video embed-responsive embed-responsive-16by9">
                                <iframe class="embed-responsive-item" src="//www.youtube.com/embed/Jkk0VHiCnKY" frameborder="0" allowfullscreen="allowfullscreen"></iframe>
                            </div>
                            <div class="post-header font-alt">
                                <h2 class="post-title"><a href="#">Post with video</a></h2>
                                <div class="post-meta">By&nbsp;<a href="#">Mark Stone</a>| 23 November | 3 Comments | <a href="#">Marketing, </a><a href="#">Web Design</a>
                                </div>
                            </div>
                            <div class="post-entry">
                                <p>A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine.</p>
                            </div>
                            <div class="post-more"><a class="more-link" href="#">Read more</a></div>
                        </div>
                        <div class="post">
                            <div class="post-images-slider">

                                <div class="flex-viewport" style="overflow: hidden; position: relative; height: 431.25px;"><ul class="slides" style="width: 800%; transition-duration: 0s; transform: translate3d(-1500px, 0px, 0px);"><li class="clone" aria-hidden="true" style="width: 750px; margin-right: 0px; float: left; display: block;"><img src="static/images/post-3.jpg" alt="Blog Slider Image" draggable="false"></li>
                                    <li class="" data-thumb-alt="" style="width: 750px; margin-right: 0px; float: left; display: block;"><img src="static/images/post-1.jpg" alt="Blog Slider Image" draggable="false"></li>
                                    <li data-thumb-alt="" class="flex-active-slide" style="width: 750px; margin-right: 0px; float: left; display: block;"><img src="static/images/post-3.jpg" alt="Blog Slider Image" draggable="false"></li>
                                    <li class="clone" aria-hidden="true" style="width: 750px; margin-right: 0px; float: left; display: block;"><img src="static/images/post-1.jpg" alt="Blog Slider Image" draggable="false"></li></ul></div><ol class="flex-control-nav flex-control-paging"><li><a href="#" class="">1</a></li><li><a href="#" class="flex-active">2</a></li></ol><ul class="flex-direction-nav"><li class="flex-nav-prev"><a class="flex-prev" href="#">Previous</a></li><li class="flex-nav-next"><a class="flex-next" href="#">Next</a></li></ul></div>
                            <div class="post-header font-alt">
                                <h2 class="post-title"><a href="#">Post with slideshow</a></h2>
                                <div class="post-meta">By&nbsp;<a href="#">Mark Stone</a>| 23 November | 3 Comments | <a href="#">Marketing, </a><a href="#">Web Design</a>
                                </div>
                            </div>
                            <div class="post-entry">
                                <p>A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine.</p>
                            </div>
                            <div class="post-more"><a class="more-link" href="#">Read more</a></div>
                        </div>
                        <div class="pagination font-alt"><a href="#"><i class="fa fa-angle-left"></i></a><a class="active" href="#">1</a><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#"><i class="fa fa-angle-right"></i></a></div>
                    </div>
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
                                    out.println("<li><a href='/agencias?agencia_id=" + agencia.getId() +"'> " +
                                            agencia.getDescription() + "</a></li>");
                                }
                                out.println("</ul>");
                                out.println("</div>");
                            }
                        %>
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
                            <h5 class="widget-title font-alt">Tag</h5>
                            <div class="tags font-serif"><a href="#" rel="tag">Blog</a><a href="#" rel="tag">Photo</a><a href="#" rel="tag">Video</a><a href="#" rel="tag">Image</a><a href="#" rel="tag">Minimal</a><a href="#" rel="tag">Post</a><a href="#" rel="tag">Theme</a><a href="#" rel="tag">Ideas</a><a href="#" rel="tag">Tags</a><a href="#" rel="tag">Bootstrap</a><a href="#" rel="tag">Popular</a><a href="#" rel="tag">English</a>
                            </div>
                        </div>
                        <div class="widget">
                            <h5 class="widget-title font-alt">Text</h5>The languages only differ in their grammar, their pronunciation and their most common words. Everyone realizes why a new common language would be desirable: one could refuse to pay expensive translators.
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
</body>
</html>
