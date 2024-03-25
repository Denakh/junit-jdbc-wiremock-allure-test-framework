package org.github.denakh.testsystem.requester;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import org.github.denakh.util.EnvironmentHelper;
import org.openapitools.jackson.nullable.JsonNullableModule;

public class TestObjectApiRequester {

    private static final String REQUEST_URL =
            "https://" + EnvironmentHelper.getEnvDomain() + "/test-object";

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

}
