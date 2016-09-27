package com.devops.ecomerce.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devops.ecomerce.models.Blog;
import com.devops.ecomerce.models.Forum;
import com.devops.ecomerce.models.SocialNetwork;
import com.devops.ecomerce.models.User;

@Repository("SocialNetwork")
public class NetworkDAOImpl implements INetworkDAO {

	@Autowired
	private SessionFactory factory;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<SocialNetwork> viewNetworks(String network) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(Blog.class);
		if(network.equals("Forum")){
			cr=session.createCriteria(Forum.class);
		}
		cr.addOrder(Order.desc("createdDate"));
		List<SocialNetwork> networks =cr.list();
		for(SocialNetwork s:networks){
			System.out.println(s.getCreatedDate());
		}
		tx.commit();
		return networks;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<SocialNetwork> viewNetworks(String network,User user) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(Blog.class);
		if(network.equals("Forum")){
			cr=session.createCriteria(Forum.class);
		}
		cr.add(Restrictions.eq("user", user));
		cr.addOrder(Order.desc("createdDate"));
		List<SocialNetwork> networks =cr.list();
		for(SocialNetwork s:networks){
			System.out.println(s.getCreatedDate());
		}
		tx.commit();
		return networks;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public void addBlog(Blog b) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(b);
		tx.commit();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public void addForum(Forum f) {
		// TODO Auto-generated method stub
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(f);
		tx.commit();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public SocialNetwork getNetwork(String Id) {
		// TODO Auto-generated method stub
		System.out.print(Id);
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria ct=session.createCriteria(SocialNetwork.class);
		//ct.add(Restrictions.eq("id",Id));
		SocialNetwork sn=(SocialNetwork)ct.uniqueResult();
		tx.commit();
		return sn;
	}


}
