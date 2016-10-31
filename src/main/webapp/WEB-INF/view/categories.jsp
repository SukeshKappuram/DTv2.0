<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

  <link rel="stylesheet" href="http://bxslider.com/lib/jquery.bxslider.css" type="text/css">

  <!--[if lt IE 9]>
  <script src="/js/html5shiv.js"></script>
  <![endif]-->

  <script id="twitter-wjs" src="http://platform.twitter.com/widgets.js"></script>
  <script type="text/javascript" async="" src="http://www.google-analytics.com/ga.js"></script>
  <script src="http://bxslider.com/js/jquery.min.js"></script>
  
  
  <script src="http://bxslider.com/lib/jquery.bxslider.js"></script>
  <script src="http://bxslider.com/js/rainbow.min.js"></script>
  <script src="http://bxslider.com/js/scripts.js"></script>

  <script type="text/javascript">

    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-36499930-1']);
    _gaq.push(['_trackPageview']);

    (function() {
      var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
      ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
      var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
    })();

  </script>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	  $('.slider1').bxSlider({
	    slideWidth: 300,
	    minSlides: 2,
	    maxSlides: 4,
	    moveSlides: 1,
	    slideMargin: 10
	  });
	});
</script>
<div class="slider1" >
	<c:forEach var="c" items="${categories}">
		<div class="slide" style="text-align: center;">
			<a href="Product/products?c=${c.id}" title="${c.name}">
				<img alt="${c.name}" src='/ecomerce/resources/images/category/${image.getImage("category",c.id)}' width="150" height="150" /><br/>
			</a>
		</div>
	</c:forEach>
</div>

</body>