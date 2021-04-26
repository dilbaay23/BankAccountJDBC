package service;

import entity.Transfer;

import java.util.List;

/**
 * Created by Moon on 25/04/2021
 */
public interface TransferService {
    void save(Transfer transfer);

    List<Transfer> getAllTransfersFromLoggedInCustomer(Long currentAccountFromId);

    List<Transfer> getAllTransfersToLoggedInCustomer(Long currentAccountToId);
}
