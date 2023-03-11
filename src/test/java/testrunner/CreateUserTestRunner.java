package testrunner;

import controller.Create;
import controller.Login;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateUserTestRunner {
    Create user;

    @Test(priority = 1, description = "Creating user with existing email")
    public void CreateUserWithExistingEmail(){
        user = new Create();
        String msg = user.createUserWithExistingEmail("Customer");
        Assert.assertTrue(msg.contains("User already exists"), "User created with exisiting email");
    }

    @Test(priority = 2, description = "Creating user with existing phone")
    public void CreateUserWithExistingPhone(){
        user = new Create();
        String msg = user.createUserWithExistingPhone("Customer");
        Assert.assertTrue(msg.contains("User already exists"), "User created with exisiting phone");
    }

    @Test(priority = 3, description = "Creating user with valid credentials")
    public void CreateUserWithValidCreds(){
        user = new Create();
        String msg = user.createUserWithValidCreds("Customer");
        Assert.assertTrue(msg.contains("User created"), "User creation unsuccessful");
    }

    @Test(priority = 4, description = "Creating agent with valid credentials")
    public void CreateAgentWithValidCreds(){
        user = new Create();
        String msg = user.createUserWithValidCreds("Agent");
        Assert.assertTrue(msg.contains("User created"), "Agent creation unsuccessful");
    }

    @Test(priority = 5, description = "Creating second user with valid credentials")
    public void CreateSecondUserWithValidCreds(){
        user = new Create();
        String msg = user.createSecondUserWithValidCreds("Customer");
        Assert.assertTrue(msg.contains("User created"), "User creation unsuccessful");
    }

}
