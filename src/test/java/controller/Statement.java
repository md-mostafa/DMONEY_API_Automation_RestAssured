package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.ConfigUtils;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class Statement {
    public String checkStatementByInvalidTransactionId(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String invalidTransanctionId ="invalidId";
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                .when()
                        .get("/transaction/search/"+invalidTransanctionId)
                .then()
                        .assertThat().statusCode(404).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        return jsonPath.get("message");
    }


    public String checkStatementByValidTransactionId(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String TransanctionId =ConfigUtils.getProperty("transId");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/transaction/search/"+TransanctionId)
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        return jsonPath.get("message");
    }

    public void checkCustomerStatementWithInvalidPhone(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String invalidPhone = "123";
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                .when()
                        .get("/transaction/statement/"+invalidPhone)
                .then()
                        .assertThat().statusCode(404).extract().response();
    }

}
