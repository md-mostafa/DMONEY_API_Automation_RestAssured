package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import model.TransanctionModel;
import utils.ConfigUtils;

import static io.restassured.RestAssured.given;

@Getter
@Setter
public class SendMoney {
    private String messsage;
    private int fee;
    private int userBalanceBeforeSendingMoney;
    private int currentBalance;
    private int amount;


    public void sendMoneyToInvalidCustomerAccount(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String fromAccount = ConfigUtils.getProperty("user_phone");
        String toAccount = "1231";
        double amount = 50;
        TransanctionModel tranModel = new TransanctionModel(fromAccount, toAccount, amount);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                        .body(tranModel)
                .when()
                        .post("/transaction/sendmoney")
                .then()
                        .assertThat().statusCode(404).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        setMesssage(jsonPath.get("message"));
    }



    public void sendMoneyToAgentAccount(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String fromAccount = ConfigUtils.getProperty("user_phone");
        String toAccount = ConfigUtils.getProperty("agent_phone");
        double amount = 50;
        TransanctionModel tranModel = new TransanctionModel(fromAccount, toAccount, amount);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                        .body(tranModel)
                        .when()
                        .post("/transaction/sendmoney")
                        .then()
                        .assertThat().statusCode(208).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        setMesssage(jsonPath.get("message"));
    }


    public void sendMoneyToAnotherCustomer(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String fromAccount = ConfigUtils.getProperty("user_phone");
        String toAccount = ConfigUtils.getProperty("user_phone_2");
        int amount = 50;
        setAmount(amount);
        TransanctionModel tranModel = new TransanctionModel(fromAccount, toAccount, amount);
        setUserBalanceBeforeSendingMoney(Integer.parseInt(ConfigUtils.getProperty("user_balance_1")));

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                        .body(tranModel)
                        .when()
                        .post("/transaction/sendmoney")
                        .then()
                        .assertThat().statusCode(201).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        setMesssage(jsonPath.get("message"));
        setFee(jsonPath.get("fee"));
        setCurrentBalance(jsonPath.get("currentBalance"));

    }
}
