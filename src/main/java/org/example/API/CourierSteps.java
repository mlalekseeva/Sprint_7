package org.example.API;

import io.restassured.response.Response;
import org.example.models.Courier;

import static io.restassured.RestAssured.given;

public class CourierSteps {

    private static final String CREATE_ENDPOINT = "/api/v1/courier";
    private static final String LOGIN_ENDPOINT = "/api/v1/courier/login";
    private static final String DELETE_ENDPOINT = "/api/v1/courier/";

    public void sendPostRequestV1courierCreateTestCourier(Courier courierCreate) {
        given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(courierCreate)
                .when()
                .post(CREATE_ENDPOINT);
    }

    public Response sendPostRequestV1courierLogin(Courier courierLogin){
        Response response = given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(courierLogin)
                .when()
                .post(LOGIN_ENDPOINT);
        return response;
    }

    public Response sendPostRequestV1courier(Courier courierCreate) {
        Response response = given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(courierCreate)
                .when()
                .post(CREATE_ENDPOINT);

        return response;
    }

    public void sendDeleteRequestV1courier(Courier courierDelete, String id) {
        given()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(courierDelete)
                .when()
                .delete(DELETE_ENDPOINT + id)
                .then().statusCode(200);
    }
}
