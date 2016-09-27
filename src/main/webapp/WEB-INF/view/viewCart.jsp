<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KartooZ</title>
<style type="text/css">
.row div{
	text-align:center; 
}
</style>
</head>
<body>
<%@include file="header.jsp" %>
<%
float sum=0;
%>
<div class="container">
  <h2 style="color: white;">Your Cart</h2>
  <div class="panel panel-default">
    <div class="panel-heading">Your product has been added to cart</div>
    <div class="panel-body">
    	<c:forEach var="c" items="${cartItems}">
    	<div class="row">
    		<div class="col-lg-4">
    		<img src="/ecomerce/resources/images/product/${c.cartGroupId.productId.productId}.jpg" style="float: left;max-width: 100px;max-height: 100px;"/>
			${c.cartGroupId.productId.name}</div>
			<div class="col-lg-4">
				<input type="number" value="${c.quantity}" min="1" max="${c.cartGroupId.productId.available}"/>
			</div>
			<div class="col-sm-2">$ ${c.totatPrice}</div>
			<div class="col-sm-2">
				<a href="updateCart?c=${c.cartGroupId}"><img src="/ecomerce/resources/images/reload.png" width="20" height="20"></a>&nbsp
				<a href="deleteCart?c=${c.cartGroupId}"><img src="/ecomerce/resources/images/delete.png" width="20" height="20"></a>
			</div>
    	</div>
    	<p style="visibility: hidden;">${sum=sum+c.totatPrice}</p>
    	</c:forEach>
    </div>
    <div class="panel-footer">
    	<div class="row">
			<div class="col-lg-8" style="font-size: 30px;">Total</div>
			<div class="col-sm-4" style="font-size: 30px;">$ ${sum}</div>
    	</div>
    	<div class="row">
			<div class="col-lg-8"></div>
			<div class="col-sm-4">
				<a href="../User/shipTo?c=${cart.cartId}" class="btn btn-info" role="button">Proceed to Shipment</a>
			</div>
    	</div>
    </div>
    </div>
</div>
<%@include file="footer.jsp" %>    
</body>
</html>