package com.devops.ecomerce.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.devops.ecomerce.models.Cart;
import com.devops.ecomerce.models.CartItem;
import com.devops.ecomerce.models.Category;
import com.devops.ecomerce.models.ListItem;
import com.devops.ecomerce.models.UserOrder;
import com.devops.ecomerce.models.Product;
import com.devops.ecomerce.models.Seller;
import com.devops.ecomerce.models.ShippingAddress;
import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.UserRole;
import com.devops.ecomerce.models.WishList;
import com.devops.ecomerce.models.colabaration.Blog;
import com.devops.ecomerce.models.colabaration.Comment;
import com.devops.ecomerce.models.colabaration.Forum;
import com.devops.ecomerce.models.colabaration.Friend;
import com.devops.ecomerce.models.colabaration.Share;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class ApplicationConfiguration{
	
	//Hibernates Properties
	
	private  Properties getHibernateProperties()
	{
		Properties properties=new Properties();
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	//SessionFactory Bean
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder localSessionFactoryBuilder=new LocalSessionFactoryBuilder(dataSource);
		localSessionFactoryBuilder.addProperties(getHibernateProperties());
		
		//Ecomerce Module
		
		localSessionFactoryBuilder.addAnnotatedClass(User.class);
		localSessionFactoryBuilder.addAnnotatedClass(UserRole.class);
		localSessionFactoryBuilder.addAnnotatedClass(Category.class);
		localSessionFactoryBuilder.addAnnotatedClass(Product.class);
		localSessionFactoryBuilder.addAnnotatedClass(CartItem.class);
		localSessionFactoryBuilder.addAnnotatedClass(Cart.class);
		localSessionFactoryBuilder.addAnnotatedClass(ShippingAddress.class);
		localSessionFactoryBuilder.addAnnotatedClass(UserOrder.class);
		localSessionFactoryBuilder.addAnnotatedClass(Seller.class);
		localSessionFactoryBuilder.addAnnotatedClass(WishList.class);
		localSessionFactoryBuilder.addAnnotatedClass(ListItem.class);
		
		//Colabration Module
		
		localSessionFactoryBuilder.addAnnotatedClass(Blog.class);
		localSessionFactoryBuilder.addAnnotatedClass(Comment.class);
		localSessionFactoryBuilder.addAnnotatedClass(Forum.class);
		localSessionFactoryBuilder.addAnnotatedClass(Share.class);
		localSessionFactoryBuilder.addAnnotatedClass(Friend.class);
		
		return localSessionFactoryBuilder.buildSessionFactory();
	}
	
	//Hibernate Transaction
	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager hibernateTransactionManager=new HibernateTransactionManager(sessionFactory);
		return hibernateTransactionManager;
		
	}
	
	//Emailer Properties
	
	@Autowired
	@Bean(name="javaMailSender")
	public JavaMailSender javaMailService() {
		
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
			
		Properties mailProperties = new Properties();
        
        mailProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailProperties.put("mail.smtp.auth","true");
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.smtp.port", "587");
        mailProperties.setProperty("mail.debug", "true");
        
	        javaMailSender.setJavaMailProperties(mailProperties);
	        javaMailSender.setHost("smtp.gmail.com");
	        javaMailSender.setPort(587);
	        javaMailSender.setProtocol("smtp");
	        javaMailSender.setUsername("yourmail@gmail.com");
	        javaMailSender.setPassword("p******d");
	        
	        return javaMailSender;
	    }

	    
}