import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.models.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreationSuccessParamsTests {

    private final List<String> color;
    private Order order;

    public OrderCreationSuccessParamsTests(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[] lionTestData() {
        return new Object[][] {
                {Arrays.asList("BLACK")},
                {Arrays.asList("GREY")},
                {Arrays.asList("BLACK", "GREY")},

        };
    }



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
                .withComment("Saske, come back to Konoha")
                .withColor(color);

    }

    @Test
    @DisplayName("Проверка создания заказа со значением color BLACK, GREY, BLACK&GREY")
    public void createOrderAnyColorSuccess() {

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
