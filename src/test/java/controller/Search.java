package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import utils.ConfigUtils;

import static io.restassured.RestAssured.given;

public class Search {
    public String searchUserByInvalidPhone(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                .when()
                        .get("/user/search/phonenumber/111")
                .then()
                        .assertThat().statusCode(404).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        return jsonPath.get("message");
    }

    public String searchUserByValidPhone(){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        String phone = ConfigUtils.getProperty("user_phone");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                .when()
                        .get("/user/search/phonenumber/"+phone)
                .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath jsonPath = res.jsonPath();
        jsonPath.prettyPrint();
        return jsonPath.get("message");
    }
}
