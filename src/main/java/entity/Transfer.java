package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by Moon on 25/04/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    private Long id;
    private Long fundFromAccountId;
    private Long fundToAccountId;
    private LocalDateTime createdAt;
    private double amount;

    public Transfer(Long fundFromAccountId, Long fundToAccountId, double amount) {
        this.fundFromAccountId = fundFromAccountId;
        this.fundToAccountId = fundToAccountId;
      //  this.createdAt = LocalDateTime.now();
        this.amount = amount;
    }

    public int transferFromAccount1ToAccount2(BankAccount accountFrom, BankAccount accountTo, double amount){

        if(accountFrom.transferFunds(amount)>0){
            accountTo.addFunds(amount);
            return 1;
        }
        return -1;
    }
}
