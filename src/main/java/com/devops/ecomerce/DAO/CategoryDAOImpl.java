package com.devops.ecomerce.DAO;

import java.util.Date;
import java.util.List;

import com.devops.ecomerce.models.Category;
import com.devops.ecomerce.service.ICategoryService;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Service("categoryService")
public class CategoryDAOImpl implements ICategoryService {

	@Autowired
	private SessionFactory factory;
	
	//CRUD Operations on Category
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void addCategory(Category category) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		category.setDated(new Date());
		session.saveOrUpdate(category);
		tx.commit();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Category> viewCategories() {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(Category.class);
		List<Category> categories=cr.list();
		tx.commit();
		return categories;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public Category viewCategory(int categoryId) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(Category.class);
		cr.add(Restrictions.eq("id",categoryId));
		return (Category)cr.uniqueResult();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void deleteCategory(int categoryId) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(Category.class);
		cr.add(Restrictions.eq("id",categoryId));
		Category category=(Category)cr.uniqueResult();
		tx.begin();
		session.delete(category);
		tx.commit();
	}

}
