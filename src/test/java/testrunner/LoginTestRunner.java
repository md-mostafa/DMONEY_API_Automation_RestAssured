package testrunner;

import controller.Login;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestRunner {
    Login login;

    @Test(priority = 1, description = "User login with invalid email")
    public void LoginWithInvalidEmail(){
        login = new Login();
        String msg = login.loginWithInvalidEmail("invalidEmail@gmail.com", "1234");
        Assert.assertTrue(msg.contains("User not found"), "User logged in");
    }

    @Test(priority = 2, description = "User login with valid credentials")
    public void LoginWithValidCreds(){
        login = new Login();
        String msg = login.loginWithValidCreds("salman@roadtocareer.net", "1234");
        Assert.assertTrue(msg.contains("Login successfully"), "Login unsuccessful");
    }
}
