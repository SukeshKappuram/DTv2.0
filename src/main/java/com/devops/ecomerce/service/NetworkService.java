package com.devops.ecomerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
