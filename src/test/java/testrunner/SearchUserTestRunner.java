package testrunner;

import controller.Create;
import controller.Login;
import controller.Search;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchUserTestRunner {
    Search search;

    @Test(priority = 1, description = "Search User by invalid phone")
    public void SearchUserWithInvalidPhoneNumber(){
        search = new Search();
        String msg = search.searchUserByInvalidPhone();
        Assert.assertTrue(msg.contains("User not found"), "User found");
    }

    @Test(priority = 2, description = "Search User by valid phone")
    public void SearchUserWithValidPhoneNumber(){
        search = new Search();
        String msg = search.searchUserByValidPhone();
        Assert.assertTrue(msg.contains("User found"), "User not found");
    }
}
