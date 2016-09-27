<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KartooZ</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
  <h2>Categories</h2>
  <div class="panel panel-default">
    <div class="panel-heading">New Category</div>
    <div class="panel-body">
 	<form:form action="addCategory" method="post" enctype="multipart/form-data">
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
<%@include file="footer.jsp" %>
</body>
</html>