<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KartooZ</title>
<style>
#myImg {
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
}

#myImg:hover {opacity: 0.7;}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
}

/* Modal Content (image) */
.modal-content {
    margin: auto;
    display: block;
    width: 60%;
    max-width: 500px;
}

/* Caption of Modal Image */
#caption {
    margin: auto;
    display: block;
    width: 80%;
    max-width: 700px;
    text-align: center;
    color: #ccc;
    padding: 10px 0;
    height: 150px;
}

/* Add Animation */
.modal-content, #caption {
    -webkit-animation-name: zoom;
    -webkit-animation-duration: 0.6s;
    animation-name: zoom;
    animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
    from {-webkit-transform:scale(0)}
    to {-webkit-transform:scale(1)}
}

@keyframes zoom {
    from {transform:scale(0)}
    to {transform:scale(1)}
}

/* The Close Button */
.close {
    position: absolute;
    top: 15px;
    right: 35px;
    color: #f1f1f1;
    font-size: 40px;
    font-weight: bold;
    transition: 0.3s;
}

.close:hover,
.close:focus {
    color: #bbb;
    text-decoration: none;
    cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
    .modal-content {
        width: 100%;
    }
}
</style>
</head>
<body>
<%@include file="header.jsp" %>
<div class="jumbotron text-center">
  <h2>${product.name}</h2>
  <p>${product.description} !</p>
</div>
  
<div class="container" >
  <div class="row">
    <div class="col-sm-4">
      <div class="thumbnail">
          	<img id="myImg" src='/ecomerce/resources/images/product/${product.productId}.jpg' class="img-rounded" alt="Cinque Terre" style="max-width:300px;max-height:400px;">
       </div>
    </div>
    <div class="col-sm-4">
      <h3>Price</h3>
       <fmt:formatNumber var="prc" value="${product.price}"  maxFractionDigits="0" />
      <p>$ ${prc}</p>
      
      <c:if test="${product.available < 1}">
      	<p style="color:#cc00cc">Not in Stock</p>
      </c:if>
      <c:if test="${product.available > 1}">
      	<p>Available : ${product.available}</p>
      	<p><a href="../Cart/addToCart?c=${product.productId}" class="btn btn-info" role="button">Add To Cart</a></p>
      </c:if>
    </div>
    
    <div class="col-sm-4">
      <h3>Sellers</h3>
      <c:forEach var="s" items="${sellers}">
      <p>${s.userId.firstName} ${s.userId.lastName}</p>
      <p>${s.description}</p>
      <fmt:formatNumber var="dis" value="${s.discount}"  maxFractionDigits="0" />
      <p>Discount ${dis} %</p>
      <fmt:formatNumber var="disPrice" value="${product.price - (product.price div s.discount)}"  maxFractionDigits="0" />
      <p>Now for just Rs.  ${disPrice} only</p>
      <!-- <p>${user.getShippingAddress(s.userId).pincode}</p> -->
      <p><c:if test="${s.freeDelivery == true}"> Free Delivery with in ${distance.delivableDays(user.getShippingAddress().getPincode(),s.shippingAddress.pincode)}</c:if> Days</p>
      <p>Available : ${s.quantity}</p>
      <!-- <p>${s.shippingAddress.pincode}</p> -->
      <p><a href="#" class="btn btn-success" role="button">Buy Now</a> </p>
      </c:forEach>
    </div>
    
  </div>
</div>
<div id="myModal" class="modal">
  <span class="close">×</span>
  <img class="modal-content" id="img01">
  <div id="caption"></div>
</div>
<script>
// Get the modal
var modal = document.getElementById('myModal');

// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById('myImg');
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");
img.onclick = function(){
    modal.style.display = "block";
    modalImg.src = this.src;
    captionText.innerHTML = this.alt;
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}
</script>
<%@include file="footer.jsp" %>
</body>
</html>