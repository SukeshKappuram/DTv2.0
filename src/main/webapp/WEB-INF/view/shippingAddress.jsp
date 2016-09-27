<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KartooZ</title>
        <style>
            .main{
                margin: 0 auto;
                width: 80%;
                color: black;
                height: auto;
                box-shadow: 0px 0px 10px #cccccc;
            }
            .button{
                width: 120px;
                height: 35px;
                background-color: #ffcc33;
                float: right;
            }
            p{
                line-height: 20%;
            }
            input[type="radio"] {
                -webkit-appearance: checkbox; /* Chrome, Safari, Opera */
                -moz-appearance: checkbox;    /* Firefox */
                -ms-appearance: checkbox;     /* not currently supported */
            }
            .invisible{
                border: none;
                color: #ffcc33;
                font-weight: 900;
            }
        </style>
    </head>
    <body>
    <%@include file="header.jsp" %> 
    <div class="container">
    	<h2 style="color: white;">Review your order</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Your Order Details</div>
			<div class="panel-body">
		        <div class="main">
        			<form action='../Cart/payment'>
            			<table style="margin:0 auto">
                			<tr>
                				<td width="50%">
					                <div style="float:left">
                    					<c:forEach var="c" items="${cartItems}">
                    					<img src="/ecomerce/resources/images/product/${c.cartGroupId.productId.productId}.jpg" style="float: left;max-width: 100px;max-height: 100px;padding-right: 5px;"/>
                    					<p><b>${c.cartGroupId.productId.name}</b></p><br/>
                    					<p><b style="color: #ffcc33">&#2352; ${c.cartGroupId.productId.price} </b></p>
                    					<p>your save: &#2352;  <u>(%) off</u></p><br/>
                    					<p>Quantity: ${c.quantity}</p><br/>
                    					<p><b>Sold By :</b> <a></a></p>
                    					</c:forEach>
                    					<p><b>Total No. Of Items :</b> <input class='invisible' type="text" name='nitems' value=''/></p>
                    					<p><b>Total amount :</b> &#2352; <input class='invisible' type="text" name='ta' value=''/></p>
                					</div>
                    			</td>
                    			<td style="text-align:right;">
                    				<c:if test="${not empty shippings}">
                    				<input class="btn btn-info" type="submit" value="Process To Payment" />
                    				</c:if>
                    			</td>
                			</tr>
                			<tr>
                    			<td colspan="2"><hr width='98%' heigth='2px'/></td>
                			</tr>
		                	<tr>
		                		<td>
            						<div>
            							<c:if test="${not empty shippings}">
            							<p><b>Delivery Address</b></p>
            							<c:forEach var="s" items="${shippings}" end="1">
               	 						<input type="radio" value="" name='addressId' checked="checked"/><b>${s.receiverName}</b>
                						<p>${s.doorNo}, ${s.street},</p>
                						<p>${s.description},</p>
                						<p>${s.location},${s.city},</p>
                						<p>${s.state},${s.pincode}.</p>
                						<p>Phone :${s.phoneNumber}</p>
                						</c:forEach>
                						<c:forEach var="s" items="${shippings}" begin="1">
               	 						<input type="radio" value="" name='addressId'/><b>${s.receiverName}</b>
                						<p>${s.doorNo}, ${s.street},</p>
                						<p>${s.description}, </p>
                						<p>${s.location},${s.city},</p>
                						<p>${s.state},${s.pincode}.</p>
                						<p>Phone :${s.phoneNumber}</p>
                						</c:forEach>
                						</c:if>
            						</div>
                    			</td>
                    			<td>
                					<div style="float:left">
                						<c:if test="${not empty shippings}">
                    					<% SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                        				Calendar cal = Calendar.getInstance();%>
                    					<p>Estimated Delivery </p>
                    					Delivered Between : <%=sdf.format(cal.getTime())%> - 
                   						<%cal.add(Calendar.DAY_OF_YEAR, 1);%>
                   						<%=sdf.format(cal.getTime())%>
                   						</c:if>
                					</div>
                				</td>
               				</tr>
                		</table>
             		</form>
           		</div>
       		</div>
    		<div class="panel-heading">
    			<h3>Add New Shipping Address</h3>
    			<c:if test="${empty shippings}">
					<p style="color: red">Delivery address not found!! Please add a new delivery address</p>
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
     	</div>
    <%@include file="footer.jsp" %> 
    </body>
</html>