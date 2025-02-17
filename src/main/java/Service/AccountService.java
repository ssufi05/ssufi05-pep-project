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
}
