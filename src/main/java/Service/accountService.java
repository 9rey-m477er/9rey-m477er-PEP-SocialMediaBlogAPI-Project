package Service;
import java.sql.Connection;
import DAO.accountDAO;
import Model.Account;
import Util.ConnectionUtil;

public class accountService {
  
  private accountDAO accountDao;
  
  public accountService(){
    accountDao = new accountDAO();
  }

  
  public Account login(Account ac){
    
    return accountDao.login(ac.getUsername(), ac.getPassword());
  }
  public Account addAccount(Account account){
    if(account.getUsername().equals("") || account.getPassword().length() < 4){
      return null;
    }
    return accountDao.addAccount(account);
  }
}
