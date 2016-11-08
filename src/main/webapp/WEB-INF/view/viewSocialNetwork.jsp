%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
<%@include file="header.jsp" %>
<div class="container-fluid" ng-app="myApp" >
	<div>
		<h2 style="float: left;font-family: cursive;font-variant: small-caps;">${network}s</h2>
		<hr style="width: 100%;color: black;height: 2px;" />
	</div>
	<c:if test="${not empty user.getUser() and network != 'Friend'}">
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
	<div class="container-fluid">
  		<div class="row content">
   			<div class="col-sm-3 sidenav">
   				<ul class="nav nav-pills nav-stacked">
        			<c:if test="${not empty user and network != 'Friend'}">
					<li><a href="#create">Write ${network}</a></li>
					<li><a href="#created">Your ${network}</a></li>
					</c:if>
      			</ul><br/>
      			<div class="input-group">
        			<input type="text" class="form-control" ng-model="search" placeholder="Search ${network}s Here">
        			<span class="input-group-btn">
         				<button class="btn btn-default" type="button">
            				<span class="glyphicon glyphicon-search"></span>
          				</button>
        			</span>
      			</div>
    		</div> 
    		<!-- This are the Social Networks created by User -->
			<div class="col-sm-9" ng-controller="usrCtrl"  id="created">
				<div ng-repeat="resource in names | filter:search">
				<c:if test="${not empty user}">
          			<c:if test="${not empty user and userNetworks.length() > 2 }">  
      				<hr>
      				<h2>
      					{{ resource.name}}
      					<a href='#'>{{resource.friends[0].firstName}} {{resource.friends[0].lastName}}</a> 
      				</h2>
      				<h5><span class="glyphicon glyphicon-time"></span> <c:if test="${network != 'Friend'}">Post by {{ resource.user.firstName}} {{ resource.user.lastName}}</c:if>  <img src='/ecomerce/resources/images/user/{{ resource.friends[0].Id }}.jpg' class="img-circle" alt="{{ resource.friends[0].firstName }}" style="max-width:50px;max-height:50px;">, {{showDate(resource.createdDate,resource.id)}}
      					<c:if test="${network == 'Friend'}"> Since {{ resource.accepted }}</c:if> <small id='{{ resource.id }}'></small></h5>
      				<h5>
					<c:if test="${network != 'Friend'}">
      					<span class="label label-success"><a href="updateCart?n={{ resource.Id }}">reload</a></span>
      					<span class="label label-danger"><a href="deleteCart?n={{ resource.Id }}">delete</a></span>
      				</c:if>
      				<c:if test="${network == 'Friend'}">
      					{{isAccepted(resource.accepted,resource.friends[0].id )}}
      					<span class="label label-success" id="{{ resource.friends[0].id }}"><a href="User/Accept/{{ resource.id}}">Accept</a></span>
      					<span class="label"><span class="badge">2</span> <a>Mutual Friends</a></span>
      				</c:if>	
    				</h5><br/>
      				<c:if test="${network != 'Friend'}">
      				<p>{{ resource.description}}</p>
      				<hr>
					<h4>Leave a Comment:</h4>
      				<form role="form">
        				<div class="form-group">
          					<textarea class="form-control" rows="3" required></textarea>
        				</div>
        				<button type="submit" class="btn btn-success">Submit</button>
      				</form>
      				<br/><br/>
					<p><span class="badge">2</span> Comments:</p><br>
      				<div class="row">
        				<div class="col-sm-2 text-center">
          					<img src="bandmember.jpg" class="img-circle" height="65" width="65" alt="Avatar">
        				</div>
        				<div class="col-sm-10">
          					<h4>Anja <small>Sep 29, 2015, 9:12 PM</small></h4>
          					<p>Keep up the GREAT work! I am cheering for you!! Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
          					<br/>
        				</div>
        				<div class="col-sm-2 text-center">
          					<img src="bird.jpg" class="img-circle" height="65" width="65" alt="Avatar">
        				</div>
        				<div class="col-sm-10">
          					<h4>John Row <small>Sep 25, 2015, 8:25 PM</small></h4>
          					<p>I am so happy for you man! Finally. I am looking forward to read about your trendy life. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
          					<br/>
          					<p><span class="badge">1</span> Comment:</p><br>
          					<div class="row">
            					<div class="col-sm-2 text-center">
              						<img src="bird.jpg" class="img-circle" height="65" width="65" alt="Avatar">
            					</div>
            					<div class="col-xs-10">
              						<h4>Nested Bro <small>Sep 25, 2015, 8:28 PM</small></h4>
              						<p>Me too! WOW!</p>
              						<br/>
             					</div>
           					</div>
		 				</div>
  	  				</div>	  
	  				</c:if>
	  				</c:if>
    			</c:if>
    			</div>
    		</div>
    		<!-- This are the Social Networks created by others -->
    		<div class="col-sm-3 sidenav"></div>
    		<div class="col-sm-9" ng-controller="namesCtrl">
    			<div ng-repeat="resource in names | filter:search">
    				<c:if test="${not empty user}">
      				<hr>
      				<h2>{{ resource.name}} {{ resource.firstName}} {{ resource.lastName}}</h2>
      				<h5><span class="glyphicon glyphicon-time"></span><c:if test="${network != 'Friend'}"> Post by {{ resource.user.firstName}} {{ resource.user.lastName}}</c:if> <img src='/ecomerce/resources/images/user/{{ resource.user.Id }}.jpg' class="img-circle" alt="{{ resource.user.firstName }}" style="max-width:50px;max-height:50px;">, 
      					{{showDate(resource.registeredDate,resource.id)}}
      					<c:if test="${network == 'Friend'}">Since {{ resource.accepted }}</c:if> <small id='{{ resource.id }}'></small>.
      				</h5>
      				<h5>
      					<c:if test="${network != 'Friend'}">
      						<span class="label label-success"><a href="updateCart?n={{ resource.id }}">reload</a></span>
   							<span class="label label-danger"><a href="deleteCart?n={{ resource.id }}">delete</a></span>
   						</c:if>
   						<c:if test="${network == 'Friend'}">
      						<span class="label label-success"><a href="User/AddFriend/{{ resource.id}}">Add as Friend</a></span>
      						<span class="label"><span class="badge">2</span> <a>Mutual Friends</a></span>
	      				</c:if>	
      				</h5><br>
      				<c:if test="${network != 'Friend'}">
      				<p>{{ resource.description}}</p>
      				<hr>
      				<h4>Leave a Comment:</h4>
      				<form role="form">
        				<div class="form-group">
          					<textarea class="form-control" rows="3" required></textarea>
        				</div>
        				<button type="submit" class="btn btn-success">Submit</button>
      				</form>
      				<br/><br/>
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
             						<p>Me too! WOW! 
             						<a href="#" class="btn btn-danger btnAction" ng-click="getMessage(123)"><span class="glyphicon glyphicon-remove"></span>remove</a></p>
              						<br>
            					</div>
          					</div>
        				</div>
      				</div>
      				</c:if>
    				</c:if>
    			</div>
  			</div>
		</div>
	</div>
</div>
			<script>
				angular.module('myApp', []).controller('namesCtrl',['$scope','$http', function($scope, $http) {
    				$scope.names = ${networks};
    				$scope.orderByMe = function(x) {
        				$scope.myOrderBy = x;
    				}
    				$scope.showDate = function (jsonDate,div) {
    					var monthNames = [
    					                  "January", "February", "March",
    					                  "April", "May", "June", "July",
    					                  "August", "September", "October",
    					                  "November", "December"
    					                ];
    					var date = new Date(parseInt(jsonDate));
    		            //alert("This is an example of ng-click "+date);
    		            var am_pm = date.getHours() >= 12 ? "PM" : "AM";
    		            var hrs=date.getHours()>=12 ? date.getHours()-12 : date.getHours();
    		            var str=monthNames[date.getMonth()]+" "+date.getDate()+", "+date.getFullYear()+", "+hrs+":"+date.getMinutes()+" "+am_pm;
    		            document.getElementById(div).innerHTML = str;
    		        }
    				$scope.getMessage = function (itemId) {
    		        	alert('inside remove method');
    		            $http.put('http://localhost:8080/ecomerce/Cart/removeItem/'+itemId).success(function () {
    		            	alert('pls refresh now');
    		            });
    		        };
				}]).controller('usrCtrl', function($scope) {
    				$scope.names = [];
    				$scope.orderByMe = function(x) {
        				$scope.myOrderBy = x;
    				}
    				$scope.showDate = function (jsonDate,div) {
    					var monthNames = [
    					                  "January", "February", "March",
    					                  "April", "May", "June", "July",
    					                  "August", "September", "October",
    					                  "November", "December"
    					                ];
    					var date = new Date(parseInt(jsonDate));
    		            //alert("This is an example of ng-click "+date);
    		            var am_pm = date.getHours() >= 12 ? "PM" : "AM";
    		            var hrs=date.getHours()>=12 ? date.getHours()-12 : date.getHours();
    		            var str=monthNames[date.getMonth()]+" "+date.getDate()+", "+date.getFullYear()+", "+hrs+":"+date.getMinutes()+" "+am_pm;
    		            document.getElementById(div).innerHTML = str;
    		        }
    				$scope.isAccepted = function (jsonData,div){
    					if(jsonData==true){
    						document.getElementById(div).innerHTML = '';
    					}
    				}
    				$scope.isSameUser = function(userId,frindId){
    					alert(userId+' '+frindId);
    					if(userId==frindId){
    						alert('Hi');
    						var x='{{ resource.user.firstName }} {{ resource.user.lastName}}';
    						var y=document.getElementById(userId);
    						alert(y);
    						y.innerHTML=x;
    					}
    				}
				});
			</script>
<c:if test="${empty user}">
<%@include file="footer.jsp" %>
</c:if>
</body>
</html>