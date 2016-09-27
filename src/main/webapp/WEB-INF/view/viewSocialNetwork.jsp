<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js" ></script>
<title>KartooZ</title>
</head>
<body>
<%@include file="header.jsp" %>
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
  <c:if test="${not empty user and not empty userNetworks }">  
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
  <c:if test="${not empty user }">
  <div class="panel panel-default" id="create">
    <div class="panel-heading">Add ${network}</div>
    <div class="panel-body">
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
<%@include file="footer.jsp" %>
</body>
</html>