package com.devops.ecomerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.ecomerce.DAO.ICategoryDAO;
import com.devops.ecomerce.models.Category;

@Service("categoryService")
public class CategoryService implements ICategoryService {

	@Autowired(required=true)
	private ICategoryDAO iCategoryDAO;

	public void addCategory(Category category) {
		iCategoryDAO.addCategory(category);
	}

	public List<Category> viewCategories() {
		return iCategoryDAO.viewCategories();
	}
	
	public Category viewCategory(int categoryId){
		return iCategoryDAO.viewCategory(categoryId);
	}

}
