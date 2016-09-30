package com.devops.ecomerce.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.devops.ecomerce.models.Seller;
import com.devops.ecomerce.models.ShippingAddress;
import com.devops.ecomerce.service.ICartService;
import com.devops.ecomerce.service.ICategoryService;
import com.devops.ecomerce.service.IProductService;
import com.devops.ecomerce.service.IUserService;

@Controller
@RequestMapping(value="/Seller")
public class SellerController {
	
	@Autowired(required=true)
	private IUserService iUserService;
	
	@Autowired(required=true)
	private IProductService iProductService;
	
	@Autowired(required=true)
	private ICategoryService iCategoryService;
	
	@Autowired(required=true)
	private ICartService iCartService;
	
	@RequestMapping(value="/order")
	public ModelAndView cart(){
		return new ModelAndView("viewOrders");
	}
	
	Seller s;
	
	@RequestMapping(value="/sell")
	public ModelAndView sell(){
		ModelAndView mv=new ModelAndView("management","command",new Seller());
		mv.addObject("categories",iCategoryService.viewCategories());
		mv.addObject("products", iProductService.viewProducts());
		mv.addObject("cart",iCartService);
		mv.addObject("sproducts",iProductService.viewProducts(iUserService.getUser()));
		mv.addObject("seller",s);
		return mv;
	}
	
	@RequestMapping(value="/shipFrom")
	public ModelAndView sShippingAddress(HttpServletRequest request){
		//int cartId=Integer.parseInt(request.getParameter("c"));
		ModelAndView mv= new ModelAndView("shippingDetails","command",new ShippingAddress()).addObject("cartItems",iCartService.viewCart(iUserService.getUser()));
		try{
			if(iUserService.viewShippingAddress().size()>0){
				mv=new ModelAndView("shippingDetails","command",new ShippingAddress()).addObject("shippings",iUserService.viewShippingAddress()).addObject("cartItems",iCartService.viewCart(iUserService.getUser()));
			}
		}
		catch(Exception e){
			mv=new ModelAndView("shippingDetails","command",new ShippingAddress()).addObject("cartItems",iCartService.viewCart(iUserService.getUser()));
		}
		return mv;
	}
	
	@RequestMapping(value="/edit")
	public String edit(HttpServletRequest request){
		int productId=Integer.parseInt(request.getParameter("p"));
		s=iProductService.getProduct(productId,iUserService.getUser());
		return "redirect:./sell";
	}
	
	@RequestMapping(value="/update")
	public String update(HttpServletRequest request){
		s.setQuantity(Integer.parseInt(request.getParameter("q")));
		s.setDiscount(Float.parseFloat(request.getParameter("d")));
		iProductService.updateSeller(s);
		s=null;
		return "redirect:./sell";
	}
	
	@RequestMapping(value="/delete")
	public String delete(HttpServletRequest request){
		int productId=Integer.parseInt(request.getParameter("p"));
		s=iProductService.getProduct(productId,iUserService.getUser());
		iProductService.delete(s);
		return "redirect:./sell";
	}
	
	@RequestMapping(value="/addSellerProduct")
	public String addSellerProduct(HttpServletRequest request,ModelMap model,@Valid @ModelAttribute("ecomerce") Seller s,BindingResult result){
		int pid=Integer.parseInt(request.getParameter("productId"));
		s.setProduct(iProductService.getProduct(pid));
		s.setUserId(iUserService.getUser());
		s.setShippingAddress(iUserService.getShippingAddress());
		iProductService.addSeller(s);
		return "redirect:./sell";
	}
	
	
}
