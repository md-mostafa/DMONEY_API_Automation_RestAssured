package testrunner;

import controller.Withdraw;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WithdrawTestRunner {
    Withdraw withdraw;

    @Test(priority = 1, description = "Withdrawing money from invalid agent number")
    public void WithdrawMoneyFromInvalidAgent(){
        withdraw = new Withdraw();
        String msg_actual = withdraw.withdrawMoneyFromInvalidAgentNumber();
        Assert.assertTrue(msg_actual.contains("Account does not exist"));
    }

    @Test(priority = 2, description = "Withdrawing money from another customer")
    public void WithdrawMoneyFromAnotherCustomer(){
        withdraw = new Withdraw();
        String msg_actual = withdraw.withdrawMoneyFromAnotherCustomer();
        Assert.assertTrue(msg_actual.contains("Customer can not withdraw money from another customer"));
    }

    @Test(priority = 3, description = "Withdrawing money using valid credentials")
    public void WithdrawMoneyFromAgent(){
        withdraw = new Withdraw();
        withdraw.withdrawMoneyFromAgent();
        String msg_actual = withdraw.getMessage();
        int balanceBeforeDeposit = withdraw.getBalanceBeforeWithdraw();
        int fee = withdraw.getFee();
        int withdrawAmount = withdraw.getWithdrawAmount();
        Assert.assertTrue(msg_actual.contains("Withdraw successful"));
        int currentBalance = withdraw.getCurrentBalance();

        Assert.assertEquals(balanceBeforeDeposit-fee-withdrawAmount, currentBalance, "Balance mismatched");
    }
}
