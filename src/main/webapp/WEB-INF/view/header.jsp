<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>KartooZ</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">

	.container-fluid{
		background-color: #CCCBBB;
		font-size:15px;
	}
	.header{
		color: #093d2f;
		font-size:50px;
		font-style:italic;
		font-variant:small-caps;
		font-weight:bold;
		letter-spacing:10px;
		text-align: center;
		font-family: fantasy;
		text-decoration: underline;
	}
	.container-fluid ul li{
		padding: 0px 15px 0px 15px;
	}
	#custom-search-input{
    padding: 2px;
    border: solid 1px #E4E4E4;
    border-radius: 6px;
    background-color: #fff;
    width: 400px;
    
}

#custom-search-input input{
    border: 0;
    box-shadow: none;
}

#custom-search-input button{
    margin: 2px 0 0 0;
    background: none;
    box-shadow: none;
    border: 0;
    color: #666666;
    padding: 0 8px 0 10px;
    border-left: solid 1px #ccc;
}

#custom-search-input button:hover{
    border: 0;
    box-shadow: none;
    border-left: solid 1px #ccc;
}

#custom-search-input .glyphicon-search{
    font-size: 23px;
}
.container{
	margin: 10px auto;
	text-align: justify;
	background-color: #093d2f;
	padding: 15px 15px 15px 15px;
	box-shadow: 0px 0px 10px #cccccc;
}
.container table tr td{
	padding: 15px 15px 15px 15px;
}

</style>
</head>
<body>
	<div class="header">
<div class="container-fluid" style="font-size: 35px;">
	<img src="/ecomerce/resources/images/cart.png" style="width:100px;height: 100px;"/>
		KartooZ
	</div>
</div>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
      <li><span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span></li>
      <li><a href="./">Home</a></li>
      <li><a href="./#about">About Us</a></li>
      <li><a href="./#contact">Contact Us</a></li>
      <li><a href="./#feedback">Feedback</a></li>
      <li><a href="./Blogs">Blogs</a></li>
      <li><a href="../Forums">Forums</a></li>
   </ul>   
   <ul class="nav navbar-nav" style="float: right;">   
      <li>
      	<div id="custom-search-input">
                <div class="input-group col-md-12">
                    <input type="text" class="form-control input-lg" placeholder="Search Products / Networks" />
                    <span class="input-group-btn">
                        <button class="btn btn-info btn-lg" type="button">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </span>
                </div>
          </div>
      </li>
      <c:if test="${fn:length(user.getUser().firstName) > 0}">
      	<li><a href="#">Hi ${user.getUser().firstName} !</a></li>
      	 <c:if test="${user.getRole()=='ROLE_USER'}">
      	<li><a href="Cart/cart"><img src="/ecomerce/resources/images/cart.png" style="width:20px;height: 20px;"/></a></li>
      	</c:if>
      	<c:if test="${user.getRole()=='ROLE_SELLER'}">
      	<li><a href="User/sell">Sell</a></li>
      	<li><a href="Cart/cart"><img src="/ecomerce/resources/images/cart.png" style="width:20px;height: 20px;"/></a></li>
      	</c:if>
      </c:if>
      <c:if test="${empty user.getUser()}">
      <li><a href="signUp">Sign Up</a></li>
      <li><a href="login">Login</a></li>
      </c:if>
    </ul>
  </div>
</nav>
<div class="parallax"></div>
</body>
</html>