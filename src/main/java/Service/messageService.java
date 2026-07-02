package Service;
import DAO.messageDAO;

public class messageService {
  messageDAO messageDao;
  
  public messageService(){
    messageDao = new messageDAO();
  }
}
