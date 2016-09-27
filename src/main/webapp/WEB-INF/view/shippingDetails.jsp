<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<div class="panel panel-default">
			<div class="panel-heading">
    			<h3>Add New Shipping Address</h3>
    			<c:if test="${empty shippings}">
					<p style="color: red">Shipping address not found!! Please add a new delivery address</p>
            	</c:if>
    		</div>
			<div class="panel-body">   
               <div>
                	<form:form action="shipTo" method="post">
                	<fieldset>
                        <legend>New Address</legend>
                        <table>
                        	<tr>
                                <td>
                                    <form:input type="text" path="receiverName" placeholder="Name"/>
                                </td>
                                <td>
                                    <form:input type="text" path="phoneNumber" placeholder="Mobile No"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:input type="text" path="doorNo" placeholder="Door No / House No"/>
                                </td>
                                <td>
                                    <form:input type="text" path="street" placeholder="Street No /Name"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:input type="text" path="location" placeholder="Area"/>
                                </td>
                                <td>
                                    <form:input type="text" path="city" placeholder="City"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:input type="text" path="state" placeholder="State"/>
                                </td>
                                <td>
                                    <form:input type="text" path="pincode" placeholder="Pincode"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:textarea cols="30" rows="4" path="description" placeholder="Landmark"></form:textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                	<input class="btn btn-info" type="submit" value="Add New Address"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                	</form:form>
            	</div>
            </div>
            <div class="panel-footer"><a href="#">Continue Shopping</a></div>
            </div>
</body>
</html>