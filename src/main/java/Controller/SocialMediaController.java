package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.*;
import Service.*;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 * 
 * The included endpoints:
 * 
 * POST localhost:8080/register : Create a new Account
 * POST localhost:8080/login : Verify login
 * POST localhost:8080/messages : Submit a new post
 * GET localhost:8080/messages : Retrieve all messages
 * GET localhost:8080/messages/{message_id} : Retrieve message by ID
 * DELETE localhost:8080/messages/{message_id} : Delete message by ID
 * PATCH localhost:8080/messages/{message_id} : Update a message
 * GET localhost:8080/accounts/{account_id}/messages : Retrieve all messages written by a user
 * 
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    public SocialMediaController() {
        accountService = new AccountService();
        messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessagesHandler);
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{message_id}", this::getMessageByID);
        app.delete("/messages/{message_id}", this::deleteMessageByID);
        app.patch("/messages/{message_id}", this::updateMessage);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUser);

        return app;
    }


    /**
     * Handler to post a new account
     * ## 1: Our API should be able to process new User registrations.
     */
    private void postRegisterHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account existAccount = accountService.getAccountByUsername(account.getUsername());
        
        if (account.getUsername() != null && account.getPassword().length() >=4  && account.getUsername() != "" && existAccount == null) {
            Account addedAccount = accountService.addAccount(account);
            ctx.json(mapper.writeValueAsString(addedAccount));
            ctx.status(200);

        }
        else {
            ctx.status(400);
        }
    }
    /**
     * Handler to process User logins
     * ## 2: Our API should be able to process User logins.
     */
    private void postLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        
        // Parse the request body into an Account object
        Account inputAccount = mapper.readValue(ctx.body(), Account.class);
        
        // Validate input: username and password must not be null or empty
        if (inputAccount.getUsername() == null || inputAccount.getPassword() == null ||
            inputAccount.getUsername().isEmpty() || inputAccount.getPassword().isEmpty()) {
            ctx.status(401);
            return;
        }
    
        // Retrieve account from database by username
        Account existingAccount = accountService.getAccountByUsername(inputAccount.getUsername());
    
        // Check if the account exists and if the password matches
        if (existingAccount != null && existingAccount.getPassword().equals(inputAccount.getPassword())) {
            // Login successful: return the account including its account_id
            ctx.json(existingAccount);
        } else {
            // Login failed: return 401 Unauthorized
            ctx.status(401);
        }
    }
    /**
     * Handler to process new Messages
     * ## 3: Our API should be able to process the creation of new messages.
     */
    private void postMessagesHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message input = mapper.readValue(ctx.body(), Message.class);
        Account existAccount = accountService.getAccountByID(input.getPosted_by());
        if (input.getMessage_text() != "" && input.getMessage_text().length() <= 255 && existAccount != null) {
            Message addedMessage = messageService.addMessage(input);
           // int real_id = good.getMessage_id();
           // input.setMessage_id(addedMessage.getMessage_id());

            ctx.json(mapper.writeValueAsString(addedMessage));
            ctx.status(200);
        }
        else {
            ctx.status(400);
        }
    }
    /**
     * Handler to retrieve all messages
     * ## 4: Our API should be able to retrieve all messages.
     */
    private void getAllMessages(Context ctx) throws JsonProcessingException {
        ctx.json(messageService.getAllMessages());
    }
    /**
     * Handler to retrieve a message by its id
     * ## 5: Our API should be able to retrieve a message by its ID.
     */
    private void getMessageByID(Context ctx) throws JsonProcessingException {
        int messageId = Integer.parseInt(ctx.pathParam("{message_id}"));
        Message message = messageService.getMessageByID(messageId);
        if (message != null) {
            ctx.json(message);
        }
    }
    /**
     * Handler to delete a message by its id
     * ## 6: Our API should be able to delete a message identified by a message ID.
     */
    private void deleteMessageByID(Context ctx) throws JsonProcessingException {
        int messageId = Integer.parseInt(ctx.pathParam("{message_id}"));
        Message existingMessage = messageService.getMessageByID(messageId);
        if (existingMessage != null) {
            ctx.json(existingMessage);
        }
        messageService.deleteMessageByID(messageId);

    }
    /**
     * Handler to update a message by its id
     * ## 7: Our API should be able to update a message text identified by a message ID.
     */
    private void updateMessage(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message newMessage = mapper.readValue(ctx.body(), Message.class);
        int messageId = Integer.parseInt(ctx.pathParam("{message_id}"));
        Message message = messageService.getMessageByID(messageId);
        if (message != null && newMessage.getMessage_text().length() <= 255 && newMessage.getMessage_text() != "" && newMessage.getMessage_text() != null) {
            Message updatedMessage = messageService.updateMessage(messageId, message);
            String newText = newMessage.getMessage_text();
            updatedMessage.setMessage_text(newText);

            ctx.json(updatedMessage);
        }
        else {
            ctx.status(400);
        }
    
    }
    /**
     * Handler to retrieve all messages by a user
     * ## 8: Our API should be able to retrieve all messages written by a particular user.
     */
    private void getAllMessagesByUser(Context ctx) throws JsonProcessingException {
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        ctx.json(messageService.getAllMessagesFromUser(account_id));
    }
}