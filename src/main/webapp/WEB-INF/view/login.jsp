<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KartooZ</title>
</head>
<body onload='document.f.username.focus();'>
<%@include file="header.jsp" %>
<div class="container">
  <h2 style="color: white;">Sign In</h2>
  <div class="panel panel-default">
    <div class="panel-heading">Login Here</div>
    <div class="panel-body">
    <c:forEach var="e" items="${errors}">
   				<c:if test="${fn:contains(e,'Invalid')}">
    				<div class="alert alert-danger" role="alert">
    					<b>${e.getObjectName()}!!</b> ${e.getDefaultMessage()}
    				</div>
   				</c:if>
	</c:forEach>
 	<form:form action="/ecomerce/login" method="post" >
    <div class="form-group">
      <label for="inputlg">Mail Id</label>
      <form:input class="form-control" path="mailId" type="email"/>
    </div>
    <div class="form-group">
      <label for="inputlg">Password</label>
      <form:input class="form-control" path="password" type="password"/>
    </div>
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="form-group">
    	<button type="submit" class="btn btn-primary">Login</button>
   	</div>
  	</form:form>
    </div>
    <div class="panel-footer">New User? click <a href="signUp">here</a></div>
  </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>