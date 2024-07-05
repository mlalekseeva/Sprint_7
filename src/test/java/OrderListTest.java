import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.API.OrderSteps;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {

    OrderSteps orderSteps = new OrderSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";

    }

    @Test
    @DisplayName("Проверка получения списка заказов")
    public void getOrderList() {

        Response response = orderSteps.sendGetRequestV1orders();
        checkStatusCodeAndOrders(response);

    }

    @Step("Check status code is correct and orders")
    public void checkStatusCodeAndOrders(Response response) {

        response.then().statusCode(200).and().assertThat().body("orders", notNullValue());

    }


}
