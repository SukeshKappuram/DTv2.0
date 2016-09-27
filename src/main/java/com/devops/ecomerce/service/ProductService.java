package com.devops.ecomerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.ecomerce.DAO.IProductDAO;
import com.devops.ecomerce.models.Category;
import com.devops.ecomerce.models.Product;
import com.devops.ecomerce.models.Seller;

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

	public void updateProduct(Product product) {
		iProductDAO.updateProduct(product);
	}

	public List<Product> viewProducts(Category category) {
		return iProductDAO.viewProducts(category);
	}

	public List<Product> viewProducts() {
		return iProductDAO.viewProducts();
	}

	public void addSeller(Seller seller) {
		iProductDAO.addSeller(seller);		
	}

}
