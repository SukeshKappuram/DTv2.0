<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js" ></script>
<script>
$(document).ready(function(){
    $("#flip").click(function(){
        $("#panel").slideToggle("slow");
    });
});
</script>
<style>
#panel, #flip {
    padding: 5px;
    text-align: center;
    background-color: #e5eecc;
    border: solid 1px #c3c3c3;
}

#panel {
    padding: 50px;
    display: none;
}
</style>
<title>KartooZ</title>
</head>
<body>
<c:if test="${empty user.getUser()}">
<%@include file="header.jsp" %>
</c:if>
<div class="container">
<div class="container-fluid text-center bg-grey" ng-app="myApp" >
	<div>
		<h2 style="float: left;font-family: cursive;font-variant: small-caps;">${network}s</h2>
		<ul class="nav navbar-nav" style="float: right;">
			<c:if test="${not empty user }">
			<li><a href="#create">Write ${network}</a></li>
			<li><a href="#created">Your ${network}</a></li>
			</c:if>
			<li>
			<form style="float: right;">
				<input type="text" ng-model="search" placeholder="Search ${network}s Here"/>&nbsp&nbsp
				<span class="glyphicon glyphicon-search"></span>
			</form>
			</li>
		</ul>
	</div>
	<div ng-controller="namesCtrl">
  		<hr style="width: 100%;color: black;height: 2px;" />
    	<div class="row text-center" ng-repeat="resource in names | filter:search">
        <div class="thumbnail">
          <div class="row">
    		<div class="col-sm-4">
          	<a href="Details?p={{ resource.Id }}">
          		<img src='/ecomerce/resources/images/network/{{ resource.Id }}.jpg' class="img-rounded" alt="{{ resource.name }}" style="max-width:100px;max-height:100px;float: left;">
          	</a>
          	</div>
          	<div class="col-sm-4">
          		<h3>{{ resource.name}}</h3>
          	</div>
		   </div>
		   <div class="row">
          	<div class="col-sm-8">
          	<b>{{ resource.description}}</b>
          	</div>
          	<div class="col-sm-4">
          	<p>
          		<a href="writer?w={{ resource.user.Id }}">
          			<img src='/ecomerce/resources/images/user/{{ resource.user.Id }}.jpg' class="img-circle" alt="{{ resource.user.firstName }}" style="max-width:50px;max-height:50px;float: right;">
          		</a>
          		<i style="float: right">{{ resource.user.firstName}} {{ resource.user.lastName}}</i>
          	</p>
          	</div>
          	</div>
        </div>
    	</div>
    </div>
  <c:if test="${not empty user and userNetworks.length() > 2 }">  
  <div  ng-controller="usrCtrl"  id="created">
  	<hr style="width: 100%;color: black;height: 2px;" />
  	<h4>You ${network}(s)</h4>
  	<div class="row text-center" ng-repeat="resource in names | filter:search">
        <div class="thumbnail">
          <div class="row">
    		<div class="col-sm-4">
          	<a href="Details?p={{ resource.Id }}">
          		<img src='/ecomerce/resources/images/network/{{ resource.Id }}.jpg' class="img-rounded" alt="{{ resource.name }}" style="max-width:100px;max-height:100px;float: left;">
          	</a>
          	</div>
          	<div class="col-sm-4">
          		<h3>{{ resource.name}}</h3>
          	</div>
          	<div class="col-sm-4">
				<a href="updateCart?n={{ resource.Id }}"><img src="/ecomerce/resources/images/reload.png" width="20" height="20"></a>&nbsp
				<a href="deleteCart?n={{ resource.Id }}"><img src="/ecomerce/resources/images/delete.png" width="20" height="20"></a>
			</div>
		   </div>
		   <div class="row">
          	<div class="col-sm-8">
          	<b>{{ resource.description}}</b>
          	</div>
          	</div>
        </div>
    </div>
  	</div>
  	</c:if>  
  </div>
  <div>
  	<hr style="width: 100%;color: black;height: 2px;" />
  </div>
  <c:if test="${not empty user}">
  <div class="panel panel-default" id="create">
    <div class="panel-heading" id="flip">Add ${network}</div>
    <div class="panel-body" id="panel">
	<form:form action="./User/${network}s" method="post" enctype="multipart/form-data">
   	<div class="form-group">
        	<form:input type="text" path="name" class="form-control input-lg" placeholder="Title of Your ${network}"/>     
        	<form:errors path="name"/>                    
 	</div>
    <div class="form-group">
        	<form:textarea  path="description" class="form-control input-lg" placeholder="Description" cols="10" rows="10" />
        	<form:errors path="description"/>     
	</div>
	<div class="form-group">
      <label for="inputlg">Upload Image</label>
      <input class="form-control" name="file" type="file"/>
    </div>
	<div  class="form-group">
        	<button class="btn btn-primary" type="submit" style="width:100%;">Add My ${network}</button>
    </div>
   	</form:form>
   	 </div>
    <div class="panel-footer"></div>          
	</div>
	</c:if>
</div>
			<script>
				angular.module('myApp', []).controller('namesCtrl', function($scope) {
    				$scope.names = ${networks};
    				$scope.orderByMe = function(x) {
        				$scope.myOrderBy = x;
    				}
				}).controller('usrCtrl', function($scope) {
    				$scope.names = ${userNetworks};
    				$scope.orderByMe = function(x) {
        				$scope.myOrderBy = x;
    				}
				});
			</script>
<c:if test="${empty user}">
<%@include file="footer.jsp" %>
</c:if>
</body>
</html>