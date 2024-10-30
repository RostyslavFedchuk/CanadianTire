package org.canadian.tire.api;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jakarta.annotation.PostConstruct;
import org.canadian.tire.spring.PropertyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.canadian.tire.util.JsonUtil.getObjectAsJsonString;

@Component
public class RestClient {

    @Autowired
    private PropertyManager propertyManager;
    private String baseUri;

    @PostConstruct
    public void setUpBaseUrl() {
        baseUri = propertyManager.getBaseApiUrl();
    }

    public Response get(String uri) {
        return getRequestSpecification()
                .when().get(uri)
                .then().log().all()
                .extract().response();
    }

    public Response getWithAuth(String uri) {
        return getAuthRequestSpecification()
                .when().get(uri)
                .then().log().all()
                .extract().response();
    }

    public Response postWthAuth(String uri, Object body) {
        String jsonBody = getObjectAsJsonString(body);
        return getAuthRequestSpecification()
                .body(jsonBody)
                .when().post(uri)
                .then().log().all()
                .extract().response();
    }

    public Response putWithAuth(String uri, Object body) {
        String jsonBody = getObjectAsJsonString(body);
        return getAuthRequestSpecification()
                .body(jsonBody)
                .when().put(uri)
                .then().log().all()
                .extract().response();
    }

    public Response deleteWithAuth(String uri) {
        return getAuthRequestSpecification()
                .when().delete(uri)
                .then().log().all()
                .extract().response();
    }


    private RequestSpecification getRequestSpecification() {
        config = new RestAssuredConfig().encoderConfig(new EncoderConfig().defaultContentCharset(UTF_8));
        return given()
                .contentType(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(baseUri)
                .log().all();
    }

    private RequestSpecification getAuthRequestSpecification() {
        return getRequestSpecification()
                .header("Authorization", "Bearer " + propertyManager.getApiAccessToken());
    }
}

