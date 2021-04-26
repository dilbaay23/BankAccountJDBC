package dao;

import entity.BankAccount;
import entity.Customer;

/**
 * Created by Moon on 25/04/2021
 */
public interface BankAccountDao {
    int save(BankAccount account);
    BankAccount getAccountByCustomerId(Long customerId);
    int deleteAccount(BankAccount account);
    int deleteAccountById(int id);
    int updateBalance(BankAccount account);

}
