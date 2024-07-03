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

public class CourierCreateAndLoginCreationWithoutLoginTest {

    private CourierCreateAndLogin courierCreateAndLogin;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierCreateAndLogin = new CourierCreateAndLogin()
                .withPassword(randomString(12))
                .withFirstName(randomString(20));

    }

    @Test
    @DisplayName("Проверка создания курьера без логина")
    public void createCourierWithoutLoginError() {

        Response response = sendPostRequestV1courierWithoutLogin();
        checkStatusCodeAndMessage(response);

    }

    @Step("Send POST request to /api/v1/courier")
    public Response sendPostRequestV1courierWithoutLogin() {
        Response response = given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(courierCreateAndLogin)
                .when()
                .post("/api/v1/courier");

        return response;
    }

    @Step("Check status code is correct and message text")
    public void checkStatusCodeAndMessage(Response response) {
        response.then().statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}
