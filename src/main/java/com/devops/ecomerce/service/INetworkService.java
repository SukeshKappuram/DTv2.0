package com.devops.ecomerce.service;

import java.util.List;

import javax.mail.MessagingException;

import com.devops.ecomerce.models.Blog;
import com.devops.ecomerce.models.Forum;
import com.devops.ecomerce.models.SocialNetwork;
import com.devops.ecomerce.models.User;

public interface INetworkService {
	public List<SocialNetwork> viewNetworks(String network);
	public void addBlog(Blog b);
	public void addForum(Forum f);
	public SocialNetwork getNetwork(String Id);
	public List<SocialNetwork> viewNetworks(String network,User user);
	public void send(User user,String subject,String body)throws MessagingException;
}
