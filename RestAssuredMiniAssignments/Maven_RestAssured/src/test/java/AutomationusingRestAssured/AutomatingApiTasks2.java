package AutomationusingRestAssured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AutomatingApiTasks2 {

    @Test
    public void test_put_call(){
        given().
                baseUri("https://reqres.in/api/users").
                body("{\n" +
                        "\"name\": \"Arun\",\n" +
                        "\t\t\"job\": \"Manager\"\n" +
                        "}").
                header("Content-Type","application/json").
                when().
                put("/users").
                then().log().all().
                statusCode(200).body("name",equalTo("Arun")).body("job",equalTo("Manager"));
    }

}

