package org.example.API;

import io.restassured.response.Response;
import org.example.models.Order;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    private static final String ORDER_ENDPOINT = "/api/v1/orders";

    public Response sendPostRequestV1orders(Order order) {
        Response response = given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(order)
                .when()
                .post(ORDER_ENDPOINT);

        return response;

    }

    public Response sendGetRequestV1orders() {
        Response response = given()
                .header("Content-Type", "application/json; charset=utf-8")
                .get("/api/v1/orders");

        return response;

    }
}
