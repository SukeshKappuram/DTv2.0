package com.devops.ecomerce.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value="/Product/products")
	public ModelAndView viewProducts(HttpServletRequest request){
		int categoryId=Integer.parseInt(request.getParameter("c"));
		return new ModelAndView("products","products",iUtilityService.getJson(iProductService.viewProducts(iCategoryService.viewCategory(categoryId)))).addObject("image", iUtilityService).addObject("user",iUserService);
	}
	
	@RequestMapping(value="/Product/Details")
	public ModelAndView viewProduct(HttpServletRequest request) throws IOException{
		int productId=Integer.parseInt(request.getParameter("p"));
		
		return new ModelAndView("productDetails","product",iProductService.getProduct(productId)).addObject("user",iUserService).addObject("sellers",iProductService.viewSellers(productId)).addObject("distance",iUtilityService);
	}

	//Commom Controls
	
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
