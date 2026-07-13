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
      Message delete = getMessageByID(id);
      String query = "DELETE message where message_id = ?";
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setInt(1, id);
      ps.executeUpdate();
      return delete;
    }catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return null;
    
  }
  public Message updateMessage(Message m){
    return null;
  }
  public List<Message> getAllMessages(int id){ //given userID
    Connection connection = ConnectionUtil.getConnection();
    try{
      String query = "Select * FROM message where posted_by = ?";
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        Message messageEntry = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
      }
    }catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return null;
  }

  public Message getMessageByID(int id){
    Connection connection = ConnectionUtil.getConnection();
    try{
      String query = "Select * FROM message where message_id = ?";
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      
      if(rs.next()){
        Message targetMessage = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
        return targetMessage;
      }
      

    }catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return null;
  }
  
}
