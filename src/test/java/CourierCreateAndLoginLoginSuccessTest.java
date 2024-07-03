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
import static org.hamcrest.Matchers.notNullValue;

public class CourierCreateAndLoginLoginSuccessTest {

    private CourierCreateAndLogin courierCreateAndLogin;
    private CourierDelete courierDelete;
    private String id;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierCreateAndLogin = new CourierCreateAndLogin()
                .withLogin(randomString(10))
                .withPassword(randomString(12));

    }

    @Test
    @DisplayName("Проверка успешного логина курьера")
    public void courierLoginSuccess() {

        sendPostRequestV1courierCreateTestCourier();
        Response response = sendPostRequestV1courierLogin();
        id = response.path("id").toString();
        checkStatusCodeAndId(response);


    }

    @Step("Send POST request to /api/v1/courier")
    public void sendPostRequestV1courierCreateTestCourier() {
        given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(courierCreateAndLogin)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response sendPostRequestV1courierLogin() {
        Response response = given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(courierCreateAndLogin)
                .when()
                .post("/api/v1/courier/login");
        return response;
    }

    @Step("Check status code is correct and id value")
    public void checkStatusCodeAndId(Response response) {
        response.then().statusCode(200).and().assertThat().body("id", notNullValue());
    }

    @After
    public void tearDown() {

        courierDelete = new CourierDelete()
                .withId(id);

        given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(courierDelete)
                .when()
                .delete("/api/v1/courier/" + id)
                .then().statusCode(200);


    }


}
