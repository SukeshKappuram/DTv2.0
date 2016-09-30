package com.devops.ecomerce.DAO;

import java.util.List;
import com.devops.ecomerce.models.Category;
import com.devops.ecomerce.models.Product;
import com.devops.ecomerce.models.Seller;
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

@Repository("Product")
public class ProductDAOImpl implements IProductDAO {

	@Autowired
	private SessionFactory factory;

	@Transactional(propagation=Propagation.SUPPORTS)
	public void addProduct(Product product) {
		Session session=factory.getCurrentSession();
	    Transaction tx=session.beginTransaction();
	    tx.begin();
	    session.saveOrUpdate(product);
	    tx.commit();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public Product getProduct(int productId) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(Product.class);
		cr.add(Restrictions.eq("id",productId));
		Product product=(Product)cr.uniqueResult();
		tx.commit();
		return product;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public void deleteProduct(int productId) {
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		session.delete(getProduct(productId));
		tx.commit();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Product> viewProducts(Category category) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(Product.class);
		cr.add(Restrictions.eq("categoryId",category));
		List<Product> products =cr.list();
		tx.commit();
		return products;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Product> viewProducts() {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(Product.class);
		List<Product> products = cr.list();
		tx.commit();
		return products;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public void addSeller(Seller seller) {
		Session session=factory.getCurrentSession();
	    Transaction tx=session.beginTransaction();
	    tx.begin();
	    session.saveOrUpdate(seller);
	    tx.commit();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Product> viewProducts(User user){
		Session session=factory.getCurrentSession();
	    Transaction tx=session.beginTransaction();
	    tx.begin();
	    Criteria cr=session.createCriteria(Seller.class);
	    cr.add(Restrictions.eq("userId",user));
	    List<Product> products = cr.list();
	    tx.commit();
	    return products;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public Seller getProduct(int sellerId,User user){
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(Seller.class);
		cr.add(Restrictions.eq("id",sellerId));
		cr.add(Restrictions.eq("userId",user));
		Seller seller=(Seller)cr.uniqueResult();
		tx.commit();
	    return seller;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void updateSeller(Seller seller){
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		session.update(seller);
		tx.commit();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void delete(Seller seller){
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		session.delete(seller);
		tx.commit();
	}
}
