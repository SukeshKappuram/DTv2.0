<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>KartooZ</title>
<style>
body {
      position: relative;
  }
  ul.nav-pills {
      top: 20px;
  }
  
  @media screen and (max-width: 810px) {
    #section1, #section2, #section3, #section41, #section42  {
        margin-left: 150px;
    }
  }
  th,td{
  	text-align: center;
  }
  </style>
</head>
<body data-spy="scroll" data-target="#myScrollspy" data-offset="20">
	<%@include file="header.jsp"%>
	<div class="container">
	<div class="row">
    <nav class="col-sm-3" id="myScrollspy">
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#section1">View Product</a></li>
        <li><a href="#section2">Add Product</a></li>
        <li><a href="#section4">Shipping Address</a></li>
        <li><a href="#section3">Shipping Requests</a></li>
      </ul>
    </nav>
    <div class="col-sm-9">
      <div id="section1" style="color:white;">
      	<div class="bs-example" data-example-id="simple-responsive-table"> 
      	<div class="table-responsive"> 
      	<table class="table" style="color: black">
      	<caption>Products sold by You</caption>
      	<thead> 
      	<tr class="active"> 
      		<th>Product Name</th> <th>Selling Price</th> <th>Quantity</th> <th>Discount</th> <th colspan='2'>Modify</th> 
      	</tr> 
      	</thead> 
      	<tbody> 
      <c:forEach var="p" items="${sproducts}">
      	<tr class="info">
      		<th scope="row">${p.product.name}</th>
      		<th scope="row">${p.product.price}</th>
  			<c:if test="${empty seller}" >
  			<td>${p.quantity}</td>
  			<td>${p.discount}</td>
  			<td><a href="edit?p=${p.id}">Update</a></td>
  			<td><a href="delete?p=${p.id}">Delete</a></td>
  			</c:if>
  			<c:if test="${p.id == seller.id}">
  			<form action="update">
  			<td><input type="number" name="q" value="${p.quantity}" min="1" style="color: blue;" /></td>
  			<td><input type="text" name="d" value="${p.discount}" style="color: blue;" /></td>
  			<td><input type="submit" value="Update" class="btn btn-default"></td>
  			</form>
  			</c:if>
		</tr>
	  </c:forEach>
	  </tbody>	
	  </table>
	  </div>
	  </div>
      </div>
      <hr style="width:100%;"/>
      <div id="section2" style="color:white;">
      	<h1>Add Product</h1>
        <form:form action="addSellerProduct" method="post">
    <div class="form-group">
      <label for="sel3">Category</label>
      <select class="form-control" name="categoryId" id="sel3">
        <c:forEach var="c" items="${categories}">
        	<option value="${c.id}">${c.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <label for="inputdefault">Product Name</label>
      <select class="form-control" path="product" name="productId" id="sel3">
        <c:forEach var="p" items="${products}">
        	<option value="${p.productId}">${p.name}</option>
        </c:forEach>
      </select>
    </div>
	    <div class="form-group">
      <label for="inputdefault">Quantity</label>
      <form:input class="form-control" path="quantity" min='1' type="number"/>
    </div>
    <div class="form-group">
      <label for="inputdefault">Discount</label>
      <form:input class="form-control" path="discount" type="text"/>
    </div>
    <div class="form-group">
      <label for="inputlg">Description</label>
      <form:input class="form-control" path="description" type="text"/>
    </div>
    <div class="form-group">
      <label for="inputlg">Free Delivery</label>
      <input type="radio" name="freeDelivery" value="true" checked="checked"/> Yes
      <input type="radio" name="freeDelivery" value="false"/> No
    </div>
    <div class="form-group">
    	<button type="submit" class="btn btn-primary">Add Product</button>
   	</div>
  	</form:form>
      </div>
      <hr style="width:100%;"/>
      <div id="section3" style="color:white;">
      </div>
      <div id="section4" style="color:white;">
      <iframe src="shipFrom"  style="width:100%;height: 500px;">      	
      </iframe>
      </div>
      
    </div>
  </div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>