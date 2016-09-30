package com.devops.ecomerce.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

@Controller
@RequestMapping(value="/Admin")
public class AdminController {
	
	Path path;
	String error;
	int productId=0;
	
	@Autowired(required=true)
	private IProductService iProductService;
	
	@Autowired(required=true)
	private ICategoryService iCategoryService;
	
	@Autowired(required=true)
	private IUserService iUserService;
	
	@RequestMapping(value="/")
	public ModelAndView admin(){
		return new ModelAndView("administration","products",iProductService.viewProducts());
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.GET)
	public ModelAndView addProduct(){
		return new ModelAndView("addProduct","command",new Product()).addObject("categories", iCategoryService.viewCategories());
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.POST)
	public String storeProduct(HttpServletRequest request,ModelMap model,@RequestParam("file") MultipartFile file, @Valid @ModelAttribute("ecomerce") Product p,BindingResult result){
		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		p.setCategoryId(iCategoryService.viewCategory(categoryId));
		p.setAvailable(0);
		if(productId!=0){
			p.setProductId(productId);
			productId=0;
		}
		System.out.println(p.getProductId());
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
        
		File oldName = new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/product/" + fileName);
        File newName = new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/product/" + p.getProductId()+fileName.substring(fileName.indexOf(".")));
        System.out.println("new file name:--------------->"+newName);
        if(oldName.renameTo(newName)) {
           System.out.println(p.getProductId());
           error=p.getName()+" added Successfully !";
           System.out.println("");
        } 
		} catch (Exception e) {
        	error="You failed to upload " + fileName + ": " + e.getMessage();
        	System.out.println(e);
        }
        return "redirect:./addProduct";
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
	
	@RequestMapping(value="/approveSeller")
	public ModelAndView users(){
		return new ModelAndView("viewUsers","users",iUserService.viewUsers());
	}
	
	@RequestMapping(value="/updateRole")
	public String updateRole(HttpServletRequest request){
		int roleId=Integer.parseInt(request.getParameter("u"));
		iUserService.updateRole(roleId);
		return "redirect:./approveSeller";
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(HttpServletRequest request){
		productId=Integer.parseInt(request.getParameter("p"));
		return new ModelAndView("addProduct","command",iProductService.getProduct(productId)).addObject("categories", iCategoryService.viewCategories());
	}
	
	@RequestMapping(value="/delete")
	public String delete(HttpServletRequest request){
		productId=Integer.parseInt(request.getParameter("p"));
		iProductService.deleteProduct(productId);
		return "redirect:./";
	}
}