package testrunner;

import controller.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SendMoneyTestRunner {

    SendMoney sendMoney;
    Login login;
    Create create;
    Deposit deposit;
    Balance balance;

    @BeforeClass
    public void setupUsers(){
        login = new Login();
        login.loginWithValidCreds("salman@roadtocareer.net", "1234");

        create = new Create();
        create.createUserWithValidCreds("Customer");
        create.createUserWithValidCreds("Agent");
        create.createSecondUserWithValidCreds("Customer");

        deposit = new Deposit();
        deposit.depositToAgentWithValidAgentNum();
        deposit.depositToCustomerFromValidAgent();

        balance = new Balance();
        balance.checkCustomerBalanceWithValidCreds();

    }


    @Test(priority = 1, description = "Send money to invalid customer phone number")
    public void SendMoneyToInvalidCustomerAccount(){
        sendMoney = new SendMoney();
        sendMoney.sendMoneyToInvalidCustomerAccount();
        String msg_actual = sendMoney.getMesssage();
        Assert.assertTrue(msg_actual.contains("From/To Account does not exist"));
    }

    @Test(priority = 2, description = "Send moeny to agent number from customer account")
    public void SendMoneyToAgentFromCustomer(){
        sendMoney = new SendMoney();
        sendMoney.sendMoneyToAgentAccount();
        String msg_actual = sendMoney.getMesssage();
        Assert.assertTrue(msg_actual.contains("From/To account should not be an agent account"));
    }

    @Test(priority = 3, description = "Send money to another customer account")
    public void SendMoneyToAnotherCustomer(){
        sendMoney = new SendMoney();
        sendMoney.sendMoneyToAnotherCustomer();
        String msg_actual = sendMoney.getMesssage();
        Assert.assertTrue(msg_actual.contains("Send money successful"));

        int balance_actual = sendMoney.getCurrentBalance();
        int balance_expected = sendMoney.getUserBalanceBeforeSendingMoney()-sendMoney.getAmount()-sendMoney.getFee();

        Assert.assertEquals(balance_actual, balance_expected, "Balance mismatched");
    }
}
