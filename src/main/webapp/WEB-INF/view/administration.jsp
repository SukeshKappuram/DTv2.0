<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
    <nav class="col-sm-3" id="myScrollspy" style="position: fixed;">
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#section1">View Products</a></li>
        <li><a href="#section2">Add Product</a></li>
        <li><a href="#section3">Add Category</a></li>
        <li><a href="#section4">Approve Seller</a></li>
        <li><a href="#section5">View Blogs</a></li>
        <li><a href="#section6">View Forums</a></li>
      </ul>
    </nav>
    <div class="col-sm-9">
	<div id="section1" style="color:white;">
		<div class="table-responsive"> 
      	<table class="table table-hover" style="color: black">
      	<thead> 
      	<tr class="active"> 
      		<th>Product Name</th> <th>Selling Price</th> <th>Available</th> <th>Category</th> <th>Remove</th> 
      	</tr> 
      	</thead> 
      	<tbody> 
      	<c:forEach var="p" items="${products}">
      	<tr class="info">
      		<th scope="row"><a href="edit/${p.productId}">${p.name}</a></th>
      		<th scope="row">${p.price}</th>
  			<td>${p.available}</td>
  			<td>${p.categoryId.name}</td>
  			<td><a href="delete/${p.productId}"><img src="/ecomerce/resources/images/delete.png" width="20" height="20"></a></td>
		</tr>
	  </c:forEach>
	  </tbody>	
	  </table>
	  </div>
	</div>
	<hr style="width:100%;"/>
	<div id="section2" style="color:white;">
		<iframe src="Product" frameborder='0' style="width:100%;height: 600px;">      	
      	</iframe>
    </div>
    <hr style="width:100%;"/>
    <div id="section3" style="color:white;">
    	<iframe src="Category" frameborder='0' style="width:100%;height: 600px;">      	
      	</iframe>
    </div>
    <hr style="width:100%;"/>
    <div id="section4" style="color:white;">
    	<iframe src="approveSeller" frameborder='0' style="width:100%;height: 600px;">      	
      	</iframe>
    </div>
    <div id="section5" style="color:white;">
    	<iframe src="../Blogs" frameborder='0' style="width:100%;height: 600px;">      	
      	</iframe>
    </div>
    <div id="section6" style="color:white;">
    	<iframe src="../Forums" frameborder='0' style="width:100%;height: 600px;">      	
      	</iframe>
    </div>
  </div>
	</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>