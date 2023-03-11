package testrunner;

import controller.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckStatementTestRunner {
    Balance userBalance;
    Login login;
    Create create;
    Deposit deposit;
    Statement userSatement;


    @Test(priority = 1, description = "Creating user with existing email")
    public void CheckStatementUsingByInvalidTransactionId(){
        userSatement = new Statement();
        userSatement.checkStatementByInvalidTransactionId();
        String msg_actual = userSatement.getMessage();
        Assert.assertTrue(msg_actual.contains("Transaction not found"), "Transanction not found");
    }


    @Test(priority = 2, description = "Check statement using valid transaction id")
    public void  CheckStatementByValidTransactionId(){
        userSatement = new Statement();
        userSatement.checkStatementByValidTransactionId();
        String msg_actual = userSatement.getMessage();
        Assert.assertTrue(msg_actual.contains("Transaction list"), "Transaction list is not shown");
    }

    @Test(priority = 3, description = "Check customer statement using invalid phone")
    public void CheckCustomerWithInvalidPhone(){
        userSatement = new Statement();
        userSatement.checkCustomerStatementWithInvalidPhone();
        String msg_actual = userSatement.getMessage();
        Assert.assertTrue(msg_actual.contains("User not found"), "Transaction found");
    }

    @Test(priority = 4, description = "Check customer statement using invalid phone")
    public void CheckCustomerWithValidPhone(){
        userSatement = new Statement();
        userSatement.checkCustomerStatementWithValidPhone();
        String msg_actual = userSatement.getMessage();
        Assert.assertTrue(msg_actual.contains("Transaction list"), "Transaction not found");
    }
}


