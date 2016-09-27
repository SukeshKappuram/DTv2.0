<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KartooZ</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
  <h2 style="color: white;">Sign Up</h2>
  <div class="panel panel-default">
    <div class="panel-heading">New User</div>
    <div class="panel-body">
    	<c:forEach var="e" items="${errors}">
    			<c:if test="${fn:contains(e,'Success')}">
    				<div class="alert alert-success" role="alert">
    					<b>${e.getObjectName()}!!</b> ${e.getDefaultMessage()}
    				</div>
   				</c:if>
   				<c:if test="${fn:contains(e,'Invalid')}">
    				<div class="alert alert-danger" role="alert">
    					<b>${e.getObjectName()}!!</b> ${e.getDefaultMessage()}
    				</div>
   				</c:if>
   				<c:if test="${not (fn:contains(e,'Success') or fn:contains(e,'Invalid'))}">
    				<div class="alert alert-warning" role="alert">
    					<b>Error!!</b> ${e.getDefaultMessage()}
    				</div>
   				</c:if>
    	</c:forEach>
 	<form:form action="User/signUp" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label for="inputdefault">First Name</label>
      <form:input class="form-control" path="firstName" type="text"/>
    </div>
    <div class="form-group">
      <label for="inputlg">Last Name</label>
      <form:input class="form-control" path="lastName" type="text"/>
    </div>
    <div class="form-group">
      <label for="inputlg">Mail Id</label>
      <form:input class="form-control" path="mailId" type="email"/>
    </div>
    <div class="form-group">
      <label for="inputlg">Phone Number</label>
      <form:input class="form-control" path="phoneNumber" type="text"/>
    </div>
    <div class="form-group">
      <label for="inputlg">Password</label>
      <form:input class="form-control" path="password" type="password"/>
    </div>
    <div class="form-group">
    	<button type="submit" class="btn btn-primary">I Agree to Sign up KartooZ</button>
   	</div>
  	</form:form>
    </div>
    <div class="panel-footer"><a>Please read the conditions before you sign up</a></div>
  </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>