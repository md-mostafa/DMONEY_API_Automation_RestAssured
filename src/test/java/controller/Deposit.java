package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.TransanctionModel;
import utils.ConfigUtils;

import static io.restassured.RestAssured.given;

public class Deposit {
    public String depositToAgentWithInvalidAgentNum(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        TransanctionModel tranModel = new TransanctionModel("SYSTEM", "01234569681", 1000);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                .body(tranModel)
                        .when()
                        .post("/transaction/deposit")
                .then()
                        .assertThat().statusCode(404).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        return jsonPath.get("message");
    }

    public String depositToAgentWithValidAgentNum(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String fromAccount = "SYSTEM";
        double amount = 5000;
        String toAccount = ConfigUtils.getProperty("agent_phone");
        TransanctionModel tranModel = new TransanctionModel(fromAccount, toAccount, amount);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                        .body(tranModel)
                .when()
                        .post("/transaction/deposit")
                .then()
                        .assertThat().statusCode(201).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        return jsonPath.get("message");
    }


    public String depositToCustomerFromCustomer(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String fromAccount = ConfigUtils.getProperty("user_phone");
        String toAccount = ConfigUtils.getProperty("user_phone_2");
        double amount = 1000;
        TransanctionModel tranModel = new TransanctionModel(fromAccount, toAccount, amount);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                        .body(tranModel)
                        .when()
                        .post("/transaction/deposit")
                        .then()
                        .assertThat().statusCode(208).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        return jsonPath.get("message");
    }

    public String depositToCustomerFromValidAgent(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String fromAccount = ConfigUtils.getProperty("agent_phone");
        String toAccount = ConfigUtils.getProperty("user_phone");
        double amount = 200;
        TransanctionModel tranModel = new TransanctionModel(fromAccount, toAccount, amount);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                        .body(tranModel)
                .when()
                        .post("/transaction/deposit")
                .then()
                        .assertThat().statusCode(201).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        String transactionId = jsonPath.get("trnxId");
        ConfigUtils.setProperty("transId", transactionId);
        return jsonPath.get("message");
    }
}
