package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;

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
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterHandler);
        app.start(8080);

        return app;
    }


    /**
     * Handler to post a new account
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postRegisterHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper

    }


}