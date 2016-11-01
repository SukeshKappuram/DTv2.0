<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>KartooZ</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://apis.google.com/js/platform.js" async defer></script>
  <link rel="icon" type="image/png" href="/ecomerce/resources/images/cart.png">
  <!-- google Script -->
  <script type="text/javascript">
        function login() 
        {
          var myParams = {
            'clientid' : 'YOUR_CLIENT_ID.apps.googleusercontent.com',
            'cookiepolicy' : 'single_host_origin',
            'callback' : 'loginCallback',
            'approvalprompt':'force',
            'scope' : 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
          };
          gapi.auth.signIn(myParams);
        }

        function loginCallback(result)
        {
            if(result['status']['signed_in'])
            {
                var request = gapi.client.plus.people.get(
                {
                    'userId': 'me'
                });
                request.execute(function (resp)
                {
                    /* console.log(resp);
                    console.log(resp['id']); */
                    var email = '';
                    if(resp['emails'])
                    {
                        for(i = 0; i < resp['emails'].length; i++)
                        {
                            if(resp['emails'][i]['type'] == 'account')
                            {
                                email = resp['emails'][i]['value'];//here is required email id
                            }
                        }
                    }
                   var usersname = resp['displayName'];//required name
                });
            }
        }
        function onLoadCallback()
        {
            gapi.client.setApiKey('YOUR_API_KEY');
            gapi.client.load('plus', 'v1',function(){});
        }

            </script>

        <script type="text/javascript">
              (function() {
               var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
               po.src = 'https://apis.google.com/js/client.js?onload=onLoadCallback';
               var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
             })();
        </script>
        <!-- End of google Script -->
  <style type="text/css">
  	#header{
		color: #093d2f;
		font-size:30px;
		font-style:italic;
		font-variant:small-caps;
		font-weight:bold;
		letter-spacing:10px;
		text-align: left;
		font-family: fantasy;
		text-decoration: underline;
	}
  </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <div class="container-fluid" style="font-size: 35px;">
    <img src="/ecomerce/resources/images/cart.png" style="width:50px;height: 50px;float: left;"/>
    <a class="navbar-brand" href="#" id="header" >KartooZ</a>
    </div>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <ul class="nav navbar-nav">
      <li><a href="${pageContext.request.contextPath}/">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/#about">About Us</a></li>
      <li><a href="${pageContext.request.contextPath}/#contact">Contact Us</a></li>
      <li><a href="${pageContext.request.contextPath}/#feedback">Feedback</a></li>
      <li><a href="${pageContext.request.contextPath}/Blogs">Blogs</a></li>
      <li><a href="${pageContext.request.contextPath}/Forums">Forums</a></li>
   	  </ul>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"><b class="caret"></b></a>
        <ul class="dropdown-menu">
          <c:if test="${empty user.getUser()}">
      		<li><a href="${pageContext.request.contextPath}/signUp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      		<li><a href="${pageContext.request.contextPath}/authenticate"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
     	  </c:if>
          <li class="divider"></li>
          <li><a><div id="mySignin" onclick="login()"><i class="fa fa-google"> Google</i></div></a></li>
        </ul>
      </li>
    </ul>
    
    <c:if test="${fn:length(user.getUser().firstName) > 0}">
    <ul class="nav navbar-nav navbar-right">
      <c:if test="${user.getRole()=='ROLE_USER' or user.getRole()=='ROLE_SELLER'}">
        <li><a href="${pageContext.request.contextPath}/Cart/"><span class="glyphicon glyphicon-shopping-cart"></span></a></li>
      </c:if>
                
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Hi ${user.getUser().firstName} !<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="${pageContext.request.contextPath}/Profile"><span class="glyphicon glyphicon-cog"></span> My Profile</a></li>
          <li><a href="${pageContext.request.contextPath}/Cart/viewOrder"><span class="glyphicon glyphicon-th"></span> My Orders</a></li>
          <li class="divider"></li>
          <li><a href="${pageContext.request.contextPath}/logout">Log out <span class="glyphicon glyphicon-log-out"></span></a></li>
        </ul>
      </li>
    </ul>
     </c:if>

    <div class="col-sm-3 col-md-3 navbar-right" >
        <form class="navbar-form" role="search">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search" name="q">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
            </div>
        </div>
        </form>
    </div>
  </div>
</nav>
</body>
</html>