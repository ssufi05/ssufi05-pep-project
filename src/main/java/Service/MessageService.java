package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;


/**
* POST localhost:8080/login : Verify login

*/

public class MessageService {
    private MessageDAO messageDAO;
    /**
     * no-args constructor for creating a new MessageService with a new MessageDAO.
     * There is no need to change this constructor.
     */
    public MessageService(){
        messageDAO = new MessageDAO();
    }
    /**
     * Constructor for a MessageService when a MessageDAO is provided.
     * @param messageDAO
     */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    /**
     * TODO: Use the MessageDAO to post a message. The given Message will not have an id provided.
     *
     * @param message a message object.
     * @return The persisted message if the persistence is successful.
     */
    public Message addMessage(Message message) {
        return messageDAO.insertMessage(message);
    }
    /**
     * TODO: Use the MessageDAO to retrieve all messages
     *
     * @param message a message object.
     * @return The persisted message if the persistence is successful.
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }
    /**
     * TODO: Use the MessageDAO to retrieve a message by its id
     *
     * @param id the id of the message object.
     * @return The message object.
     */
    public Message getMessageByID(int id) {
        return messageDAO.getMessageById(id);
    }
    /**
     * TODO: Use the MessageDAO to delete a message by its id
     *
     * @param message_id the id of the message object.
     * @return The message object.
     */
    public void deleteMessageByID(int message_id) {
        messageDAO.deleteMessageByID(message_id);
        
    }
    /**
     * TODO: Use the MessageDAO to update an existing message from the database.
     * You should first check that the message ID already exists.
     *
     * @param message_id the ID of the flight to be modified.
     * @param message an object containing all data that should replace the values contained by the existing message_id.
     *         the message object does not contain a message ID.
     * @return the newly updated message if the update operation was successful. Return null if the update operation was
     *         unsuccessful.
     */
    public Message updateMessage(int message_id, Message message){

        if (messageDAO.getMessageById(message_id) == null) {
            return null;
        }
        
        messageDAO.updateMessage(message_id, message);
        Message mes = messageDAO.getMessageById(message_id);
        return mes;
    }
    /**
     * TODO: Use the MessageDAO to retrieve a List containing all messages from a particular Account
     *
     * @param posted_by an int that references the account_id.
     * @return all messages departing from a particular account.
     */
    public List<Message> getAllMessagesFromUser(int posted_by) {
        
        return messageDAO.getAllMessagesFromUser(posted_by);
    }
}