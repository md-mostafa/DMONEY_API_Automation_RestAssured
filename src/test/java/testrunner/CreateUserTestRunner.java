package testrunner;

import controller.Create;
import controller.Login;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserTestRunner {
    Create user;

    @Test(priority = 1)
    public void CreateUserWithExistingEmail(){
        user = new Create();
        String msg = user.createUserWithExistingEmail("Customer");
        Assert.assertTrue(msg.contains("User already exists"), "User created with exisiting email");
    }

    @Test(priority = 2)
    public void CreateUserWithExistingPhone(){
        user = new Create();
        String msg = user.createUserWithExistingPhone("Customer");
        Assert.assertTrue(msg.contains("User already exists"), "User created with exisiting phone");
    }

    @Test(priority = 3)
    public void CreateUserWithValidCreds(){
        user = new Create();
        String msg = user.createUserWithValidCreds("Customer");
        Assert.assertTrue(msg.contains("User created"), "User creation unsuccessful");
    }

    @Test(priority = 4)
    public void CreateAgentWithValidCreds(){
        user = new Create();
        String msg = user.createUserWithValidCreds("Agent");
        Assert.assertTrue(msg.contains("User created"), "Agent creation unsuccessful");
    }

}
