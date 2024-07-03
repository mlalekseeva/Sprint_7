import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.models.Order;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderCreationSuccessTest {

    private Order order;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        order = new Order()
                .withFirstName("Naruto")
                .withLastName("Uchiha")
                .withAddress("Konoha, 142 apt.")
                .withPhone("+7 800 355 35 35")
                .withRentTime(5)
                .withDeliveryDate("2020-06-06")
                .withComment("Saske, come back to Konoha");

    }

    @Test
    @DisplayName("Создание заказа без color")
    public void createOrderNoColorSuccess() {

        Response response = sendPostRequestV1orders();
        checkStatusCodeAndTrack(response);

    }

    @Step("Send POST request to /api/v1/orders")
    public Response sendPostRequestV1orders() {
        Response response = given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(order)
                .when()
                .post("/api/v1/orders");

        return response;

    }

    @Step("Check status code is correct and track")
    public void checkStatusCodeAndTrack(Response response) {

        response.then().statusCode(201).and().assertThat().body("track", notNullValue());

    }


}
