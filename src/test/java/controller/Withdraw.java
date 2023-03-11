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
public class Withdraw {

    public String withdrawMoneyFromInvalidAgentNumber(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String fromAccount = ConfigUtils.getProperty("user_phone");
        String toAccount = "1231";
        double amount = 50;
        TransanctionModel tranModel = new TransanctionModel(fromAccount, toAccount, amount);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(tranModel)
                .when()
                        .post("/transaction/withdraw")
                .then()
                        .assertThat().statusCode(404).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        return jsonPath.get("message");
    }


    public String withdrawMoneyFromAnotherCustomer(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String fromAccount = ConfigUtils.getProperty("user_phone");
        String toAccount = ConfigUtils.getProperty("user_phone_2");
        double amount = 50;
        TransanctionModel tranModel = new TransanctionModel(fromAccount, toAccount, amount);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(tranModel)
                .when()
                        .post("/transaction/withdraw")
                .then()
                        .assertThat().statusCode(208).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        return jsonPath.get("message");
    }

    private int currentBalance;
    private int fee;
    private String message;
    private int balanceBeforeWithdraw;
    private int withdrawAmount;
    public void withdrawMoneyFromAgent(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String fromAccount = ConfigUtils.getProperty("user_phone");
        String toAccount = ConfigUtils.getProperty("agent_phone");
        int amount = 20;
        TransanctionModel tranModel = new TransanctionModel(fromAccount, toAccount, amount);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(tranModel)
                .when()
                        .post("/transaction/withdraw")
                .then()
                        .assertThat().statusCode(201).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        setFee(jsonPath.get("fee"));
        setCurrentBalance(jsonPath.get("currentBalance"));
        setMessage(jsonPath.get("message"));
        setBalanceBeforeWithdraw(Integer.parseInt(ConfigUtils.getProperty("user_balance_1")));
        setWithdrawAmount(amount);

        ConfigUtils.setProperty("user_balance_1", jsonPath.get("currentBalance")+"");

    }
}
