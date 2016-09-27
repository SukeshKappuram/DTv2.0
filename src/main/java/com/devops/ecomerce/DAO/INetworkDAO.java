package com.devops.ecomerce.DAO;

import java.util.List;

import com.devops.ecomerce.models.Blog;
import com.devops.ecomerce.models.Forum;
import com.devops.ecomerce.models.SocialNetwork;
import com.devops.ecomerce.models.User;

public interface INetworkDAO {
	public List<SocialNetwork> viewNetworks(String network);
	public void addBlog(Blog b);
	public void addForum(Forum f);
	public SocialNetwork getNetwork(String Id);
	public List<SocialNetwork> viewNetworks(String network,User user);
}
