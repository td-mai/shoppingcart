package dao.impl;
import dao.AccountDAO;
import entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Transactional
public class AccountDAOImpl implements AccountDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Account findAccount(String userName) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery <Account> criteria = builder.createQuery(Account.class);
		Root<Account> root = criteria.from(Account.class);
		criteria.where(builder.equal(root.get("userName"), userName));
		
		Query<Account> query = session.createQuery(criteria);
		return (Account) query.uniqueResult();
				
	}
	
}
