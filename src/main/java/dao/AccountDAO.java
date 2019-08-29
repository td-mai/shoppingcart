package dao;
import entity.Account;

public interface AccountDAO {
	public Account findAccount(String userName);
}
