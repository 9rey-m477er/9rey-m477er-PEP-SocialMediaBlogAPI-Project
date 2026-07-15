package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.accountService;
import Service.messageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

    accountService acServ;
    messageService meServ;

    public SocialMediaController(){
        this.acServ = new accountService();
        this.meServ = new messageService();
    }
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::accountCreationHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages/{message_id}", this::getMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesFromIDHandler);
        app.get("/messages", this::getAllMessagesHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void accountCreationHandler(Context context) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Account ac = om.readValue(context.body(), Account.class);
        Account newAccount = acServ.addAccount(ac);
        if(newAccount != null){
            context.json(om.writeValueAsString(newAccount));
        }
        else{
            context.status(400);
        }
    }
    private void loginHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Account ac = om.readValue(ctx.body(),Account.class);
        Account loggedIn = acServ.login(ac);
        if(loggedIn != null){
            ctx.json(om.writeValueAsString(loggedIn));
        }else{
            ctx.status(401);
        }
    }
    private void createMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Message mess = om.readValue(ctx.body(), Message.class);
        Message newMess = meServ.createMessage(mess);
        /*if(newMess.getMessage_text().equals("")){
            ctx.status(400);
        }*/
        if(newMess != null){
            ctx.json(om.writeValueAsString(newMess));
            ctx.status(200);
        }
        else{
            ctx.status(400);
        }
    }
    private void getMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        //Message mess = om.readValue(ctx.body(), Message.class);
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        Message targetMessage = meServ.getMessageByID(id);
        if(targetMessage != null){
            ctx.json(om.writeValueAsString(targetMessage));
            ctx.status(200);
        }
        else{
            ctx.status(200);
            ctx.result("");
        }

    }
    private void deleteMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        //Message mess = om.readValue(ctx.body(), Message.class);
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        Message deleted = meServ.deleteMessage(id);
        if(deleted == null){
            ctx.status(200);
        }
        else{
            ctx.json(om.writeValueAsString(deleted));
            ctx.status(200);
        }
    }

    private void updateMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Message mess = om.readValue(ctx.body(), Message.class);
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        String newMessage = mess.getMessage_text();
        Message target = meServ.updateMessage(id, newMessage);
        if(target == null){
            ctx.status(400);
        }
        else{
            ctx.json(om.writeValueAsString(target));
            ctx.status(200);
        }

    }
    private void getAllMessagesFromIDHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Account a = om.readValue(ctx.body(), Account.class);
        List<Message> messages = meServ.getAllMessages(a.getAccount_id());
        ctx.json(meServ.getAllMessages(a.getAccount_id()));

    }

    private void getAllMessages(Context ctx) throws JsonProcessingException{
        ctx.json(meServ.getAllMessages());
    }

}