import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.models.CourierCreateAndLogin;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.example.utils.Utils.randomString;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreateAndLoginLoginWithoutPasswordErrorTest {

    private CourierCreateAndLogin courierCreateAndLogin;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierCreateAndLogin = new CourierCreateAndLogin()
                .withLogin(randomString(10));

    }

    @Test
    @DisplayName("Проверка логина курьера без пароля")
    public void courierLoginWithoutPasswordError() {

        Response response = sendPostRequestV1courierLogin();
        checkStatusCodeAndMessage(response);

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

    @Step("Check status code is correct and message value")
    public void checkStatusCodeAndMessage(Response response) {

        response.then().statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для входа"));

    }


}
