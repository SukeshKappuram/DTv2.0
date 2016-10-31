<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KartooZ</title>
</head>
<body>
<div class="container" >
 <div class="jumbotron" style="background-image: url('http://www.panunited.com.sg/images/pics/banner-feedback.jpg');background-repeat: no-repeat;background-size:100% 300px; background-position: bottom;">
  <div class="row">
    <ul class="col-md-4" style="line-height: 20px;">
      <li>Query? Drop a note.</li>	
      <li style="font-size: 12px;"><span class="glyphicon glyphicon-map-marker"></span> Hyderabad, India</li>
      <li style="font-size: 12px;"><span class="glyphicon glyphicon-phone"></span> Phone: +91 4025879056</li>
      <li style="font-size: 12px;"><span class="glyphicon glyphicon-envelope"></span> Email: wecare@kartooz.com</li>
    </ul>
    <div class="col-md-8">
      <div class="row">
        <div class="col-sm-6 form-group">
          <input class="form-control" id="name" name="name" placeholder="Name" type="text" required>
        </div>
        <div class="col-sm-6 form-group">
          <input class="form-control" id="email" name="email" placeholder="Email" type="email" required>
        </div>
      </div>
      <textarea class="form-control" id="comments" name="comments" placeholder="Comment" rows="5"></textarea>
      <br>
      <div class="row">
        <div class="col-md-12 form-group">
          <button class="btn pull-right" style="color:black;" type="submit">Send</button>
        </div>
      </div>
    </div>
  </div>
  </div>
	</div>
</body>
</html>