package DAO;
import Model.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Util.ConnectionUtil;

public class accountDAO {
  
  public Account addAccount(Account account){
    Connection connection = ConnectionUtil.getConnection();
    try{
        String sql = "INSERT INTO Account (username, password) VALUES (?,?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, account.getUsername());
        ps.setString(2, account.getPassword());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
          int generated_account_id = (int) rs.getLong(1);
          return new Account(generated_account_id, account.getUsername(), account.getPassword());
        }


    }catch(Exception e){
      System.out.println(e.getMessage());
    }
    return null;

  }
}
