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
<script>
var num=0;
var ar = [];
var i=0;
function selectOption(num)
{
	var selObj = document.getElementById('category');
	selObj.selectedIndex = num;
}
</script>
</head>
<body onload="selectOption(ar);">
<c:if test="${not empty command.categoryId.id}" >
<%@ include file="header.jsp" %>
</c:if>
<div class="container">
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
      <select class="form-control" path="categoryId" name="categoryId" id="category">
        <c:forEach var="c" items="${categories}">
        	<option value="${c.id}">${c.name}</option>
        	<c:if test="${c.id == command.categoryId.id}" >
        		<script>
        			ar[0]=i;i++;
        		</script>
        	</c:if>
        	<script>
        		i++;
        	</script>
        </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <label for="inputlg">Description</label>
      <form:input class="form-control" path="description" type="text"/>
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
    	<button type="submit" class="btn btn-primary">Update Product</button>
   	</div>
  	</form:form>
	</div>
    <div class="panel-footer">Adding New Product</div>
  </div>
</div>
<c:if test="${not empty command.categoryId.id}" >
<%@ include file="footer.jsp" %>
</c:if>
</body>
</html>