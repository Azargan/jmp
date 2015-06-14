package com.abc;

import org.junit.Ignore;
import org.junit.Test;
import org.omg.PortableServer._ServantActivatorStub;

import static org.assertj.core.api.Assertions.*;

public class AccountTest {

    @Test
    public void testDeposit() {
        final Account account = new Account(Account.SAVINGS);
        account.deposit(100d);

        assertThat(account.transactions)
                .isNotNull()
                .hasSize(1);
        assertThatThrownBy(() -> account.deposit(-10d))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("amount must be greater than zero");
    }

    @Test
    public void testWithdraw() {
        final Account account = new Account(Account.SAVINGS);
        account.withdraw(100d);

        assertThat(account.transactions)
                .isNotEmpty()
                .hasSize(1);
        assertThatThrownBy(() -> account.withdraw(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("amount must be greater than zero");
    }

    @Test
    public void testInterestEarnedForSaving() {
        final Account savingSmallAccount = new Account(Account.SAVINGS);
        final Account savingBigAccount = new Account(Account.SAVINGS);
        savingSmallAccount.deposit(1000);
        savingBigAccount.deposit(100000);

        assertThat(savingSmallAccount.interestEarned()).isEqualTo(1d);
        assertThat(savingBigAccount.interestEarned()).isEqualTo(199d);
    }

    @Test
    public void testInterestEarnedForMaxiSaving() {
        final Account savingSmallAccount = new Account(Account.MAXI_SAVINGS);
        final Account savingMidleAccount = new Account(Account.MAXI_SAVINGS);
        final Account savingBigAccount = new Account(Account.MAXI_SAVINGS);
        savingSmallAccount.deposit(1000);
        savingMidleAccount.deposit(2000);
        savingBigAccount.deposit(100000);

        assertThat(savingSmallAccount.interestEarned()).isEqualTo(20d);
        assertThat(savingMidleAccount.interestEarned()).isEqualTo(70d);
        assertThat(savingBigAccount.interestEarned()).isEqualTo(9870d);
    }

    @Test
    public void testInterestEarnedForDefault() {
        final Account account = new Account(Account.CHECKING);
        account.deposit(1000);

        assertThat(account.interestEarned()).isEqualTo(1d);
    }
}
