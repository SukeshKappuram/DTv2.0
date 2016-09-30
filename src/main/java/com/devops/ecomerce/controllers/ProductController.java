package com.devops.ecomerce.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.devops.ecomerce.models.Product;
import com.devops.ecomerce.service.ICategoryService;
import com.devops.ecomerce.service.IProductService;
import com.devops.ecomerce.service.IUserService;
import com.devops.ecomerce.service.IUtilityService;

@Controller
@RequestMapping("/Product")
public class ProductController {
	
	Path path;
	String error;
	
	@Autowired(required=true)
	private ICategoryService iCategoryService;
	
	@Autowired(required=true)
	private IProductService iProductService;
	
	@Autowired(required=true)
	private IUserService iUserService;
	
	@Autowired(required=true)
	private IUtilityService iUtilityService;
	
	@RequestMapping(value="/products")
	public ModelAndView viewProducts(HttpServletRequest request){
		int categoryId=Integer.parseInt(request.getParameter("c"));
		return new ModelAndView("products","products",getProducts(iProductService.viewProducts(iCategoryService.viewCategory(categoryId)))).addObject("image", iUtilityService).addObject("user",iUserService);
	}
	
	@RequestMapping(value="/Details")
	public ModelAndView viewProduct(HttpServletRequest request){
		int productId=Integer.parseInt(request.getParameter("p"));
		return new ModelAndView("productDetails","product",iProductService.getProduct(productId)).addObject("user",iUserService);
	}
	
	public String getProducts(List<Product> products){
 		ObjectMapper mapper = new ObjectMapper();
        String jsonData="";
        try {
			jsonData=mapper.writeValueAsString(products);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return jsonData;
 	}
	
}
