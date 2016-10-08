package com.devops.ecomerce.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import com.devops.ecomerce.DAO.INetworkDAO;
import com.devops.ecomerce.models.Blog;
import com.devops.ecomerce.models.Forum;
import com.devops.ecomerce.models.SocialNetwork;
import com.devops.ecomerce.models.User;
@Service("networkService")
public class NetworkService implements INetworkService {

	@Autowired(required=true)
	private INetworkDAO iNetworkDAO;
	
	public List<SocialNetwork> viewNetworks(String network) {
		// TODO Auto-generated method stub
		return iNetworkDAO.viewNetworks(network);
	}

	public void addBlog(Blog b) {
		// TODO Auto-generated method stub
		iNetworkDAO.addBlog(b);
	}

	public void addForum(Forum f) {
		// TODO Auto-generated method stub
		iNetworkDAO.addForum(f);
	}

	public SocialNetwork getNetwork(String Id) {
		return iNetworkDAO.getNetwork(Id);
	}
	
	public List<SocialNetwork> viewNetworks(String network,User user){
		return iNetworkDAO.viewNetworks(network, user);
	}
	
	public static final String REPLY_TO_ADDRESS="support@kartooz.com";
	public static final String FROM_ADDRESS="wecare@kartooz.com";
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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
		helper.setText(body);
		helper.addBcc("sukesh.niithabsiguda@gmail.com");
		javaMailSender.send(mail);
	}
}
