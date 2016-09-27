<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KartooZ</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script src="resources/js/binding.js"></script>
</head>
<body>
	<div class="mainContainer" ng-app="MyApp">
		<div ng-controller="mainCtrl">
		<h1>Orders Page</h1>
		<input type='text' ng-model="data.label">
		<button class="btn btn-default">{{data.label}}</button>
		</div>
		<somedata></somedata>
		<div interactive-btn class="btn btn-default">
			button
		</div>
		<shld Reigns Roman Bns class="btn btn-default">
			Access
		</shld>
		<shld Reigns class="btn btn-default">
			Access
		</shld>
		<shld Roman class="btn btn-default">
			Access
		</shld>
		<shld Bns class="btn btn-default">
			Access
		</shld>
	</div>
</body>
</html>