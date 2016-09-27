package com.devops.ecomerce.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.devops.ecomerce.models.Category;
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
	
	@RequestMapping(value="/addProduct",method=RequestMethod.GET)
	public ModelAndView addProduct(){
		return new ModelAndView("addProduct","command",new Product()).addObject("categories", iCategoryService.viewCategories());
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.POST)
	public String storeProduct(HttpServletRequest request,ModelMap model,@RequestParam("file") MultipartFile file, @Valid @ModelAttribute("ecomerce") Product p,BindingResult result){
		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		p.setCategoryId(iCategoryService.viewCategory(categoryId));
		iProductService.addProduct(p);
		String fileName=null;
		try {
            fileName = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/product/" + fileName)));
            buffStream.write(bytes);
            buffStream.close();
            error= "You have successfully uploaded " + fileName;
            System.out.println("---------->"+error);
        } catch (Exception e) {
        	error="You failed to upload " + fileName + ": " + e.getMessage();
        	System.out.println(e);
        }
		File oldName = new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/product/" + fileName);
        File newName = new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/product/" + p.getProductId()+fileName.substring(fileName.indexOf(".")));
        System.out.println("new file name:--------------->"+newName);
        if(oldName.renameTo(newName)) {
           System.out.println(p.getProductId());
           error=p.getName()+" added Successfully !";
           System.out.println("");
        } 
        return "redirect:/addProduct";
	}
	
	@RequestMapping(value="/updateProduct",method=RequestMethod.GET)
	public ModelAndView updateProduct(){
		return new ModelAndView("updateProduct");
	}
	
	@RequestMapping(value="/updateProduct",method=RequestMethod.POST)
	public ModelAndView modifyProduct(){
		return new ModelAndView("updateProduct");
	}
	
	@RequestMapping(value="/Details")
	public ModelAndView viewProduct(HttpServletRequest request){
		int productId=Integer.parseInt(request.getParameter("p"));
		return new ModelAndView("productDetails","product",iProductService.getProduct(productId)).addObject("user",iUserService);
	}
	
	@RequestMapping(value="/addCategory",method=RequestMethod.GET)
	public ModelAndView addCategory(){
		return new ModelAndView("addCategory","command",new Category());
	}
	
	@RequestMapping(value="/addCategory",method=RequestMethod.POST)
	public String storeCategory(HttpServletRequest request,ModelMap model,@RequestParam("file") MultipartFile file, @Valid @ModelAttribute("ecomerce") Category c,BindingResult result){
		iCategoryService.addCategory(c);
		String fileName=null;
		try {
            fileName = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/category/" + fileName)));
            buffStream.write(bytes);
            buffStream.close();
            error= "You have successfully uploaded " + fileName;
            System.out.println("---------->"+error);
        } catch (Exception e) {
        	error="You failed to upload " + fileName + ": " + e.getMessage();
        	System.out.println(e);
        }
		File oldName = new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/category/" + fileName);
        File newName = new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/category/" + c.getId()+fileName.substring(fileName.indexOf(".")));
        System.out.println("new file name:--------------->"+newName);
        if(oldName.renameTo(newName)) {
           System.out.println(c.getId());
           error=c.getName()+" added Successfully !";
           System.out.println("");
        } 
        return "redirect:/addCategory";
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
