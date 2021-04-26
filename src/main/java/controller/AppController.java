package controller;


import entity.BankAccount;
import entity.Customer;
import entity.Transfer;
import service.BankAccountService;
import service.CustomerService;
import service.impl.BankAccountServiceImpl;
import service.impl.CustomerServiceImpl;

import static utility.KeyboardUtility.*;
import static utility.MenuUtility.*;
import static utility.TextUtil.*;

/**
 * Created by Moon on 25/04/2021
 */
public class AppController {
    private int loginId= 0;
    private final BankAccountService bankAccountService = new BankAccountServiceImpl();
    private final CustomerService customerService= new CustomerServiceImpl();
    private Customer customer=null;

    public void start(){
        String line1= "MoonBank Application[version 1.0.2-SNAPSHOT]";
        System.out.println(line1);
        String line2= "@2021 Moon. All rights reserved.";
        System.out.println(line2);
        System.out.println();
        System.out.println("Welcome to Moon Bank");
        System.out.println();
        String title= "*   Moon Bank    *";
        printTitleTop(title);
        login();
    }

    private void login() {
        System.out.println(thickLine());
        System.out.println(thinLine());
        System.out.println(center("Welcome to LOGIN Page"));
        System.out.println(thinLine());
        System.out.println(thickLine());
        String phoneNumber = ask("Enter your Phone Number  : ");
        String password = ask("Enter your password : ");

        int checkId= customerService.checkCustomerForLogin(phoneNumber,password);
        if(checkId>0){
            loginId= checkId;
            customer = customerService.getCustomerById(loginId);
            showMenu();
        }else {
            if(askYorN("Do you want to try again? "))
                login();
            else
                quit();
        }
    }

    private void showMenu() {
        String title= "*  Main menu   *";
        printTitle(title);
        String[] options = {"Create An Account", "Account Details", "Transfer Details", "Transfer to Another Account", "Exit"};
        int chosenMenuOption = askForChoice(options);
        chooseMenuOption(chosenMenuOption);
    }

    private void chooseMenuOption(int chosenMenuOption) {

        switch(chosenMenuOption){
            case 0:
                createAccount();
                break;
            case 1:
                accountDetails();
                break;
            case 2:
                transferDetails();
                break;
            case 3:
                transferToOtherAccount();
                break;
            case 4:
                quit();
                break;
        }
    }

    private void transferToOtherAccount() {
        BankAccount bankAccountFrom = getAccountByCustomerId(loginId);
        if(bankAccountFrom==null){
            inValidLoginAccount();
        }else{
            BankAccount bankAccountTo = askRecipientCustomerIdForValidAccount();
            Transfer transfer = new Transfer();
            double amount = askForDouble("How much do you want to transfer from your account?");
            transfer.transferFromAccount1ToAccount2(bankAccountFrom,bankAccountTo,amount);
            bankAccountService.updateBalance(bankAccountFrom);
            bankAccountService.updateBalance(bankAccountTo);

        }

    }
    private BankAccount getAccountByCustomerId(int id){
        BankAccount bankAccount= bankAccountService.getAccountByCustomerId((long) id);
        return bankAccount;
    }

    private void transferDetails() {
        BankAccount bankAccountFrom = getAccountByCustomerId(loginId);
        if(bankAccountFrom==null){
            inValidLoginAccount();
        }else{
            Transfer transfer = new Transfer(bankAccountFrom.getId());
        }


    }
    private BankAccount askRecipientCustomerIdForValidAccount(){
        BankAccount bankAccountTo = null;
        while (bankAccountTo==null){
            int secondAccountId = askForInt("Enter the CUSTOMER ID of the Recipient Account");
             bankAccountTo = getAccountByCustomerId(secondAccountId);
             if(bankAccountTo==null){
                 System.out.println("There is no Account by this customer Id ");
                 if(!askYorN("Do you want to enter new customer Id of the Recipient Account")){
                     showMenu();
                 }
             }
        }
        return bankAccountTo;
    }

    private void inValidLoginAccount(){
        System.out.println("There is no Account by this customer Id for you.");
        if(askYorN("Do you want to create an account")){
            createAccount();
        }else{
            showMenu();
        }
    }


    private void accountDetails() {
        BankAccount bankAccount = bankAccountService.getAccountByCustomerId((long) loginId);
        if(bankAccount==null){
            inValidLoginAccount();
        }else{
            String subtitle="Details Of Account";
            printSubheading(subtitle);
            System.out.printf("Customer Name : %s %s%nAccount No    : %d%nBalance       : €%.2f%n",customer.getFirstName(), customer.getLastName(),bankAccount.getId(),bankAccount.getBalance());
        }
        showMenu();
    }

    private void createAccount() {
        if(customer==null) {
            System.out.println("There is no Customer By This id. You can not create Account ");
        }else if(hasAccount()){
            System.out.println("You have already an Account!... ");
            showMenu();
            }
        else{
            String title= "*  Create Account   *";
            printTitle(title);
            System.out.printf("Welcome %s %s ", customer.getFirstName(), customer.getLastName());
            double balance = askForDouble("Enter Your initial balance");
            BankAccount bankAccount = new BankAccount((long) loginId, balance);
             bankAccountService.save(bankAccount);
             showMenu();
        }

    }

    private boolean hasAccount() {
        if(getAccountByCustomerId(loginId)==null){
            return false;
        }else{
            return  true;
        }
    }

    public void quit() {
        System.out.println(tildaLine());
        System.out.println(tildaLine());
        System.out.println(center("*** GOODBYE :) ***"));
        System.out.println(center("*** SEE YOU LATER ***"));
        System.out.println(tildaLine());
        System.out.println(tildaLine());
        System.out.println(tildaLine());
        System.exit(0);

    }

}
