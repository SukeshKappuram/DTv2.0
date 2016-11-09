package com.devops.ecomerce.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.devops.ecomerce.models.Category;
import com.devops.ecomerce.models.Product;
import com.devops.ecomerce.service.ICategoryService;
import com.devops.ecomerce.service.IProductService;
import com.devops.ecomerce.service.IUserService;
import com.devops.ecomerce.service.IUtilityService;

@RestController
@RequestMapping(value="/Admin")
public class AdminController {
	
	String error;
	int productId=0;
	
	@Autowired(required=true)
	private IProductService iProductService;
	
	@Autowired(required=true)
	private ICategoryService iCategoryService;
	
	@Autowired(required=true)
	private IUserService iUserService;
	
	@Autowired(required=true)
	private IUtilityService iUtilityService;
	
	//Controller Page
	
	@RequestMapping(value="/")
	public ModelAndView admin(){
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)a.getPrincipal();
		System.out.println(currentUser.getUsername());
		iUserService.loadUser(currentUser.getUsername());
		return new ModelAndView("administration","products",iProductService.viewProducts()).addObject("user",iUserService);
	}
	
	//CRUD Operation on Product
	
	@RequestMapping(value="/Product",method=RequestMethod.GET)
	public ModelAndView addProduct(){
		return new ModelAndView("addProduct","command",new Product()).addObject("categories", iCategoryService.viewCategories());
	}
	
	@RequestMapping(value="/Product",method=RequestMethod.POST)
	public String addProduct(HttpServletRequest request,ModelMap model,@RequestParam("file") MultipartFile file, @Valid @ModelAttribute("ecomerce") Product p,BindingResult result){
		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		p.setCategoryId(iCategoryService.viewCategory(categoryId));
		p.setAvailable(0);
		if(productId!=0){
			p.setProductId(productId);
			productId=0;
		}
		System.out.println(p.getProductId());
		iProductService.addProduct(p);
		iUtilityService.uploadImage(p.getProductImage(), file.getOriginalFilename(), "product", p.getProductId());
        return "redirect:./";
	}
	
	@RequestMapping(value="/edit/{productId}")
	public ModelAndView editProduct(@PathVariable(value="productId") Integer productId){
		return new ModelAndView("addProduct","command",iProductService.getProduct(productId)).addObject("categories", iCategoryService.viewCategories());
	}
	
	@RequestMapping(value="/delete/{productId}")
	public String deleteProduct(@PathVariable(value="productId") Integer productId){
		iProductService.deleteProduct(productId);
		return "redirect:./";
	}
	
	//CRUD Operations on Category
	
	@RequestMapping(value="/Category")
	public ModelAndView addCategory(){
		return new ModelAndView("addCategory","command",new Category());
	}
	
	@RequestMapping(value="/Category",method=RequestMethod.POST)
	public String addCategory(ModelMap model,@RequestParam("file") MultipartFile file, @Valid @ModelAttribute("ecomerce") Category c,BindingResult result){
		iCategoryService.addCategory(c);
		iUtilityService.uploadImage(c.getCategoryImage(), file.getOriginalFilename(), "category", c.getId());
        return "redirect:./";
	}
	
	//CRUD Operations on User
	
	@RequestMapping(value="/approveSeller")
	public ModelAndView viewUsers(){
		return new ModelAndView("viewUsers","users",iUserService.viewUsers());
	}
	
	@RequestMapping(value="/updateRole/{roleId}")
	public String updateRole(@PathVariable(value="roleId") Integer roleId){
		iUserService.updateRole(roleId);
		return "redirect:./approveSeller";
	}
	
}