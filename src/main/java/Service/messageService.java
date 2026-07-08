package Service;
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
  public Message deleteMessage(Message m){
    return messageDao.deleteMessage(m);
  }
  public Message updateMessage(Message m){
    return messageDao.updateMessage(m);
  }
  public Message getAllMessagse(Account a){
    return messageDao.getAllMessages(a);
  }
}
