package org.github.denakh.testsystem.requester;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.github.denakh.testsystem.model.api.TestObject;
import org.github.denakh.util.EnvironmentHelper;
import org.openapitools.jackson.nullable.JsonNullableModule;

public class TestObjectApiRequester {

    private static final String REQUEST_URL =
            "http://" + EnvironmentHelper.getEnvDomain() + "/test-object-endpoint";

    private final RequestSpecification requestSpecification;

    static {
        Jackson2ObjectMapperFactory defaultMapperFactory = RestAssuredConfig.config().getObjectMapperConfig()
                .jackson2ObjectMapperFactory();
        RestAssured.config = RestAssuredConfig.newConfig().objectMapperConfig(
                new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (type, string) -> {
                            ObjectMapper mapper = defaultMapperFactory.create(type, string);
                            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                            mapper.registerModule(new JsonNullableModule());
                            return mapper;
                        }));
    }

    public TestObjectApiRequester() {
        PreemptiveOAuth2HeaderScheme auth2HeaderScheme = new PreemptiveOAuth2HeaderScheme();
        // auth2HeaderScheme.setAccessToken(EnvironmentHelper.getToken());
        this.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(REQUEST_URL)
                .setAuth(auth2HeaderScheme)
                .setContentType(ContentType.JSON)
                .build();
    }

    public TestObject postTestObject(TestObject body) {
        Response response = RestAssured
                .given(this.requestSpecification)
                .body(body)
                .log().all()
                .post()
                .then().statusCode(201)
                .extract().response();
        return response.as(TestObject.class);
    }

}
