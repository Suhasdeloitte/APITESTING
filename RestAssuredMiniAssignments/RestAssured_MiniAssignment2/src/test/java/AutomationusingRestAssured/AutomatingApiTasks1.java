package AutomationusingRestAssured;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class AutomatingApiTasks1 {
    RequestSpecification rs;
    ResponseSpecification res;


        @BeforeClass
                public void setup(){
            RequestSpecBuilder rsp=new RequestSpecBuilder();
            rsp.setBaseUri("https://jsonplaceholder.typicode.com").addHeader("Content-Type","application/json");
            rs= RestAssured
                    .with().spec(rsp.build());
            res=RestAssured.expect().contentType(ContentType.JSON);

        }
        @Test
                public void test_get_call() {
            given().spec(rs).when().get("/posts").then().spec(res).statusCode(200).body("userId[39]",equalTo(4)).body("title[39]",equalTo("enim quo cumque"));

        }


        }














