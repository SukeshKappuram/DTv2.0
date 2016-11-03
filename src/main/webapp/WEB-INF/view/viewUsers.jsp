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
	<div class="panel panel-default" style="width:80%;margin:0 auto;">
    <div class="panel-heading" id="flip">Sellers</div>
    <div class="panel-body" id="panel">
	<c:forEach var="u" items="${users}" end="4">
		<div class="row">
			<div class="col-sm-2">
				${u.roleId.firstName}${u.roleId.lastName}
			</div>
			<div class="col-sm-2">
				${u.roleId.id}
			</div>
			<div class="col-sm-2">
				${u.roleName}
			</div>
			<div class="col-sm-2">
				<a href="updateRole/${u.roleId.id}">Make Seller</a>
			</div>
		</div>
	</c:forEach>
	</div>
    <div class="panel-footer"></div>          
	</div>
	</div>
</body>
</html>