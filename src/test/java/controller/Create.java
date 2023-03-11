package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import utils.ConfigUtils;
import utils.RandomUtils;

import static io.restassured.RestAssured.given;

public class Create {
    public String createUserWithValidCreds(String role){
        RandomUtils randomUser = new RandomUtils();
        String name = randomUser.getFullName();
        String email = randomUser.getEmail();
        String phone =randomUser.generatePhoneNumber();
        String pass = "1234";
        String nid = "100200302";

        UserModel regModel = new UserModel(name, email, pass, phone, nid, role);
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                        .body(regModel)
                .when()
                        .post("/user/create")
                .then()
                        .assertThat().statusCode(201).extract().response();


        JsonPath jsonRes = res.jsonPath();
        jsonRes.prettyPrint();

        if(role == "Customer"){
            ConfigUtils.setProperty("user_id", jsonRes.get("user.id").toString());
            ConfigUtils.setProperty("user_name", jsonRes.get("user.name"));
            ConfigUtils.setProperty("user_email", jsonRes.get("user.email"));
            ConfigUtils.setProperty("user_phone", jsonRes.get("user.phone_number"));
        }else if(role=="Agent"){
            ConfigUtils.setProperty("agent_id", jsonRes.get("user.id").toString());
            ConfigUtils.setProperty("agent_name", jsonRes.get("user.name"));
            ConfigUtils.setProperty("agent_email", jsonRes.get("user.email"));
            ConfigUtils.setProperty("agent_phone", jsonRes.get("user.phone_number"));
        }

        return jsonRes.get("message");
    }
    public String createUserWithExistingEmail(String role){
        RandomUtils randomUser = new RandomUtils();
        String name = randomUser.getFullName();
        String email = ConfigUtils.getProperty("user_email");
        String phone =randomUser.generatePhoneNumber();
        String pass = "1234";
        String nid = "100200302";

        UserModel regModel = new UserModel(name, email, pass, phone, nid, role);
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                        .body(regModel)
                        .when()
                        .post("/user/create")
                        .then()
                        .assertThat().statusCode(208).extract().response();


        JsonPath jsonRes = res.jsonPath();
        return jsonRes.get("message");
    }
    public String createUserWithExistingPhone(String role){
        RandomUtils randomUser = new RandomUtils();
        String name = randomUser.getFullName();
        String email = randomUser.getEmail();
        String phone = ConfigUtils.getProperty("user_phone");
        String pass = "1234";
        String nid = "100200302";

        UserModel regModel = new UserModel(name, email, pass, phone, nid, role);
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                        .body(regModel)
                        .when()
                        .post("/user/create")
                        .then()
                        .assertThat().statusCode(208).extract().response();


        JsonPath jsonRes = res.jsonPath();
        jsonRes.prettyPrint();
        return jsonRes.get("message");
    }



    public String createSecondUserWithValidCreds(String role){
        RandomUtils randomUser = new RandomUtils();
        String name = randomUser.getFullName();
        String email = randomUser.getEmail();
        String phone =randomUser.generatePhoneNumber();
        String pass = "1234";
        String nid = "100200302";

        UserModel regModel = new UserModel(name, email, pass, phone, nid, role);
        RestAssured.baseURI = ConfigUtils.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", ConfigUtils.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", System.getProperty("secretkey"))
                        .body(regModel)
                        .when()
                        .post("/user/create")
                        .then()
                        .assertThat().statusCode(201).extract().response();


        JsonPath jsonRes = res.jsonPath();
        jsonRes.prettyPrint();

        if(role == "Customer"){
            ConfigUtils.setProperty("user_id_2", jsonRes.get("user.id").toString());
            ConfigUtils.setProperty("user_name_2", jsonRes.get("user.name"));
            ConfigUtils.setProperty("user_email_2", jsonRes.get("user.email"));
            ConfigUtils.setProperty("user_phone_2", jsonRes.get("user.phone_number"));
        }else if(role=="Agent"){
            ConfigUtils.setProperty("agent_id_2", jsonRes.get("user.id").toString());
            ConfigUtils.setProperty("agent_name_2", jsonRes.get("user.name"));
            ConfigUtils.setProperty("agent_email_2", jsonRes.get("user.email"));
            ConfigUtils.setProperty("agent_phone_2", jsonRes.get("user.phone_number"));
        }

        return jsonRes.get("message");
    }
}
