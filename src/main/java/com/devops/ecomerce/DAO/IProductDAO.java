package com.devops.ecomerce.DAO;

import java.util.List;

import com.devops.ecomerce.models.Category;
import com.devops.ecomerce.models.Product;
import com.devops.ecomerce.models.Seller;
import com.devops.ecomerce.models.User;

public interface IProductDAO {
	public void addProduct(Product product);
	public Product getProduct(int productId);
	public void deleteProduct(int productId);
	public List<Product> viewProducts(Category category);
	public List<Product> viewProducts();
	public void addSeller(Seller seller);
	public List<Product> viewProducts(User user);
	public Seller getProduct(int sellerId,User user);
	public void updateSeller(Seller seller);
	public void delete(Seller seller);
}
