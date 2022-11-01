import com.google.gson.JsonObject;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Quiz2 {
    @Test
    public void right_user_name() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com/Account/v1/user";
        JsonObject param = new JsonObject();
        param.addProperty("userName", "btuquiz");
        param.addProperty("password", "Testautomation12345#");

        RequestSpecification request = given();
        request.header("Content-Type", "application/json")
                .body(param);
       given().spec(request).post(baseURI).then().assertThat().statusCode(201);

    }
    @Description("register with existing user ")
    @Test
    public void existing_user_name() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com/Account/v1/user";
        JsonObject param = new JsonObject();
        param.addProperty("userName", "btuquiz");
        param.addProperty("password", "Testautomation12345#");

        RequestSpecification request = given();
        request.header("Content-Type", "application/json")
                .body(param);
        JsonPath json_path=given().spec(request).post(baseURI).then().log().body().extract().jsonPath();
        String message=json_path.getString("message");
        Assert.assertEquals(message, "User exists!");



    }

    @Test
    public void wrong_user_name() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com/Account/v1/user";
        JsonObject param = new JsonObject();
        param.addProperty("userName", "btuquiz");
        param.addProperty("password", "testautomation1234");

        RequestSpecification request = given();
        request.header("Content-Type", "application/json")
                .body(param);
        JsonPath json_path=given().spec(request).post(baseURI).then().log().body().extract().jsonPath();
        String message=json_path.getString("code");
        Assert.assertEquals(message, "1300");




    }




}
