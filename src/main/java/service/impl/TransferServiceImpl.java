package service.impl;


import dao.TransferDao;
import dao.impl.TransferDaoImpl;
import entity.Transfer;
import service.TransferService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moon on 25/04/2021
 */
public class TransferServiceImpl implements TransferService {
    private final TransferDao transferDao = new TransferDaoImpl();

    @Override
    public void save(Transfer transfer) {
        transferDao.save(transfer);
    }

    @Override
    public List<Transfer> getAllTransfersFromLoggedInCustomer(Long currentAccountFromId) {
        List<Transfer> transfers =  transferDao.getTransfersByFromCustomerId(currentAccountFromId);
        if(transfers.size()>0){
            int count = 1;
            for (Transfer transfer : transfers) {
                System.out.printf("%d. You have send to Account Id= %d, Amount= €%.2f,  at ", count,transfer.getFundToAccountId(),transfer.getAmount());
                System.out.println(transfer.getCreatedAt());

                count++;
            }
        }else{
            System.out.println("There is no transfer From you to anybody. ");
        }
        return transfers;
    }

    @Override
    public List<Transfer> getAllTransfersToLoggedInCustomer(Long currentAccountToId) {
        List<Transfer> transfers =  transferDao.getTransfersByToCustomerId(currentAccountToId);
        if(transfers.size()>0){
            int count = 1;
            for (Transfer transfer : transfers) {
                System.out.printf("%d. Account ID= %d sent you  Amount= €%.2f , at ", count,transfer.getFundToAccountId(),transfer.getAmount());
                System.out.println(transfer.getCreatedAt());
                count++;
            }
        }else{
            System.out.println("There is no transfer To you From anybody. ");
        }
        return transfers;
    }
}
