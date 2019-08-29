package dao.impl;
import dao.ProductDAO;
import entity.Product;
import model.ProductInfo;
import model.PaginationResult;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.Date;

@Transactional
public class ProductDAOImpl implements ProductDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Product findProduct(String code) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> productRoot = criteria.from(Product.class);
		criteria.where(builder.equal(productRoot.get("code"), code));
		Query <Product> query = session.createQuery(criteria);
		
		return (Product) query.uniqueResult();
	}
	@Override
	public ProductInfo findProductInfo(String code) {
		Product product = findProduct(code);
		if (product ==null) {
			return null;
		}
		return new ProductInfo(product.getCode(), product.getName(), product.getPrice());
	}
	@Override
	public void save(ProductInfo productInfo) {
		String code =productInfo.getCode();
		Product product = null;
		boolean isNew =false;
		if(code != null) {
			product = this.findProduct(code);
		}
		if (product ==null) {
			isNew= true;
			product = new Product();
			product.setCreatedDate(new Date());		
		}
		product.setCode(code);
		product.setName(productInfo.getName());
		product.setPrice(productInfo.getPrice());
		if (productInfo.getFileData()!=null) {
			byte[] image = productInfo.getFileData().getBytes();
			if(image!=null && image.length > 0) {
				product.setImage(image);
			}
		}
		if(isNew) {
			this.sessionFactory.getCurrentSession().persist(product);
		}
		this.sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName){
		String sql = "Select new "+ProductInfo.class.getName()+//
				" (p.code, p.name, p.price) from "+ Product.class.getName()+ " p ";
		//Select new ProductInfo(p.code,p.name, p.price) from Product p
		
		if(likeName!=null && likeName.length()>0) {
			sql += "Where lower(p.name) like:%"+ likeName.toLowerCase()+"% ";
		}
		sql += " order by p.createdDate desc";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		
		return new PaginationResult<ProductInfo>(query, page, maxResult,maxNavigationPage);
		
	} 
	@Override
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage){
		return queryProducts(page, maxResult, maxNavigationPage, null);
	}
	
	

}
