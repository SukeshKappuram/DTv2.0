package com.devops.ecomerce.service;

import java.util.List;

import com.devops.ecomerce.models.Category;

public interface ICategoryService {
	public void addCategory(Category category);
	public List<Category> viewCategories();
	public Category viewCategory(int categoryId);
}
