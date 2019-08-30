package config;

import java.util.Properties;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
//import dao.AccountDAO;
//import dao.OrderDAO;
//import dao.ProductDAO;
//import AccountDAOImpl;
//import dao.impl.OrderDAOImpl;
//import dao.impl.ProductDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import dao.AccountDAO;
import dao.ProductDAO;
import dao.impl.AccountDAOImpl;
import dao.impl.ProductDAOImpl;

@Configuration
@EnableTransactionManagement
// Load to Environment.
@PropertySource("classpath:ds-hibernate-cfg.properties")

public class ApplicationContextConfig {
	
	//Store the properties loaded by @PropertySource
	@Autowired
	private Environment env;
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		//Load property in message/validator properties
		rb.setBasenames(new String[] {"messages/validator"});
		return rb;
	}
	
	@Bean(name="viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	//Config for uploading
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		//Set max size
		commonsMultipartResolver.setMaxUploadSize(-1);
		return commonsMultipartResolver;
	}
	
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//see ds-hibernate-cfg.properties
		dataSource.setDriverClassName(env.getProperty("ds-database-driver"));
		dataSource.setUrl("ds.url");
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));
		
		System.out.println("## get data Source: "+ dataSource);
		return dataSource;
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception{
		Properties properties = new Properties();
		
		//ds-hibernate-cfg.properties
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
		
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		
		//Package contains the entity classes
		factoryBean.setPackagesToScan(new String[] {"entity"});
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();
		
		SessionFactory sf = factoryBean.getObject();
		System.out.println("##GetSessionFactory "+ sf);
		return sf;
		
	}
	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	
	@Bean(name="accountDAO")
	public AccountDAO getAccountDAO() {
		return new AccountDAOImpl();
	}
	
	@Bean(name="productDAO")
	public ProductDAO getProductDAO() {
		return new ProductDAOImpl();
	}
	
	
}
