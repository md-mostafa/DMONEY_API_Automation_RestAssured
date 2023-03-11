package testrunner;

import controller.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DepositTestRunner {
    Balance userBalance;
    Login login;
    Create create;
    Deposit deposit;

    @Test(priority = 1, description = "Deposit to agent with invalid agent phone number")
    public void DepositToAgentWithInvalidPhone(){
        deposit = new Deposit();
        String msg = deposit.depositToAgentWithInvalidAgentNum();
        Assert.assertTrue(msg.contains("Account does not exist"), "Account found");
    }


    @Test(priority = 2, description = "Deposit to agent with valid agent phone number")
    public void DepositToAgentWithValidPhone(){
        deposit = new Deposit();
        String msg = deposit.depositToAgentWithValidAgentNum();
        Assert.assertTrue(msg.contains("Deposit successful"), "Deposit unsuccessful");
    }


    @Test(priority = 3, description = "Deposit to customer from another customer account")
    public void DepositToCustomerFromCustomer(){
        deposit = new Deposit();
        String msg = deposit.depositToCustomerFromCustomer();
        Assert.assertTrue(msg.contains("Only Agent can deposit money"), "Deposit successful");
    }


    @Test(priority = 4, description = "Deposit to customer from valid agent")
    public void DepositToCustomerFromValidAgent(){
        deposit = new Deposit();
        String msg = deposit.depositToCustomerFromValidAgent();
        Assert.assertTrue(msg.contains("Deposit successful"), "Deposit unsuccessful");
    }
}
