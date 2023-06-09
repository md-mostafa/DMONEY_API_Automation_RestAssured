package testrunner;

import controller.Balance;
import controller.Create;
import controller.Deposit;
import controller.Login;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckBalanceTestRunner {
    Balance userBalance;
    Login login;
    Create create;
    Deposit deposit;


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
