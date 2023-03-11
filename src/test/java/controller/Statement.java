package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import utils.ConfigUtils;

import static io.restassured.RestAssured.given;

@Getter
@Setter
public class Statement {
    private String message;

    public void checkStatementByInvalidTransactionId(){
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
        setMessage(jsonPath.get("message"));
    }


    public void checkStatementByValidTransactionId(){
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
        setMessage(jsonPath.get("message"));
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

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        setMessage(jsonPath.get("message"));
    }

    public void checkCustomerStatementWithValidPhone(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String validPhone = ConfigUtils.getProperty("user_phone");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/transaction/statement/"+validPhone)
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        setMessage(jsonPath.get("message"));
    }

}
