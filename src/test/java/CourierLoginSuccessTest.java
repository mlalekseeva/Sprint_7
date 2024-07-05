import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.API.CourierSteps;
import org.example.models.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.example.utils.Utils.randomString;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginSuccessTest {

    private Courier courierLogin;
    private Courier courierDelete;
    CourierSteps courierSteps = new CourierSteps();
    private String id;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierLogin = new Courier()
                .withLogin(randomString(10))
                .withPassword(randomString(12));

        courierDelete = new Courier()
                .withId(id);

    }

    @Test
    @DisplayName("Проверка успешного логина курьера")
    public void courierLoginSuccess() {

        courierSteps.sendPostRequestV1courierCreateTestCourier(courierLogin);
        Response response = courierSteps.sendPostRequestV1courierLogin(courierLogin);
        id = response.path("id").toString();
        checkStatusCodeAndId(response);


    }

    @Step("Check status code is correct and id value")
    public void checkStatusCodeAndId(Response response) {
        response.then().statusCode(200).and().assertThat().body("id", notNullValue());
    }

    @After
    public void tearDown() {

        courierSteps.sendDeleteRequestV1courier(courierDelete, id);

    }


}
