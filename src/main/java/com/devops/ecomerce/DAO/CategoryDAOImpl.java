package com.devops.ecomerce.DAO;

import java.util.List;

import com.devops.ecomerce.models.Category;
import com.devops.ecomerce.models.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("Category")
public class CategoryDAOImpl implements ICategoryDAO {

	@Autowired
	private SessionFactory factory;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void addCategory(Category category) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
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

}
