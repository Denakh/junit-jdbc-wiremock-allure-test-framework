/*
 * Hitachi Confidential
 * All Rights Reserved. Copyright (C) 2022, Hitachi, Ltd.
 */

package org.github.denakh.testsystem.mock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.commons.lang3.tuple.Pair;
import org.openapitools.jackson.nullable.JsonNullableModule;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseStub {

    protected static final Pair<String, String> CONTENT_TYPE_HEADER = Pair.of("Content-Type", "application/json");
    protected static final ObjectMapper OBJECT_MAPPER;
    private static final String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd'T'hh:mm:ss'Z'";

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(OffsetDateTime.class, new JsonSerializer<>() {
            @Override
            public void serialize(OffsetDateTime offsetDateTime, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString(DateTimeFormatter.ofPattern(ISO_DATE_TIME_FORMAT)
                        .format(offsetDateTime));
            }
        });
        OBJECT_MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        OBJECT_MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.registerModule(new JsonNullableModule());
        OBJECT_MAPPER.registerModule(simpleModule);
    }

    public abstract void register(WireMockServer wireMockServer);

}
