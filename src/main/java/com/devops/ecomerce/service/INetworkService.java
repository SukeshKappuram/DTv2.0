package com.devops.ecomerce.service;

import java.util.List;

import javax.mail.MessagingException;

import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.colabaration.Blog;
import com.devops.ecomerce.models.colabaration.Forum;
import com.devops.ecomerce.models.colabaration.Share;
import com.devops.ecomerce.models.colabaration.SocialNetwork;

public interface INetworkService {
	public List<SocialNetwork> viewNetworks(String network);
	public void addBlog(Blog b);
	public void addForum(Forum f);
	public void addShare(Share s);
	public SocialNetwork getNetwork(String Id);
	public List<SocialNetwork> viewNetworks(String network,User user);
	public void send(User user,String subject,String body)throws MessagingException;
}
