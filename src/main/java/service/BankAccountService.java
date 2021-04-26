package service;

import entity.BankAccount;


/**
 * Created by Moon on 25/04/2021
 */
public interface BankAccountService {
    boolean save(BankAccount bankAccount);
    BankAccount getAccountByCustomerId(Long customerId);
}
