package testrunner;

import controller.Balance;
import controller.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckStatementTestRunner {
    Statement userSatement;

    @Test(priority = 1, description = "Creating user with existing email")
    public void CheckStatementUsingByInvalidTransactionId(){
        userSatement = new Statement();
        String msg_actual = userSatement.checkStatementByInvalidTransactionId();
        Assert.assertTrue(msg_actual.contains("Transaction not found"), "Transanction not found");
    }


    @Test(priority = 2, description = "Check statement using valid transaction id")
    public void  CheckStatementByValidTransactionId(){
        userSatement = new Statement();
        String msg_actual = userSatement.checkStatementByValidTransactionId();
        Assert.assertTrue(msg_actual.contains("Transaction list"), "Transaction list is not shown");
    }
}


