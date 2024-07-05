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

public class CourierLoginWithoutPasswordErrorTest {

    private Courier courierLogin;
    CourierSteps courierSteps = new CourierSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierLogin = new Courier()
                .withLogin(randomString(10));

    }

    @Test
    @DisplayName("Проверка логина курьера без пароля")
    public void courierLoginWithoutPasswordError() {

        Response response = courierSteps.sendPostRequestV1courierLogin(courierLogin);
        checkStatusCodeAndMessage(response);

    }


    @Step("Check status code is correct and message value")
    public void checkStatusCodeAndMessage(Response response) {

        response.then().statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для входа"));

    }


}
