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
  <h2>Your Cart</h2>
  <div class="panel panel-default">
    <div class="panel-heading">Your product has been added to cart 
    <c:if test="${not empty cart.getCartItems()}">
    	<a href="deleteCart/${cart.cartId}/">
    		<button type="button" class="btn btn-danger" style="float: right;">
    			<i class="fa fa-trash"> Clear Cart </i>
   			</button>
		</a>
    </c:if>
    </div>
    <div class="panel-body">
    	<c:if test="${empty cart.getCartItems()}">
    		<i>Your Cart is Empty</i>
    	</c:if>
    	<c:forEach var="c" items="${cart.getCartItems()}">
    	<div class="row">
    		<div class="col-lg-4">
    		<img src="/ecomerce/resources/images/product/${c.cartGroupId.productId.productId}.jpg" style="float: left;max-width: 100px;max-height: 100px;"/>
			${c.cartGroupId.productId.name}</div>
			<div class="col-lg-4">
				<form action="updateCart/${c.cartGroupId.cartId.cartId}/${c.cartGroupId.productId.productId}">
					<input type="number" value="${c.quantity}" name="q" min="1" max="${c.cartGroupId.productId.available}"/>
					<button class="fa fa-refresh"></button>
				</form>
			</div>
			<div class="col-sm-2">$ ${c.totatPrice}</div>
			<div class="col-sm-2">
				<a href="deleteCart/${c.cartGroupId.cartId.cartId}/${c.cartGroupId.productId.productId}"><i class="fa fa-trash fa-stack-2x"></i></a>
			</div>
    	</div>
    	<p style="visibility: hidden;">${sum=sum+c.totatPrice}</p>
    	</c:forEach>
    </div>
    <div class="panel-footer">
    <c:if test="${not empty cart.getCartItems()}">
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
   	</c:if>
    </div>
    </div>
</div>
<%@include file="footer.jsp" %>    
</body>
</html>