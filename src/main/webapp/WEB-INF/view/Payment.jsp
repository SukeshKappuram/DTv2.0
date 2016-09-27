<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KartooZ</title>
        <style>
            .container table tr td{
                height: 50px;
            }
            .payfrm{
                width: 80%;
                height: 250px;
                float: left;
            }
            .button{
                width: 120px;
                height: 35px;
                background-color: #ffcc33;
                float: right;
            }
        </style>
        <script>
            function setFrame(loc,obj){
               document.pay.action=loc;
               document.pay.submit() ;
               if(obj.value==="COD"){
                   document.getElementById("agr").checked=true;
                   document.getElementById("sbt").disabled=false;
               }else{
                   document.getElementById("agr").checked=false;
                   document.getElementById("sbt").disabled=true;
                }
            }
            function fillCheck(){
                var theFrame = document.getElementsByTagName("iframe")[0];
                var theFrameDocument = theFrame.contentDocument || theFrame.contentWindow.document;
                document.getElementById("agr").checked=false;
                if(theFrameDocument.getElementById("cn").value.length===16)
                {
                    if(theFrameDocument.getElementById("nc").value.length>6)
                    {
                        if(theFrameDocument.getElementById("em").value.length>0)
                        {
                            if(theFrameDocument.getElementById("ey").value.length>1)
                            {
                                document.getElementById("sbt").disabled=false;
                                document.getElementById("agr").checked=true;
                            }else{alert('Invalid Expiry Year');} 
                        }else{alert('Invalid Expiry Month');}   
                    }else{alert('Invalid card name');}
                }else{alert('Invalid card Number');}
            }
        </script>
    </head>
    <body text="black">
    <%@include file="header.jsp" %> 
        <div class="container">
        <h2 style="color: white;">Payment</h2>
  			<div class="panel panel-default">
    		<div class="panel-heading">Select Payment Mode</div>
			<div class="panel-body">
            <form target="paym" name="pay">
            <table width="15%" border="1" style="float: left;">
                <tr>
                    <td><input type="radio" id="p" name="p" value="CC" onclick="setFrame('card',this)"/>&nbsp Credit Card</td>
                </tr>
                <tr>
                    <td><input type="radio" id="p" name="p" value="DC" onclick="setFrame('card',this)"/>&nbsp Debit Card</td>
                </tr>
                <tr>
                    <td><input type="radio" id="p" name="p" value="NB" onclick="setFrame('net',this)"/>&nbsp Net Banking</td>
                </tr>
                <tr>
                    <td><input type="radio" id="p" name="p" value="COD" onclick="setFrame('cod',this)"/>&nbsp Cash on Delivery</td>
                </tr>
                <tr>
                    <td><input type="radio" id="p" name="p" value="Wallet" onclick="setFrame('wallet',this)"/>&nbsp Wallet</td>
                </tr>
            </table>
            </form>
            <iframe class="payfrm" name="paym" frameborder='0'></iframe>
            <div Style="width:90%; height: 60px;text-align: right" >
                <%String uId=request.getParameter("productId");%>
                <div style="float:left;padding-left: 20px;">
                <input type="checkbox" name="agr" id="agr" required="required" onclick='fillCheck()'/>
                I agree for the payment towards the product
                <p style='font-size: 10px;line-height: 10%'>review this order before you finalize.</p>
                </div>
                <a href="../User/review?c=${cart.cartId}"><button id='sbt' class="btn btn-info" role="button" disabled="true">Place an Order</button></a>
            </div>
            </div>
            <div class="panel-footer"><a href="#">Continue Shopping</a></div>
        </div>
        </div>
        <%@include file="footer.jsp" %> 
    </body>
</html>
