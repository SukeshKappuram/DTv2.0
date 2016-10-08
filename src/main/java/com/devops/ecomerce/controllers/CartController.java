package com.devops.ecomerce.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value="/cart")
	public ModelAndView Cart(){
		return new ModelAndView("viewCart","cartItems",iCartService.viewCart(iUserService.getUser())).addObject("cart",iCartService.getCart(iUserService.getUser())).addObject("user",iUserService);
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
		Cart cart=iCartService.getCart(iUserService.getUser());
		CartItem cartItem=new CartItem();
		cartItem.setQuantity(1);
		cartItem.setTotatPrice(iProductService.getProduct(productId).getPrice());
		CartGroup cartGroup=new CartGroup();
		cartGroup.setCartId(cart);
		cartGroup.setProductId(iProductService.getProduct(productId));
		cartItem.setCartGroupId(cartGroup);
		iCartService.addToCart(cartItem);
			redirect="redirect:/Cart/cart";
			if(request.getRequestURI().contains("buyNow")){
				redirect="redirect:/User/shipTo?c="+cart.getCartId()+"&&p="+productId;
			}
		return redirect;
	}
	
	@RequestMapping(value="/updateCart")
	public String updateCart(HttpServletRequest request){
		return "redirect:/Cart/cart";
	}
	
	@RequestMapping(value="/deleteCart")
	public String deleteCart(HttpServletRequest request){
		return "redirect:/Cart/cart";
	} 
	
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
		
	@RequestMapping(value="/createOrder")
	public ModelAndView createOrder(){
		return new ModelAndView("wallet");
	}
	
	@RequestMapping(value="/viewOrder")
	public ModelAndView viewOrder(){
		return new ModelAndView("viewOrders","cartItems",iCartService.viewCart(iUserService.getUser()));
	}
}
