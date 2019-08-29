package authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import dao.AccountDAO;
import entity.Account;
@Service
public class MyDBAuthenticationService implements UserDetailsService{
	@Autowired
	private AccountDAO accountDAO;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		Account account = accountDAO.findAccount(userName);
		System.out.println("Account ="+account);
		if (account ==null) {
			throw new UsernameNotFoundException("User "+ userName + " is not found");
		}
		
		
		//MANAGER, EMPLOYEE
		String role = account.getUserRole();
		List <GrantedAuthority> grantList = new ArrayList<GrantedAuthority> ();
		
		//ROLE_MANAGER
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
		grantList.add(authority);
		
		boolean enable = account.isActive();
		boolean accountNotExpired = true;
		boolean accountNonLock =true;
		boolean credentialsNonExpired = true;
		
		UserDetails userDetails = (UserDetails) new User(account.getUserName(), //
				account.getPassword(), enable, accountNotExpired, credentialsNonExpired, accountNonLock, grantList);
		
		return userDetails;
		
	}

}
