package Service;

import java.sql.Connection;

import DAO.accountDAO;
import Model.Account;
import Util.ConnectionUtil;

public class accountService {
  
  public accountDAO accountDao;
  
  public accountService(){
    accountDao = new accountDAO();
  }

  
  public void login(){

  }
}
