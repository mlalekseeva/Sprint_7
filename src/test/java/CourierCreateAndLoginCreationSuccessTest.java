import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.models.CourierCreateAndLogin;
import org.example.models.CourierDelete;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.example.utils.Utils.randomString;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreateAndLoginCreationSuccessTest {

    private CourierCreateAndLogin courierCreateAndLogin;
    private CourierDelete courierDelete;
    private String id;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierCreateAndLogin = new CourierCreateAndLogin()
                .withLogin(randomString(10))
                .withPassword(randomString(12))
                .withFirstName(randomString(20));

    }

    @Test
    @DisplayName("Проверка успешного создания курьера")
    public void createCourierSuccess() {

        Response response = sendPostRequestV1courier();
        checkStatusCodeAndOk(response);

    }

    @Step("Send POST request to /api/v1/courier")
    public Response sendPostRequestV1courier(){
        Response response = given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(courierCreateAndLogin)
                .when()
                .post("/api/v1/courier");

        return response;
    }

    @Step("Check status code and ok parameter are correct")
    public void checkStatusCodeAndOk(Response response) {
        response.then().statusCode(201).and().assertThat().body("ok", equalTo(true));

    }

    @After
    public void tearDown() {

        courierDelete = new CourierDelete()
                .withId(id);

        Response responseLogin = given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(courierCreateAndLogin)
                .when()
                .post("/api/v1/courier/login");

        id = responseLogin.path("id").toString();


        given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(courierDelete)
                .when()
                .delete("/api/v1/courier/" + id)
                .then().statusCode(200);


    }


}

