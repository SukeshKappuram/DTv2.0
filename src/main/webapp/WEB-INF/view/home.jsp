<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>KartooZ</title>
<style type="text/css">
  .sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 100px;
    left: 0;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 20px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 25px;
    color: #818181;
    display: block;
    transition: 0.3s
}

.sidenav a:hover, .offcanvas a:focus{
    color: #f1f1f1;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
.dot {
  cursor:pointer;
  height: 13px;
  width: 13px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}
div.scrollmenu {
    overflow: auto;
    white-space: nowrap;
    text-align: center;
}
div.scrollmenu a {
    display: inline-block;
    color: white;
    text-align: center;
    padding: 14px;
    text-decoration: none;
    padding: 15px 35px 15px 35px;
}

div.scrollmenu a:hover {
    background-color: #777;
}
  body {
      font: 400 15px Lato, sans-serif;
      line-height: 1.8;
      color: #818181;
  }

  .item h4 {
      font-size: 19px;
      line-height: 1.375em;
      font-weight: 400;
      font-style: italic;
      margin: 70px 0;
  }
  .item span {
      font-style: normal;
  }
   footer .glyphicon {
      font-size: 20px;
      margin-bottom: 20px;
      color: #f4511e;
  }
  .slideanim {visibility:hidden;}
  .slide {
      animation-name: slide;
      -webkit-animation-name: slide;
      animation-duration: 1s;
      -webkit-animation-duration: 1s;
      visibility: visible;
  }
  @keyframes slide {
    0% {
      opacity: 0;
      transform: translateY(70%);
    }
    100% {
      opacity: 1;
      transform: translateY(0%);
    }
  }
  @-webkit-keyframes slide {
    0% {
      opacity: 0;
      -webkit-transform: translateY(70%);
    }
    100% {
      opacity: 1;
      -webkit-transform: translateY(0%);
    }
  }
  @media screen and (max-width: 768px) {
    .col-sm-4 {
      text-align: center;
      margin: 25px 0;
    }
    .btn-lg {
        width: 100%;
        margin-bottom: 35px;
    }
  }
  @media screen and (max-width: 480px) {
    .logo {
        font-size: 150px;
    }
  }
 
</style>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60"> 
<%@include file="header.jsp" %>
  <div id="Home" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#Home" data-slide-to="0" class="dot"></li>
      <li data-target="#Home" data-slide-to="1" class="dot"></li>
      <li data-target="#Home" data-slide-to="2" class="dot"></li>
      <li data-target="#Home" data-slide-to="3" class="dot"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="http://www.shopaholicindians.com/sidata1/uploads/2014/11/laptop-Offers-At-Shopaholic-Indians-620x196.jpg" alt="Chania" style="width:100%;height: 350px;">
      </div>

      <div class="item">
        <img src="http://2.bp.blogspot.com/-xmBzS1SAqtc/VFaADhtT1MI/AAAAAAAABXI/cEIFAdJ289U/s1600/sd2.PNG" alt="Chania" style="width: 100%;height: 350px;" >
      </div>
    
      <div class="item">
        <img src="http://blog.woohoo.in/wp-content/uploads/2013/04/deals-and-offers-at-jabong.jpg" alt="Flower" style="width: 100%;height: 350px;" >
      </div>

      <div class="item">
        <img src="https://s3-ap-southeast-1.amazonaws.com/carrotfry-images/ThreadUserImage/sale5-154.png" alt="Flower" style="width: 100%;height: 350px;" >
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#Home" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#Home" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
<div class="container" id="categories">
	<%@include file="categories.jsp" %>
</div>
<br/>
<div class="container" id="about">
	<%@include file="aboutUs.jsp" %>
</div>
<div class="container" id="feedback">
	<%@include file="feedback.jsp" %>
</div>
<div class="container" id="contact">
	<%@include file="contactUs.jsp" %>
</div>

<footer class="container-fluid text-center">
  <a href="#myPage" title="To Top">
    <span class="glyphicon glyphicon-chevron-up"></span>
  </a>
</footer>

<script>
$(document).ready(function(){
  // Add smooth scrolling to all links in navbar + footer link
  $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
    // Make sure this.hash has a value before overriding default behavior
    if (this.hash !== "") {
      // Prevent default anchor click behavior
      event.preventDefault();

      // Store hash
      var hash = this.hash;

      // Using jQuery's animate() method to add smooth page scroll
      // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 900, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
    } // End if
  });
  
  $(window).scroll(function() {
    $(".slideanim").each(function(){
      var pos = $(this).offset().top;

      var winTop = $(window).scrollTop();
        if (pos < winTop + 600) {
          $(this).addClass("slide");
        }
    });
  });
})
</script>
<br/><br/><br/>
<%@include file="footer.jsp" %>
<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>
</body>
</html>