<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KartooZ</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
  <h2>Product</h2>
  <div class="panel panel-default">
    <div class="panel-heading">New Product</div>
    <div class="panel-body">
    <form:form action="addProduct" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label for="inputdefault">Product Name</label>
      <form:input class="form-control" path="name" type="text"/>
    </div>
    <div class="form-group">
      <label for="sel3">Category</label>
      <select class="form-control" path="categoryId" name="categoryId" id="sel3">
        <c:forEach var="c" items="${categories}">
        	<option value="${c.id}">${c.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <label for="inputlg">Description</label>
      <form:input class="form-control" path="description" type="text"/>
    </div>
    <div class="form-group">
      <label for="inputdefault">Quantity</label>
      <form:input class="form-control" path="available" type="text"/>
    </div>
    <div class="form-group">
      <label for="inputdefault">Price</label>
      <form:input class="form-control" path="price" type="text"/>
    </div>
    <div class="form-group">
      <label for="inputlg">Upload Image</label>
      <input class="form-control" name="file" type="file"/>
    </div>
    <div class="form-group">
    	<button type="submit" class="btn btn-primary">Add Product</button>
   	</div>
  	</form:form>
	</div>
    <div class="panel-footer">Adding New Product</div>
  </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>