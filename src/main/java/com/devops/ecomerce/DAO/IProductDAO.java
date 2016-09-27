package com.devops.ecomerce.DAO;

import java.util.List;

import com.devops.ecomerce.models.Category;
import com.devops.ecomerce.models.Product;
import com.devops.ecomerce.models.Seller;

public interface IProductDAO {
	public void addProduct(Product product);
	public Product getProduct(int productId);
	public void updateProduct(Product product);
	public List<Product> viewProducts(Category category);
	public List<Product> viewProducts();
	public void addSeller(Seller seller);
}
