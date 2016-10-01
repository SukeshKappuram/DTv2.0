package com.devops.ecomerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.ecomerce.DAO.IProductDAO;
import com.devops.ecomerce.models.Category;
import com.devops.ecomerce.models.Product;
import com.devops.ecomerce.models.Seller;
import com.devops.ecomerce.models.User;

@Service("productService")
public class ProductService implements IProductService {

	@Autowired(required=true)
	private IProductDAO iProductDAO;

	public void addProduct(Product product) {
		iProductDAO.addProduct(product);
	}

	public Product getProduct(int productId) {
		return iProductDAO.getProduct(productId);
	}

	public void deleteProduct(int productId) {
		iProductDAO.deleteProduct(productId);
	}

	public List<Product> viewProducts(Category category) {
		return iProductDAO.viewProducts(category);
	}

	public List<Product> viewProducts() {
		return iProductDAO.viewProducts();
	}
	
	public List<Product> viewProducts(User user) {
		return iProductDAO.viewProducts(user);
	}

	public void addSeller(Seller seller) {
		iProductDAO.addSeller(seller);		
	}
	
	public Seller getProduct(int sellerId,User user){
		return iProductDAO.getProduct(sellerId,user);
	}
	
	public void updateSeller(Seller seller){
		iProductDAO.updateSeller(seller);
	}
	
	public void delete(Seller seller){
		iProductDAO.delete(seller);
	}
	
	public List<Product> showProducts(){
		return iProductDAO.showProducts();
	}

	public List<Product> updateProductAvailablity(){
		return iProductDAO.updateProductAvailablity();
	}
}
