package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import utils.ConfigUtils;

import static io.restassured.RestAssured.given;

public class Login {
    public String loginWithValidCreds(String email, String pass){
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        UserModel loginModel = new UserModel(email, pass);

        Response res =
                given()
                    .contentType("application/json")
                    .body(loginModel)
                .when()
                    .post("/user/login")
                .then()
                    .assertThat().statusCode(200).extract().response();

        JsonPath jsonPath = res.jsonPath();
        String token = jsonPath.get("token");

        ConfigUtils.setProperty("token", token);
        return jsonPath.get("message");

    }

    public String loginWithInvalidEmail(String email, String pass) {
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");
        UserModel loginModel = new UserModel(email, pass);

        Response res =
                given()
                        .contentType("application/json")
                        .body(loginModel)
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(404).extract().response();

        JsonPath jsonPath = res.jsonPath();
        return jsonPath.get("message");
    }
}
