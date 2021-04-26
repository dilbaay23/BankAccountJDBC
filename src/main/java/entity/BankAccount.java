package entity;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Moon on 25/04/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    private Long id;
    private Long customerId;
    private double balance;

    public BankAccount(Long customerId, double balance) {
        if(balance < 0) {
            throw new IllegalArgumentException("Starting balance cannot be less than zero");
        }else {
            this.customerId = customerId;
            this.balance = balance;
            System.out.println("Account initialized.\nBalance set to €" + this.balance);
        }
    }


    public double transferFunds(double withdrawal){
        if(withdrawal > this.balance){
            System.out.printf("Unable to transfer €%.2f. Balance is insufficient.\n",withdrawal);
            return -1;
        }else if(withdrawal <= 0){
            System.out.println("Transfer amount must be greater than zero. Transfer failed.");
            return -1;
        }else{
            this.balance -= withdrawal;
            System.out.printf("Transfer of €%.2f successful. Your new balance is €%.2f.\n",withdrawal,this.balance);
            return balance;
        }
    }

    public double addFunds(double deposit){
        if(deposit <= 0){
            System.out.println("Amount deposited must be greater than zero.");
            return -1;
        }else {
            this.balance += deposit;
          //  System.out.printf("Deposit of €%.2f successful. Your new balance is €%.2f.\n",deposit,this.balance);
            return balance;
        }
    }

}
