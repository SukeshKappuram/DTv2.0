package com.devops.ecomerce.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.colabaration.Blog;
import com.devops.ecomerce.models.colabaration.Forum;
import com.devops.ecomerce.service.ICategoryService;
import com.devops.ecomerce.service.INetworkService;
import com.devops.ecomerce.service.IProductService;
import com.devops.ecomerce.service.IUserService;
import com.devops.ecomerce.service.IUtilityService;

@Controller
public class HomeController {
	
	@Autowired(required=true)
	private ICategoryService iCategoryService;
	
	@Autowired(required=true)
	private INetworkService iNetworkService;
	
	@Autowired(required=true)
	private IUserService iUserService;
	
	@Autowired(required=true)
	private IProductService iProductService;
	
	@Autowired(required=true)
	private IUtilityService iUtilityService;
	
	//Home Controls

	@RequestMapping(value="/")
	public ModelAndView home(){
		return new ModelAndView("home","categories", iCategoryService.viewCategories()).addObject("image", iUtilityService).addObject("user",iUserService);
	}
	
	@RequestMapping(value="/about")
	public ModelAndView aboutUs(){
		return new ModelAndView("aboutUs");
	}
	
	@RequestMapping(value="/contact")
	public ModelAndView contactUs(){
		return new ModelAndView("contactUs");
	}
	
	@RequestMapping(value="/authenticate",method=RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("login","command",new User()).addObject("errors", iUtilityService.getErrors());
	}
	
	@RequestMapping(value="/signUp",method=RequestMethod.GET)
	@PreAuthorize("permitAll()")
	public ModelAndView signUp(){
		return new ModelAndView("signUp","command",new User()).addObject("errors", iUtilityService.getErrors());
	}
	
	//Colabration Landing Page
	
	@RequestMapping(value={"/Blogs","/Forums"})
	public ModelAndView viewSocialNetwork(HttpServletRequest request){
		ModelAndView mv=new ModelAndView("viewSocialNetwork","command",new Blog());
		String reqString=request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/')+1,request.getRequestURI().length()-1);
		System.out.println(reqString);
		if(reqString.equals("Forum")){mv=new ModelAndView("viewSocialNetwork","command",new Forum());}
		mv.addObject("networks",iUtilityService.getJson(iNetworkService.viewNetworks(reqString)));
		mv.addObject("userNetworks",iUtilityService.getJson(iNetworkService.viewNetworks(reqString,iUserService.getUser())));
		mv.addObject("network",reqString).addObject("user",iUserService);
		mv.addObject("image", iUtilityService);
		return mv;
	}
	
	//Ecomerce Product Pages
	
	@RequestMapping(value="/{category}/{id}")
	public ModelAndView viewProducts(@PathVariable(value="category") String categoryName,@PathVariable(value="id") Integer categoryId){
		return new ModelAndView("products","products",iUtilityService.getJson(iProductService.viewProducts(iCategoryService.viewCategory(categoryId)))).addObject("image", iUtilityService).addObject("user",iUserService);
	}
	
	@RequestMapping(value="/Product/{product}/{id}")
	public ModelAndView viewProduct(@PathVariable(value="product") String categoryName,@PathVariable(value="id") Integer productId) throws IOException{
		return new ModelAndView("productDetails","product",iProductService.getProduct(productId)).addObject("user",iUserService).addObject("sellers",iProductService.viewSellers(productId)).addObject("distance",iUtilityService);
	}

	//User Commom Controls
	
	List<ObjectError> errors;
	
	@RequestMapping(value="/signUp",method=RequestMethod.POST)
	@PreAuthorize("permitAll()")
	public String signUp(HttpServletRequest request,ModelMap model,@Valid @ModelAttribute("ecomerce") User u,BindingResult result){
			System.out.println(u);
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
	
	@RequestMapping(value="/Profile")
	public ModelAndView viewProfile(HttpServletRequest request){
		//int productId=Integer.parseInt(request.getParameter("p"));
		return new ModelAndView("profile","user",iUserService);
	}
	
	//Exception Handler
	
	@RequestMapping(value = "/403")
	public ModelAndView errorPage() {
		ModelAndView model = new ModelAndView("errorPage");
		return model;
	}
}
