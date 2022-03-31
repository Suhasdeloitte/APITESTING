package AutomationusingRestAssured;


import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

public class AutomatingApiTasks1 {


        @Test
                public void test_get_call(){

            given().
                    baseUri("https://jsonplaceholder.typicode.com/posts").header("Content-Type","application/json").
                    when().
                    get("https://jsonplaceholder.typicode.com/posts").
                    then().log().all().assertThat().statusCode(200).body("userId[39]",equalTo(4)).body("title[39]",equalTo("enim quo cumque"));
        }


        }














