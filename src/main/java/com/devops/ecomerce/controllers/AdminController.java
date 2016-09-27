package com.devops.ecomerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.devops.ecomerce.models.Seller;
import com.devops.ecomerce.service.ICategoryService;
import com.devops.ecomerce.service.IProductService;

@Controller
public class AdminController {
	
	@Autowired(required=true)
	private IProductService iProductService;
	
	@Autowired(required=true)
	private ICategoryService iCategoryService;
	
	@RequestMapping(value="/order")
	public ModelAndView cart(){
		return new ModelAndView("viewOrders");
	}
	
	@RequestMapping(value="/sell")
	public ModelAndView sell(){
		return new ModelAndView("management","command",new Seller()).addObject("categories",iCategoryService.viewCategories()).addObject("products", iProductService.viewProducts());
	}
}
