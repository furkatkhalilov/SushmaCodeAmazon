package API_TEST;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DummyAPI {
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;
    static String id;

    @BeforeClass
    public void setup(){


        baseURI = "http://dummy.restapiexample.com/api/v1/";

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
    }

    @Test(priority = 0)
    public void getEmployeeList(){
        given()
                .spec(requestSpecification)
                .when()
                .get("employees/")
                .then()
                .log().body()
                .spec(responseSpecification);
    }

    @Test(priority = 1)
    public void getEmployeeByID(){
        id = randomID();
        given()
                .spec(requestSpecification)
                .when()
                .get("employee/"+id)
                .then()
                .log().body()
                .spec(responseSpecification);
    }

    @Test(priority = 2)
    public void deleteEmployeeByID(){
        given()
                .spec(requestSpecification)
                .when()
                .delete("delete/"+id)
                .then()
                .log().body()
                .spec(responseSpecification)
                .body("data",equalTo(id))
                .body("message", equalTo("Successfully! Record has been deleted"));
    }



    public String randomID(){
        return String.valueOf((int)(1 + (Math.random() *10)));
    }
}

