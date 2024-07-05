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

public class CourierLoginWithNonexistentLoginAndPasswordErrorTest {

    private Courier courierLogin;
    CourierSteps courierSteps = new CourierSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierLogin = new Courier()
                .withLogin(randomString(10))
                .withPassword(randomString(12));

    }

    @Test
    @DisplayName("Проверка логина курьера с несуществующими логином и паролем")
    public void courierLoginWithNonexistentLoginAndPasswordError() {

        Response response = courierSteps.sendPostRequestV1courierLogin(courierLogin);
        checkStatusCodeAndMessage(response);

    }

    @Step("Check status code is correct and message value")
    public void checkStatusCodeAndMessage(Response response) {
        response.then().statusCode(404).and().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }


}
