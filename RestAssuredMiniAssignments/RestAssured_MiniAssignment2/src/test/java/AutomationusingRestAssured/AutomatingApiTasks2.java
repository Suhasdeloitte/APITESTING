package AutomationusingRestAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AutomatingApiTasks2 {
    RequestSpecification rs;
    ResponseSpecification res;

    @BeforeClass
    public void setup(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://reqres.in/api").
                addHeader("Content-Type","application/json");
        rs = RestAssured.with().spec(requestSpecBuilder.build());

        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON);
        res = specBuilder.build();
    }

    @Test
    public void test_put_call(){
        File jsonData = new File("C:\\Users\\mvsuhas\\Maven_RestAssured\\PutCall.json");
        given().spec(rs).
                body(jsonData).
                when().
                put("/users").
                then().spec(res).
                statusCode(200).body("name",equalTo("Arun")).body("job",equalTo("Manager"));
    }
}