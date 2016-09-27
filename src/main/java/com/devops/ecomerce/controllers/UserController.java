package com.devops.ecomerce.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.devops.ecomerce.models.Blog;
import com.devops.ecomerce.models.Forum;
import com.devops.ecomerce.models.Seller;
import com.devops.ecomerce.models.ShippingAddress;
import com.devops.ecomerce.models.User;
import com.devops.ecomerce.service.ICartService;
import com.devops.ecomerce.service.INetworkService;
import com.devops.ecomerce.service.IProductService;
import com.devops.ecomerce.service.IUserService;
import com.devops.ecomerce.service.IUtilityService;

@Controller
@RequestMapping("/User")
public class UserController {
	
	@Autowired(required=true)
	private IUserService iUserService;
	
	@Autowired(required=true)
	private INetworkService iNetworkService;
	
	@Autowired(required=true)
	private ICartService iCartService;
	
	@Autowired(required=true)
	private IProductService iProductService;
	
	@Autowired(required=true)
	private IUtilityService iUtilityService;

	List<ObjectError> errors;
	
	@RequestMapping(value="/signUp",method=RequestMethod.POST)
	public String signUp(HttpServletRequest request,ModelMap model,@Valid @ModelAttribute("ecomerce") User u,BindingResult result){
			u.setRegisteredDate(new Date());
			ObjectError oe= new ObjectError("Success", "You have been successfully Signed Up!!");
			if(iUserService.addUser(u)==0){
				oe= new ObjectError("Invalid", "Mail Id already Registered");
			}
			iUserService.addUser(u);
			result.addError(oe);
			errors=result.getAllErrors();
		iUtilityService.setErrors(errors);
		return "redirect:/signUp";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,ModelMap model,@Valid @ModelAttribute("ecomerce") User u,BindingResult result){
		iUserService.verifyUser(u);
		try{
			iUserService.getUser().equals(null);
		}
		catch(Exception e){
			ObjectError oe= new ObjectError("Invalid", "You mailId or password is incorrect");
			result.addError(oe);
			errors=result.getAllErrors();
			iUtilityService.setErrors(errors);
			return "redirect:/login";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/cart")
	public ModelAndView cart(){
		return new ModelAndView("cart");
	}
	
	@RequestMapping(value="/orders")
	public ModelAndView orders(){
		return new ModelAndView("orders");
	}
	
	@RequestMapping(value="/shipTo")
	public ModelAndView shippingAddress(HttpServletRequest request){
		//int cartId=Integer.parseInt(request.getParameter("c"));
		ModelAndView mv= new ModelAndView("shippingAddress","command",new ShippingAddress()).addObject("cartItems",iCartService.viewCart(iUserService.getUser()));
		try{
			if(iUserService.viewShippingAddress().size()>0){
				mv=new ModelAndView("shippingAddress","command",new ShippingAddress()).addObject("shippings",iUserService.viewShippingAddress()).addObject("cartItems",iCartService.viewCart(iUserService.getUser()));
			}
		}
		catch(Exception e){
			mv=new ModelAndView("shippingAddress","command",new ShippingAddress()).addObject("cartItems",iCartService.viewCart(iUserService.getUser()));
		}
		return mv;
	}
	
	@RequestMapping(value="/shipTo",method=RequestMethod.POST)
	public String addShippingAddress(HttpServletRequest request,ModelMap model,@Valid @ModelAttribute("ecomerce") ShippingAddress s,BindingResult result){
		s.setUserId(iUserService.getUser());
		iUserService.addShippingAddress(s);
		return "redirect:/shipTo";
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
	
	@RequestMapping(value="/review")
	public ModelAndView order(){
		return new ModelAndView("orders");
	}
	
	@RequestMapping(value="/approveSeller")
	public ModelAndView users(){
		return new ModelAndView("viewUser","users",iUserService.viewUsers());
	}
	
	@RequestMapping(value="/updateRole")
	public String updateRole(HttpServletRequest request){
		int roleId=Integer.parseInt(request.getParameter("u"));
		iUserService.updateRole(roleId);
		return "redirect:/User/approveSeller";
	}
	
	@RequestMapping(value="/addSellerProduct")
	public String addSellerProduct(HttpServletRequest request,ModelMap model,@Valid @ModelAttribute("ecomerce") Seller s,BindingResult result){
		int pid=Integer.parseInt(request.getParameter("productId"));
		s.setProduct(iProductService.getProduct(pid));
		s.setUserId(iUserService.getUser());
		s.setShippingAddress(iUserService.getShippingAddress());
		iProductService.addSeller(s);
		return "redirect:/User/sell";
	}
	
	@RequestMapping(value={"/Blogs"},method=RequestMethod.POST)
	public String viewSocialNetwork(HttpServletRequest request,ModelMap model,@RequestParam("file") MultipartFile file,@Valid @ModelAttribute("ecomerce") Blog b,BindingResult result){
		b.setCreatedDate(new Date());
		b.setUser(iUserService.getUser());
		iNetworkService.addBlog(b);
		iUtilityService.uploadImage(file, b);
		return "redirect:/Blogs";
	}
	
	@RequestMapping(value={"/Forums"},method=RequestMethod.POST)
	public String viewSocialNetwork(HttpServletRequest request,ModelMap model,@RequestParam("file") MultipartFile file,@Valid @ModelAttribute("ecomerce") Forum f,BindingResult result){
		f.setCreatedDate(new Date());
		f.setUser(iUserService.getUser());
		iNetworkService.addForum(f);
		iUtilityService.uploadImage(file, f);
		return "redirect:/Forums";
	}
}