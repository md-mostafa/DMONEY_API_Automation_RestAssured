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
    private String msg;

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
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(tranModel)
                .when()
                        .post("/transaction/sendmoney")
                .then()
                        .assertThat().statusCode(404).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        setMsg(jsonPath.get("message"));
    }
}
