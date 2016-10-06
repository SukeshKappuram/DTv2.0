<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KartooZ</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js" ></script>
<style type="text/css">
* {
    box-sizing: border-box;
}

.columns {
    float: left;
    width: 33.3%;
    padding: 8px;
}

.price {
    list-style-type: none;
    border: 1px solid #eee;
    margin: 0;
    padding: 0;
    -webkit-transition: 0.3s;
    transition: 0.3s;
}

.price:hover {
    box-shadow: 0 8px 12px 0 rgba(0,0,0,0.2)
}

.price .header {
    background-color: #111;
    color: white;
    font-size: 20px;
    font-family: monospace;
    text-decoration: none;
}

.price li {
    border-bottom: 1px solid #eee;
    padding: 25px;
    text-align: center;
}

.price .grey {
    background-color: #eee;
    font-size: 20px;
}

.button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 10px 25px;
    text-align: center;
    text-decoration: none;
    font-size: 18px;
    }

@media only screen and (max-width: 600px) {
    .columns {
        width: 100%;
    }
}
</style>
</head>
<body>
<%@include file="header.jsp" %>
<div id="tour" class="bg-1">
  <div class="container" ng-app="myApp" ng-controller="namesCtrl">
  	<div style="background-color: white;height: 30px;">
  	<form>
			<input type="text" ng-model="search" style="height: 30px;width: 90%;outline: none;"/>&nbsp&nbsp
			<span class="glyphicon glyphicon-search"></span> Search
	</form>
	</div>
	<br/>
    <div class="row text-center" >
      <div >
	    <div class="columns" ng-repeat="resource in names | filter:search">
  			<ul class="price">
  				<li>
  					<div class="thumbnail">
          				<a href="Details?p={{ resource.productId }}">
          					<img src='/ecomerce/resources/images/product/{{ resource.productId }}.jpg' class="img-rounded" alt="Cinque Terre" style="max-width:200px;max-height:200px;">
          				</a>
         			</div>
  				</li>
    			<li class="header">{{ resource.name}}</li>
    			<li class="grey">$ {{ resource.price}}</li>
    			<li><a href="../Cart/addToCart?c={{ resource.productId}}" class="button" role="button">Add To Cart</a></li>
   				<li class="grey"><a href="../Cart/buyNow?c={{ resource.productId}}" class="button" role="button">Buy Now</a> </li>
  			</ul>
		</div>
  	</div>
</div>
</div>
</div>
			<script>
				angular.module('myApp', []).controller('namesCtrl', function($scope) {
    				$scope.names = ${products};
    				$scope.orderByMe = function(x) {
        				$scope.myOrderBy = x;
    				}
				});
			</script>
<br/><br/><br/><br/>
<%@include file="footer.jsp" %>
</body>
</html>