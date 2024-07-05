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

public class CourierCreationWithoutPasswordTest {

    private Courier courierCreate;
    CourierSteps courierSteps = new CourierSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierCreate = new Courier()
                .withLogin(randomString(10))
                .withFirstName(randomString(20));

    }

    @Test
    @DisplayName("Проверка создания курьера без пароля")
    public void createCourierWithoutPasswordError() {

        Response response = courierSteps.sendPostRequestV1courier(courierCreate);
        checkStatusCodeAndMessage(response);

    }

    @Step("Check status code is correct and message text")
    public void checkStatusCodeAndMessage(Response response) {
        response.then().statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
