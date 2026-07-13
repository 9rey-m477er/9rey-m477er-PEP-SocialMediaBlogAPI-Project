package Service;
import java.util.List;

import DAO.accountDAO;
import DAO.messageDAO;
import Model.Message;
import Model.Account;

public class messageService {
  messageDAO messageDao;
  accountDAO accountDao;
  public messageService(){
    messageDao = new messageDAO();
    accountDao = new accountDAO();
  }
  public Message createMessage(Message m){
    if(m.getMessage_text() == null || m.getMessage_text().length() > 255 || m.getMessage_text().isBlank()){
      return null;
    }
    if(accountDao.getAccountByID(m.getPosted_by()) == null){
      return null;
    }
    return messageDao.createMessage(m);
  }

  public Message deleteMessage(int id){
    Message delete = messageDao.getMessageByID(id);
    if(delete != null){
      return messageDao.deleteMessage(id);
    }
    else{
      return null;
    }
    
  }
  
  public Message updateMessage(int id, String text){
    return messageDao.updateMessage(id, text);
  }

  public List<Message> getAllMessages(int id){
    return messageDao.getAllMessages(id);
  }
  public Message getMessageByID(int id){
    return messageDao.getMessageByID(id);
  }
}
