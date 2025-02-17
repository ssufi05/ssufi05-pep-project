package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;
    /**
     * no-args constructor for creating a new AuthorService with a new AuthorDAO.
     * There is no need to change this constructor.
     */
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    /**
     * Constructor for a AccountService when a AccountDAO is provided.
     * @param accountDAO
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    /**
     * TODO: Use the AccountDAO to persist an account. The given Account will not have an id provided.
     *
     * @param account an account object.
     * @return The persisted account if the persistence is successful.
     */
    public Account addAccount(Account account) {
        return accountDAO.insertAccount(account);
    }
    /**
     * TODO: Use the AccountDAO to retrieve an Account given the username.
     *
     * @param account_id the id of an account object.
     * @return The account object if it is in the table.
     */
    public Account getAccountByID(int account_id) {
        return accountDAO.getAccountByID(account_id);
    }
    /**
     * TODO: Use the AccountDAO to retrieve an Account given the username.
     *
     * @param username the username of an account object.
     * @return The account object if it is in the table.
     */
    public Account getAccountByUsername(String username) {
        return accountDAO.getAccountByUsername(username);
    }
}
