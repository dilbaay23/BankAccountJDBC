package entity;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Moon on 25/04/2021
 */
class BankAccountTest {
    private BankAccount ba1;
    private BankAccount ba2;

    @BeforeEach
    void setUp(){
        ba1 = new BankAccount(110L,100);
        ba2 = new BankAccount(110L,100);
    }

    @Test
    void transferFundsForBiggerValue() {
        final  int expected = -1;
        final double result= ba1.transferFunds(200);

        assertTrue(expected == result);
    }

    @Test
    void transferFundsForSmallerThenZero() {
        final  int expected = -1;
        final double result= ba1.transferFunds(-5);

        assertTrue(expected == result);
    }

    @Test
    void transferFundsForEqualZero() {
        final  int expected = -1;
        final double result= ba1.transferFunds(0);

        assertTrue(expected == result);
    }

    @Test
    void transferFundsForInRangeValues() {
        final  int expected = 70;
        final double result= ba1.transferFunds(30);


        assertTrue(expected == result);
    }

    @Test
    void addFundsForSmallerThenZero() {
        final  int expected = -1;
        final double result= ba1.addFunds(-5);

        assertTrue(expected == result);
    }

    @Test
    void addFundsForEqualZero() {
        final  int expected = -1;
        final double result= ba1.addFunds(0);

        assertTrue(expected == result);
    }

    @Test
    void addFundsForInRangeValues() {
        final  int expected = 150;
        final double result= ba1.addFunds(50);

        assertTrue(expected == result);
    }
}