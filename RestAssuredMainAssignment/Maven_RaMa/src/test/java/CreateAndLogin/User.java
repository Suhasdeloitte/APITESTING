package CreateAndLogin;

import CreateAndLogin.data;
import CreateAndLogin.excelData;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class User {

    @Test(priority = 1)
    public void userRegister() throws IOException {

        for (int i = 1; i <= 5; i++) {

            excelData ed = new excelData();
            String nam = ed.getString(i, 0);
            String emai = ed.getString(i, 1);
            String pass = ed.getString(i, 2);
            int age = ed.getAge(i, 3);
            data dt = new data(nam, emai, pass, age);
            given().
                    body(dt).
                    contentType(ContentType.JSON).
                    when().
                    post("https://api-nodejs-todolist.herokuapp.com/user/register").
                    then().
                    log().body().

                    statusCode(HttpStatus.SC_CREATED);
            //System.out.println(dt.getAge());
            //contentType("application/json");
        }
    }

    @Test(priority = 2)
    public void userLogin() throws IOException {

        excelData ed = new excelData();
        String emai = ed.getString(1, 1);
        String pass = ed.getString(1, 2);
        data dt = new data(emai, pass);
        Response response = given().
                body(dt).
                contentType(ContentType.JSON).
                when().
                post("https://api-nodejs-todolist.herokuapp.com/user/login").
                then().
                log().body().
                statusCode(HttpStatus.SC_OK).extract().response();
        JSONObject jsonObject = new JSONObject(response.asString());
        //System.out.println(jsonObject.getJSONObject("user").get("email"));
        Object obj = jsonObject.getJSONObject("user").get("email");
        assertThat(obj, is(emai));
        Object ObjToken = jsonObject.get("token");
        excelData excelData = new excelData();
        excelData.writeToken(ObjToken);
        System.out.println(ObjToken);
    }

}