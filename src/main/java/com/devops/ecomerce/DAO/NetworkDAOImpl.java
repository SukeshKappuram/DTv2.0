package com.devops.ecomerce.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.colabaration.Blog;
import com.devops.ecomerce.models.colabaration.Forum;
import com.devops.ecomerce.models.colabaration.Friend;
import com.devops.ecomerce.models.colabaration.Share;
import com.devops.ecomerce.models.colabaration.SocialNetwork;
import com.devops.ecomerce.service.INetworkService;

@Repository
@Service("networkService")
public class NetworkDAOImpl implements INetworkService {

	@Autowired
	private SessionFactory factory;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public static final String REPLY_TO_ADDRESS="support@kartooz.com";
	public static final String FROM_ADDRESS="wecare@kartooz.com";

	Set<User> Users=new HashSet<User>();
	//CRUD on Networks
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<SocialNetwork> viewNetworks(String network) throws ParseException {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(Blog.class);
		if(network.equals("Forum")){
			System.out.println(network);
			cr=session.createCriteria(Forum.class);
		}
		cr.addOrder(Order.desc("createdDate"));
		List<SocialNetwork> networks =cr.list();
		tx.commit();
		return networks;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<SocialNetwork> viewNetworks(String network,User user) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		List<SocialNetwork> networks=new ArrayList<SocialNetwork>();
		if(network.equals("Friend")){
			Criteria cf=session.createCriteria(Friend.class);
			cf.add(Restrictions.eq("user.id",user.getId()));
			List<SocialNetwork> friendz=cf.list();
			
			Criteria cuf=session.createCriteria(Friend.class);
			List<Friend> fuzers=cuf.list();
			
			for(Friend ux:fuzers){
				List<User> uzers=new ArrayList();	
				uzers.addAll(ux.getFriends());
				if(uzers.get(0).getId().equals(user.getId())){
					friendz.add(ux);
					Users.add(ux.getUser());
				}
			}
			
			System.out.println("==================================");
			for(SocialNetwork fx:friendz){
				System.out.println(fx.getId());
			}
			System.out.println("==================================");
			networks=friendz;
		}else{
		Criteria cr=session.createCriteria(Blog.class);
		if(network.equals("Forum")){
			cr=session.createCriteria(Forum.class);
			System.out.println(network+" "+user.getId());
		}
		cr.add(Restrictions.eq("user.id", user.getId()));
		
		cr.addOrder(Order.desc("createdDate"));
		
			networks=cr.list();
			System.out.println(networks);
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
	
	public void addShare(Share s) {
		// TODO Auto-generated method stub
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(s);
		tx.commit();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public SocialNetwork getNetwork(String Id) {
		// TODO Auto-generated method stub
		System.out.print(Id);
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria ct=session.createCriteria(SocialNetwork.class);
		ct.add(Restrictions.eq("id",Id));
		SocialNetwork sn=(SocialNetwork)ct.uniqueResult();
		tx.commit();
		return sn;
	}
	
	public void deleteNetwork(int networkId) {
		// TODO Auto-generated method stub
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria ct=session.createCriteria(SocialNetwork.class);
		ct.add(Restrictions.eq("id",networkId));
		SocialNetwork sn=(SocialNetwork)ct.uniqueResult();
		session.delete(sn);
		tx.commit();
	}
	
	//Emailer
	
	public void send(User user,String subject,String body) throws MessagingException
	{
		System.out.println("Inside mail");
		MimeMessage mail=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mail,true);
		helper.setTo(user.getMailId());
		helper.setReplyTo(REPLY_TO_ADDRESS);
		helper.setFrom(FROM_ADDRESS);
		helper.setSubject(subject);
		helper.setText(body);
		helper.addBcc("sukesh.niithabsiguda@gmail.com");
		javaMailSender.send(mail);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<User> viewUsers(User u){
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria ct=session.createCriteria(User.class);
		List<User> allUser=ct.list();
		System.out.println("=============USER=================");
		List<User> nonFriends=new ArrayList<User>(allUser);
		for(User fx:allUser){
			System.out.println(fx.getId());
			for(User fy:Users){
				if(fy.getId().equals(fx.getId())){
					nonFriends.remove(fx);
				}
			}
			if(u.getId().equals(fx.getId())){
				System.out.println(u.getId()+"==="+fx.getId());
				nonFriends.remove(fx);
			}
		}
		nonFriends.remove(u);
		System.out.println("==================================");
		System.out.println("=============NoN Friends=================");
		for(User fx:nonFriends){
			System.out.println(fx.getId());
		}
		System.out.println("==================================");
		return nonFriends;
	}
		
	@Transactional(propagation=Propagation.SUPPORTS)
	public Friend getFriend(User u) {
		// TODO Auto-generated method stub
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria ct=session.createCriteria(Friend.class);
		ct.add(Restrictions.eq("user",u));
		Friend f=(Friend)ct.uniqueResult();
		tx.commit();
		return f;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void addFriend(Friend friend){
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(friend);
		tx.commit();
	}
	
	public void acceptFriend(int requestId){
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		Criteria ct=session.createCriteria(Friend.class);
		ct.add(Restrictions.eq("id",requestId));
		Friend f=(Friend)ct.uniqueResult();
		f.setAccepted(true);
		session.saveOrUpdate(f);
		tx.commit();
	}
}
