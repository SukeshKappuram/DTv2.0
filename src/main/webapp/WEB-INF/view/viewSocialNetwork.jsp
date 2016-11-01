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
<div class="container-fluid" ng-app="myApp" >
	<div>
		<h2 style="float: left;font-family: cursive;font-variant: small-caps;">${network}s</h2>
	</div>
  <div>
  	<hr style="width: 100%;color: black;height: 2px;" />
  </div>

<div class="container-fluid">
  <div class="row content">
   <div class="col-sm-3 sidenav">
   	<ul class="nav nav-pills nav-stacked">
        <c:if test="${not empty user }">
			<li><a href="#create">Write ${network}</a></li>
			<li><a href="#created">Your ${network}</a></li>
		</c:if>
      </ul><br>
      <div class="input-group">
        <input type="text" class="form-control" ng-model="search" placeholder="Search ${network}s Here">
        <span class="input-group-btn">
          <button class="btn btn-default" type="button">
            <span class="glyphicon glyphicon-search"></span>
          </button>
        </span>
      </div>
    </div> 

	
	<div class="col-sm-9" ng-controller="usrCtrl"  id="created">
	<div >
          <c:if test="${not empty user and userNetworks.length() > 2 }">  
			<h4><small>RECENT POSTS</small></h4>
      <hr>
      <h2>{{ resource.name}}</h2>
      <h5><span class="glyphicon glyphicon-time"></span> Post by {{ resource.user.firstName}} {{ resource.user.lastName}} <img src='/ecomerce/resources/images/user/{{ resource.user.Id }}.jpg' class="img-circle" alt="{{ resource.user.firstName }}" style="max-width:50px;max-height:50px;">, {{ resource.createdDate}}.</h5>
      <h5>
      	<span class="label label-success"><a href="updateCart?n={{ resource.Id }}">reload</a></span>
      	<span class="label label-danger"><a href="deleteCart?n={{ resource.Id }}">delete</a></span>
      </h5><br>
      <p>{{ resource.description}}</p>
      <hr>

      <h4>Leave a Comment:</h4>
      <form role="form">
        <div class="form-group">
          <textarea class="form-control" rows="3" required></textarea>
        </div>
        <button type="submit" class="btn btn-success">Submit</button>
      </form>
      <br><br>
      
      <p><span class="badge">2</span> Comments:</p><br>
      
      <div class="row">
        <div class="col-sm-2 text-center">
          <img src="bandmember.jpg" class="img-circle" height="65" width="65" alt="Avatar">
        </div>
        <div class="col-sm-10">
          <h4>Anja <small>Sep 29, 2015, 9:12 PM</small></h4>
          <p>Keep up the GREAT work! I am cheering for you!! Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
          <br>
        </div>
        <div class="col-sm-2 text-center">
          <img src="bird.jpg" class="img-circle" height="65" width="65" alt="Avatar">
        </div>
        <div class="col-sm-10">
          <h4>John Row <small>Sep 25, 2015, 8:25 PM</small></h4>
          <p>I am so happy for you man! Finally. I am looking forward to read about your trendy life. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
          <br>
          <p><span class="badge">1</span> Comment:</p><br>
          <div class="row">
            <div class="col-sm-2 text-center">
              <img src="bird.jpg" class="img-circle" height="65" width="65" alt="Avatar">
            </div>
            <div class="col-xs-10">
              <h4>Nested Bro <small>Sep 25, 2015, 8:28 PM</small></h4>
              <p>Me too! WOW!</p>
              <br>
             </div>
           </div>
		 </div>
  	  </div>	  
	  </c:if>
    </div>
    </div>
    
    <div class="col-sm-9" ng-controller="namesCtrl">
    <div ng-repeat="resource in names | filter:search">
      <h4><small>RECENT POSTS</small></h4>
      <hr>
      <h2>{{ resource.name}}</h2>
      <h5><span class="glyphicon glyphicon-time"></span> Post by {{ resource.user.firstName}} {{ resource.user.lastName}} <img src='/ecomerce/resources/images/user/{{ resource.user.Id }}.jpg' class="img-circle" alt="{{ resource.user.firstName }}" style="max-width:50px;max-height:50px;">, {{ resource.createdDate}}.</h5>
      <h5>
      	<span class="label label-success"><a href="updateCart?n={{ resource.Id }}">reload</a></span>
      	<span class="label label-danger"><a href="deleteCart?n={{ resource.Id }}">delete</a></span>
      </h5><br>
      <p>{{ resource.description}}</p>
      <hr>

      <h4>Leave a Comment:</h4>
      <form role="form">
        <div class="form-group">
          <textarea class="form-control" rows="3" required></textarea>
        </div>
        <button type="submit" class="btn btn-success">Submit</button>
      </form>
      <br><br>
      
      <p><span class="badge">2</span> Comments:</p><br>
      
      <div class="row">
        <div class="col-sm-2 text-center">
          <img src="bandmember.jpg" class="img-circle" height="65" width="65" alt="Avatar">
        </div>
        <div class="col-sm-10">
          <h4>Anja <small>Sep 29, 2015, 9:12 PM</small></h4>
          <p>Keep up the GREAT work! I am cheering for you!! Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
          <br>
        </div>
        <div class="col-sm-2 text-center">
          <img src="bird.jpg" class="img-circle" height="65" width="65" alt="Avatar">
        </div>
        <div class="col-sm-10">
          <h4>John Row <small>Sep 25, 2015, 8:25 PM</small></h4>
          <p>I am so happy for you man! Finally. I am looking forward to read about your trendy life. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
          <br>
          <p><span class="badge">1</span> Comment:</p><br>
          <div class="row">
            <div class="col-sm-2 text-center">
              <img src="bird.jpg" class="img-circle" height="65" width="65" alt="Avatar">
            </div>
            <div class="col-xs-10">
              <h4>Nested Bro <small>Sep 25, 2015, 8:28 PM</small></h4>
              <p>Me too! WOW!</p>
              <br>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
  
  <c:if test="${not empty user.getUser()}">
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