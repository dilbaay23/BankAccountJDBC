package service;

import entity.BankAccount;


/**
 * Created by Moon on 25/04/2021
 */
public interface BankAccountService {
    boolean save(BankAccount bankAccount);
    BankAccount getAccountByCustomerId(Long customerId);
    int deleteAccount(BankAccount account);
    int deleteAccountById(int id);
    int updateBalance(BankAccount account);
}
