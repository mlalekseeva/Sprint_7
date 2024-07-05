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
import static org.hamcrest.Matchers.equalTo;

public class CourierCreationSuccessTest {

    private Courier courierCreate;
    private Courier courierDelete;


    CourierSteps courierSteps = new CourierSteps();
    private String id;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierCreate = new Courier()
                .withLogin(randomString(10))
                .withPassword(randomString(12))
                .withFirstName(randomString(20));


        courierDelete = new Courier()
                .withId(id);

    }

    @Test
    @DisplayName("Проверка успешного создания курьера")
    public void createCourierSuccess() {

        Response response = courierSteps.sendPostRequestV1courier(courierCreate);
        checkStatusCodeAndOk(response);

    }

    @Step("Check status code and ok parameter are correct")
    public void checkStatusCodeAndOk(Response response) {
        response.then().statusCode(201).and().assertThat().body("ok", equalTo(true));

    }


    @After
    public void tearDown() {

        Response responseLogin = courierSteps.sendPostRequestV1courierLogin(courierCreate);

        id = responseLogin.path("id").toString();

        courierSteps.sendDeleteRequestV1courier(courierDelete, id);

    }


}

