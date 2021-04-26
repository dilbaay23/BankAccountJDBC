package dao;

import entity.BankAccount;
import entity.Transfer;

import java.util.List;

/**
 * Created by Moon on 25/04/2021
 */
public interface TransferDao {
    List<Transfer> getTransfersByFromCustomerId(Long accountFromId);
    List<Transfer> getTransfersByToCustomerId(Long accountToId);

    int save(Transfer transfer);
}
