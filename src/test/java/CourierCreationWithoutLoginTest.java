import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.API.CourierSteps;
import org.example.models.Courier;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.example.utils.Utils.randomString;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreationWithoutLoginTest {

    private Courier courierCreate;
    CourierSteps courierSteps = new CourierSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierCreate = new Courier()
                .withPassword(randomString(12))
                .withFirstName(randomString(20));

    }

    @Test
    @DisplayName("Проверка создания курьера без логина")
    public void createCourierWithoutLoginError() {

        Response response = courierSteps.sendPostRequestV1courier(courierCreate);
        checkStatusCodeAndMessage(response);

    }

    @Step("Check status code is correct and message text")
    public void checkStatusCodeAndMessage(Response response) {
        response.then().statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}
