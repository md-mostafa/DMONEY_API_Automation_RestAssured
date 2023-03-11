package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.TransanctionModel;
import utils.ConfigUtils;

import static io.restassured.RestAssured.given;

public class Balance {
    public String checkCustomerBalanceWithInvalidPhone(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String invalidPhone = "123";
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                .when()
                        .get("/transaction/balance/"+invalidPhone)
                .then()
                        .assertThat().statusCode(404).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        return jsonPath.get("message");
    }


    public String checkCustomerBalanceWithValidCreds(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String customerPhone = ConfigUtils.getProperty("user_phone");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/transaction/balance/"+customerPhone)
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath jsonPath = res.jsonPath();
        int balance_current = jsonPath.get("balance");
        ConfigUtils.setProperty("user_balance_1", balance_current+"");

        jsonPath.prettyPrint();
        return jsonPath.get("message");
    }

}
