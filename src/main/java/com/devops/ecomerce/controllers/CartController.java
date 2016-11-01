package com.devops.ecomerce.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.devops.ecomerce.models.Cart;
import com.devops.ecomerce.models.CartGroup;
import com.devops.ecomerce.models.CartItem;
import com.devops.ecomerce.service.ICartService;
import com.devops.ecomerce.service.IProductService;
import com.devops.ecomerce.service.IUserService;

@Controller
@RequestMapping("/Cart")
public class CartController {
	
	@Autowired(required=true)
	private IProductService iProductService;
	
	@Autowired(required=true)
	private ICartService iCartService;
	
	@Autowired(required=true)
	private IUserService iUserService;
	
	//CRUD on Cart
	
	@RequestMapping(value="/")
	public ModelAndView Cart(){
		return new ModelAndView("viewCart","command",new CartItem()).addObject("cart",iCartService.getCart(iUserService.getUser())).addObject("user",iUserService);
	}
	
	@RequestMapping(value={"/addToCart","/buyNow"})
	public String addToCart(HttpServletRequest request){
		int productId=Integer.parseInt(request.getParameter("c"));
		String redirect="redirect:/";
		
		try{
			System.out.println(iUserService.getUser().getFirstName());
		}catch(Exception e){
			redirect="redirect:/login";
			return redirect;
		}
		
		Cart cart=new Cart();
		cart.setCartId(iCartService.getCart(iUserService.getUser()).getCartId());
		cart.setUserId(iUserService.getUser());
		cart.setCartDate(new Date());
		cart.setPaid(false);
		
		CartGroup cartGroup=new CartGroup();
		cartGroup.setProductId(iProductService.getProduct(productId));
		cartGroup.setCartId(cart);
		
		CartItem cartItem=new CartItem();
		cartItem.setQuantity(1);
		cartItem.setTotatPrice(iProductService.getProduct(productId).getPrice());
		cartItem.setCartGroupId(cartGroup);
		
		cart.getCartItems().addAll(iCartService.getCart(iUserService.getUser()).getCartItems());
		cart.getCartItems().add(cartItem);
		
		iCartService.addToCart(cartItem);
		iCartService.updateCart(cart);
		
			redirect="redirect:/Cart/";
			if(request.getRequestURI().contains("buyNow")){
				redirect="redirect:/User/shipTo?c="+cart.getCartId()+"&&p="+productId;
			}
		return redirect;
	}
	
	@RequestMapping(value="/updateCart/{cart}/{product}")
	public String updateCart(HttpServletRequest requset ,@PathVariable(value="cart") Integer cartId,@PathVariable(value="product") Integer productId){
		CartItem cartItem=iCartService.getCart(productId, cartId).get(0);
		int q=Integer.parseInt(requset.getParameter("q"));
		cartItem.setQuantity(q);
		cartItem.setTotatPrice(cartItem.getCartGroupId().getProductId().getPrice()*q);
		iCartService.addToCart(cartItem);
		return "redirect:/Cart/";
	}
	
	@RequestMapping(value="/deleteCart/{cart}/{product}")
	public String deleteCartItem(@PathVariable(value="cart") Integer cartId,@PathVariable(value="product") Integer productId){
		iCartService.deleteCartItem(cartId, productId);
		return "redirect:/Cart/";
	}
	
	@RequestMapping(value="/deleteCart/{cart}")
	public String deleteCart(@PathVariable(value="cart") Integer cartId){
		iCartService.deleteCart(cartId);
		return "redirect:/Cart/";
	}
	
	//Payment Options
	
	@RequestMapping(value="/payment")
	public ModelAndView PayCart(){
		return new ModelAndView("Payment");
	}
	
	@RequestMapping(value="/card")
	public ModelAndView PayCard(){
		return new ModelAndView("card");
	}
	
	@RequestMapping(value={ "/cod", "/net","/wallet" })
	public ModelAndView PayCod(HttpServletRequest request){
		String payment="",str=request.getRequestURL().toString();
		str=str.substring(str.lastIndexOf("/")+1);
		if(str.equals("wallet")){
			payment="E-Wallet";
		}
		else if(str.equals("net")){
			payment="Net Banking";
		}
		else{
			payment="Cash on Delivery";
		}
		return new ModelAndView("cod","payment",payment);
	}
		
	//CRUD on Order
	
	@RequestMapping(value="/createOrder")
	public ModelAndView createOrder(){
		return new ModelAndView("wallet");
	}
	
	@RequestMapping(value="/viewOrder")
	public ModelAndView viewOrder(){
		return new ModelAndView("viewOrders");
	}
}
