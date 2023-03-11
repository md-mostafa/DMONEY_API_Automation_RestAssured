package testrunner;

import controller.Balance;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBalanceTestRunner {
    Balance userBalance;

    @Test(priority = 1, description = "Creating user with existing email")
    public void CheckUserBalanceWithInvalidPhone(){
        userBalance = new Balance();
        String msg = userBalance.checkCustomerBalanceWithInvalidPhone();
        Assert.assertTrue(msg.contains("User not found"), "User found");
    }


    @Test(priority = 2, description = "Check customer 1 balance after succesful deposit")
    public void CheckCustomerBalanceWithValidCreds(){
        userBalance = new Balance();
        String msg = userBalance.checkCustomerBalanceWithValidCreds();
        Assert.assertTrue(msg.contains("User balance"), "User not found");
    }
}
