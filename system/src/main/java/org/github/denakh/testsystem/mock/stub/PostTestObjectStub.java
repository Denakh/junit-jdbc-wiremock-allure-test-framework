/*
 * Hitachi Confidential
 * All Rights Reserved. Copyright (C) 2023, Hitachi, Ltd.
 */

package org.github.denakh.testsystem.mock.stub;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import lombok.*;
import org.github.denakh.testsystem.mock.BaseStub;
import org.github.denakh.testsystem.model.api.TestObject;
import org.github.denakh.util.NumberUtility;
import org.github.denakh.util.StringUtility;
import org.openapitools.jackson.nullable.JsonNullable;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTestObjectStub extends BaseStub {

    private final static String URL = "/test-object-endpoint";
    private int STATUS_CODE = 201;

    private int priority = 1;
    private TestObject requestBody;

    @Override
    @SneakyThrows
    public void register(WireMockServer wireMockServer) {
        MappingBuilder mappingBuilder = post(URL).atPriority(priority)
                .willReturn(generateResponseDefinitionBuilder());
        wireMockServer.stubFor(mappingBuilder);
    }

    private ResponseDefinitionBuilder generateResponseDefinitionBuilder() {
        return aResponse()
                .withHeader(CONTENT_TYPE_HEADER.getKey(), CONTENT_TYPE_HEADER.getValue())
                .withStatus(STATUS_CODE)
                .withJsonBody(OBJECT_MAPPER.valueToTree(getResponseBody()));
    }

    private TestObject getResponseBody() {
        return requestBody.getStringParam() == null ?
                TestObject
                        .builder()
                        .intParam(NumberUtility.getIntInRange(Integer.MAX_VALUE))
                        .build()
                : TestObject
                .builder()
                .intParam(requestBody.getIntParam())
                .stringParam(JsonNullable.of(StringUtility.generateStringOfLength(TestObject.STRING_PARAM_MAX_LENGTH)))
                .build();
    }

}
