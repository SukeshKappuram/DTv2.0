package com.devops.ecomerce.DAO;

import java.util.List;

import com.devops.ecomerce.models.Category;

public interface ICategoryDAO {
	public void addCategory(Category category);
	public List<Category> viewCategories();
	public Category viewCategory(int categoryId);
}
