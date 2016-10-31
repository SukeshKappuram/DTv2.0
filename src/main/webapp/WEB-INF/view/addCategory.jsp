<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>KartooZ</title>
</head>
<body>
<div class="container">
  <div class="panel panel-default">
    <div class="panel-heading">New Category</div>
    <div class="panel-body">
 	<form:form action="Category" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label for="inputdefault">Category Name</label>
      <form:input class="form-control" path="name" type="text"/>
    </div>
    <div class="form-group">
      <label for="inputlg">Description</label>
      <form:input class="form-control" path="description" type="text"/>
    </div>
    <div class="form-group">
      <label for="inputlg">Upload Image</label>
      <input class="form-control" name="file" type="file"/>
    </div>
    <div class="form-group">
    	<button type="submit" class="btn btn-primary">Add Category</button>
   	</div>
  	</form:form>
    </div>
    <div class="panel-footer">adding new Category</div>
  </div>
</div>
</body>
</html>