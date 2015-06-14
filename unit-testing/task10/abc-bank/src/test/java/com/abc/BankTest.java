package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testGetFirstCustomer() {

        final Bank bankWithCustomer = new Bank();
        final Bank bankWithoutCustomer = new Bank();
        final Account account = new Account(Account.CHECKING);
        bankWithCustomer.addCustomer(new Customer("Bill").openAccount(account));

        assertThat(bankWithCustomer.getFirstCustomer()).isEqualToIgnoringCase("Bill");
        assertThat(bankWithoutCustomer.getFirstCustomer()).isEqualToIgnoringCase("Error");
    }

    @Test
    public void testCustomerSummary() {

        final Bank bankWithCustomer = new Bank();
        final Bank bankWithoutCustomer = new Bank();
        final Account account = new Account(Account.SAVINGS);
        bankWithCustomer.addCustomer(new Customer("Bill").openAccount(account));
        final Customer gregor = new Customer("Gregor")
                .openAccount(new Account((Account.CHECKING)))
                .openAccount(new Account(Account.SAVINGS));
        bankWithCustomer.addCustomer(gregor);

        assertThat(bankWithoutCustomer.customerSummary()).isEqualTo("Customer Summary");
        assertThat(bankWithCustomer.customerSummary())
                .isNotEmpty()
                .contains("Bill (1 account)")
                .contains("Gregor (2 accounts)");
    }
}
