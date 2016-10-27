package com.devops.ecomerce.controllers;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.devops.ecomerce.models.Cart;
import com.devops.ecomerce.models.ShippingAddress;
import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.UserOrder;
import com.devops.ecomerce.models.colabaration.Blog;
import com.devops.ecomerce.models.colabaration.Forum;
import com.devops.ecomerce.service.ICartService;
import com.devops.ecomerce.service.INetworkService;
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
	private IUtilityService iUtilityService;

	List<ObjectError> errors;
	
	//Security Controls
	
	@RequestMapping(value="/")
	public String home(){
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User)a.getPrincipal();
		iUserService.loadUser(currentUser.getUsername());
		return "redirect:/";
	}

	//Authentications
	
	
	
	
	/*
	@RequestMapping(value="/signUp",method=RequestMethod.POST)
	public ResponseEntity<Void> signUp(@RequestBody User user,UriComponentsBuilder ucBuilder){
		iUserService.addUser(user);
		String subject="Thanks for Registering with KartooZ";
		String body="This is a greeting mail from KartooZ";
		try {
			iNetworkService.send(user, subject,body);
			System.out.println("mail sent");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}*/
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public String login(HttpServletRequest request,ModelMap model,@Valid @ModelAttribute("ecomerce") User u,BindingResult result){
		System.out.println("Login");
		iUserService.verifyUser(u);
		try{
			iUserService.getUser().equals(null);
			if(iUserService.getRole().equals("ROLE_ADMIN")){
				return "redirect:/Admin/";
			}
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
	
	//Common Controls or Views
	
	@RequestMapping(value="/shipTo")
	public ModelAndView shippingAddress(HttpServletRequest request){
		int productId=0,cartId=0;
		try{
		productId=Integer.parseInt(request.getParameter("p"));
		cartId=Integer.parseInt(request.getParameter("c"));
		}catch(Exception e){}
		ModelAndView mv= new ModelAndView("shippingAddress","command",new ShippingAddress());
		
		try{
			if(iUserService.viewShippingAddress().size()>0){
				mv.addObject("shippings",iUserService.viewShippingAddress());
				System.out.println(iUserService.viewShippingAddress().size());
				if(productId>0){
					mv.addObject("cartItems",iCartService.getCart(productId,cartId));
				}
				else{
					mv.addObject("cartItems",iCartService.viewCart(iUserService.getUser()));
				}
			}
		}
		catch(Exception e){
			mv.addObject("cartItems",iCartService.viewCart(iUserService.getUser()));
		}
		return mv;
	}
	
	@RequestMapping(value="/shipTo",method=RequestMethod.POST)
	public String addShippingAddress(HttpServletRequest request,ModelMap model,@Valid @ModelAttribute("ecomerce") ShippingAddress s,BindingResult result){
		s.setUserId(iUserService.getUser());
		iUserService.addShippingAddress(s);
		return "redirect:/shipTo";
	}
	
	@RequestMapping(value="/review")
	public ModelAndView order(){
		UserOrder order=new UserOrder();
		Cart cart=iCartService.getCart(iUserService.getUser());
		cart.setPaid(true);
		order.setCartId(cart);
		order.setDelivered(false);
		order.setDeliveryDate(new Date());
		order.setOrderDate(new Date());
		iCartService.addOrder(order);
		return new ModelAndView("viewOrders","cartItems",iCartService.viewCart(iUserService.getUser()));
	}

	//Collabration Control vies
	
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