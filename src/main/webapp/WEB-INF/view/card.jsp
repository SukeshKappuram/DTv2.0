<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KartooZ</title>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<script type="text/javascript" src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>

<!-- If you're using Stripe for payments -->
<script type="text/javascript" src="https://js.stripe.com/v2/"></script>
        <style>
        	body { margin-top:20px; }

/* CSS for Credit Card Payment form */
.panel-title {display: inline;font-weight: bold;}
.checkbox.pull-right { margin: 0; }
.pl-ziro { padding-left: 0px; }
.form-control.error {
    border-color: red;
    outline: 0;
    box-shadow: inset 0 1px 1px rgba(0,0,0,0.075),0 0 8px rgba(255,0,0,0.6);
}
label.error {
  font-weight: bold;
  color: red;
  padding: 2px 8px;
  margin-top: 2px;
}
.payment-errors {
  font-weight: bold;
  color: red;
  padding: 2px 8px;
  margin-top: 2px;
}
        </style>
        <script type="text/javascript">
        var $form = $('#payment-form');
        $form.on('submit', payWithStripe);

        /* If you're using Stripe for payments */
        function payWithStripe(e) {
            e.preventDefault();

            /* Visual feedback */
            $form.find('[type=submit]').html('Validating <i class="fa fa-spinner fa-pulse"></i>');

            var PublishableKey = 'pk_test_6pRNASCoBOKtIshFeQd4XMUh'; // Replace with your API publishable key
            Stripe.setPublishableKey(PublishableKey);
            Stripe.card.createToken($form, function stripeResponseHandler(status, response) {
                console.log
                if (response.error) {
                    /* Visual feedback */
                    $form.find('[type=submit]').html('Try again');
                    /* Show Stripe errors on the form */
                    $form.find('.payment-errors').text(response.error.message);
                    $form.find('.payment-errors').closest('.row').show();
                } else {
                    /* Visual feedback */
                    $form.find('[type=submit]').html('Processing <i class="fa fa-spinner fa-pulse"></i>');
                    /* Hide Stripe errors on the form */
                    $form.find('.payment-errors').closest('.row').hide();
                    $form.find('.payment-errors').text("");
                    // response contains id and card, which contains additional card details
                    var token = response.id;
                    console.log(token);
                    // AJAX
                    $.post('/account/stripe_card_token', {
                            token: token
                        })
                        // Assign handlers immediately after making the request,
                        .done(function(data, textStatus, jqXHR) {
                            $form.find('[type=submit]').html('Payment successful <i class="fa fa-check"></i>').prop('disabled', true);
                        })
                        .fail(function(jqXHR, textStatus, errorThrown) {
                            $form.find('[type=submit]').html('There was a problem').removeClass('success').addClass('error');
                            /* Show Stripe errors on the form */
                            $form.find('.payment-errors').text('Try refreshing the page and trying again.');
                            $form.find('.payment-errors').closest('.row').show();
                        });
                }
            });
        }

        /* Form validation */
        jQuery.validator.addMethod("month", function(value, element) {
          return this.optional(element) || /^(01|02|03|04|05|06|07|08|09|10|11|12)$/.test(value);
        }, "Please specify a valid 2-digit month.");

        jQuery.validator.addMethod("year", function(value, element) {
          return this.optional(element) || /^[0-9]{2}$/.test(value);
        }, "Please specify a valid 2-digit year.");

        validator = $form.validate({
            rules: {
                cardNumber: {
                    required: true,
                    creditcard: true,
                    digits: true
                },
                expMonth: {
                    required: true,
                    month: true
                },
                expYear: {
                    required: true,
                    year: true
                },
                cvCode: {
                    required: true,
                    digits: true
                }
            },
            highlight: function(element) {
                $(element).closest('.form-control').removeClass('success').addClass('error');
            },
            unhighlight: function(element) {
                $(element).closest('.form-control').removeClass('error').addClass('success');
            },
            errorPlacement: function(error, element) {
                $(element).closest('.form-group').append(error);
            }
        });

        paymentFormReady = function() {
            if ($form.find('[name=cardNumber]').hasClass("success") &&
                $form.find('[name=expMonth]').hasClass("success") &&
                $form.find('[name=expYear]').hasClass("success") &&
                $form.find('[name=cvCode]').val().length > 1) {
                return true;
            } else {
                return false;
            }
        }

        $form.find('[type=submit]').prop('disabled', true);
        var readyInterval = setInterval(function() {
            if (paymentFormReady()) {
                $form.find('[type=submit]').prop('disabled', false);
                clearInterval(readyInterval);
            }
        }, 250);
        </script>
    </head>
    <body>
<!-- Vendor libraries -->


<!-- Credit card form -->
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><img class="pull-right" src="http://i76.imgup.net/accepted_c22e0.png">Payment Details</h3>
                </div>
                <div class="panel-body">
                    <form role="form" id="payment-form">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="cardNumber">CARD NUMBER</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="cardNumber" placeholder="Valid Card Number" required autofocus data-stripe="number" />
                                        <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                                    </div>
                                </div>                            
                            </div>
                            <br/>
                        </div>
                        <div class="row">
                            <div class="col-xs-7 col-md-7">
                                <div class="form-group">
                                    <label for="expMonth">EXPIRATION DATE</label>
                                    <div class="col-xs-6 col-lg-6 pl-ziro">
                                        <select id='em' name="mnth">
                							<option value="">MM</option>
                							<%
                    							Date d=new Date();
                    							int y=d.getYear()+1900;
                    							for(int j=1;j<13;j++){
                        					%>
                        					<option value="<%=j%>"><%=j%></option>
                        					<%
                    							}
                							%>
            							</select>
                                        <select id='ey' name="year">
                							<option value="">YY</option>
                							<%
                    							for(int j=1;j<13;j++){
                        						y++;
                        					%>
                        					<option value="<%=y%>"><%=y%></option>
                        					<%
               								     }
                							%>
            							</select>
                                    </div>
                                </div>
                                <br/>
                            </div>
                            <div class="col-xs-5 col-md-5 ">
                                <div class="form-group">
                                    <label for="cvCode">CV CODE</label>
                                    <input type="password" class="form-control" name="cvCode" placeholder="CV" required data-stripe="cvc" />
                                </div>
                            </div>
                            <br/>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="couponCode">COUPON CODE</label>
                                    <input type="text" class="form-control" name="couponCode" />
                                </div>
                            </div>  
                            <br/>                      
                        </div>
                        <div class="row" style="display:none;">
                            <div class="col-xs-12">
                                <p class="payment-errors"></p>
                            </div>
                            <br/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
    </body>
</html>