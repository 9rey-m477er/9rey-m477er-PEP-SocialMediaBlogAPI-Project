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
      String query = "INSERT INTO message (message_text, posted_by, time_posted_epoch) VALUES (?,?,?)";
      PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, m.getMessage_text());
      ps.setInt(2, m.getPosted_by());
      ps.setLong(3, m.getTime_posted_epoch());
      ps.executeUpdate();
      
      ResultSet rs = ps.getGeneratedKeys();
      int message_id = 0;
      if(rs.next()){
        message_id = (int) rs.getInt(1);
      }
      return new Message(message_id, m.getPosted_by(), m.getMessage_text(), m.getTime_posted_epoch());
    }
    catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return null;
  }

  public Message deleteMessage(int id){
    Connection connection = ConnectionUtil.getConnection();
    try{
      String query = "DELETE * FROM message where message_id = ?";
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setInt(1, id);
      //ps.executeUpdate();
      ResultSet rs = ps.executeQuery();
      Message deleted = null;;
      if(rs.next()){
        deleted = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
      }
      return deleted;
    }catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return null;
    
  }
  public Message updateMessage(Message m){
    return null;
  }
  public Message getAllMessages(Account a){ //given userID
    return null;
  }
  
}
