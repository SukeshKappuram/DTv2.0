<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KartooZ</title>
</head>
<body>
<%@ include file="header.jsp" %>
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
				<a href="updateRole?u=${u.roleId.id}">Make Seller</a>
			</div>
		</div>
	</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>