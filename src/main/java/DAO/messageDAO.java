package DAO;
import Model.Message;
import Model.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.h2.command.Prepared;
import Util.ConnectionUtil;

public class messageDAO {
  public Message createMessage(Message m){
    Connection connection = ConnectionUtil.getConnection();
    try{
      String query = "INSERT INTO message (message_text, posted_by, time_posted_epoch, message_id) VALUES (?,?,?,?)";
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setString(1, m.getMessage_text());
      ps.setInt(2, m.getPosted_by());
      ps.setLong(3, m.getTime_posted_epoch());
      ps.setInt(4, m.getMessage_id());
      ps.executeUpdate();
      return m;
    }
    catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return null;
  }
  public Message deleteMessage(Message m){
    return null;
  }
  public Message updateMessage(Message m){
    return null;
  }
  public Message getAllMessages(Account a){ //given userID
    return null;
  }
  
}
