package service.impl;

import dao.BankAccountDao;
import dao.impl.BankAccountDaoImpl;
import entity.BankAccount;
import service.BankAccountService;

/**
 * Created by Moon on 25/04/2021
 */
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountDao bankAccountDao = new BankAccountDaoImpl();


    @Override
    public boolean save(BankAccount bankAccount) {

        if(bankAccountDao.save(bankAccount)>0) {
            System.out.println("You Have been created an account successfully");
            return true;
        }else{
            System.out.println("DB Error");
            return false;
        }
    }

    @Override
    public BankAccount getAccountByCustomerId(Long customerId) {
        return bankAccountDao.getAccountByCustomerId(customerId);
    }

    @Override
    public int deleteAccount(BankAccount account) {
        return bankAccountDao.deleteAccount(account);
    }

    @Override
    public int deleteAccountById(int id) {
        return bankAccountDao.deleteAccountById(id);
    }

    @Override
    public int updateBalance(BankAccount account) {
        return bankAccountDao.updateBalance(account);
    }

}
