/*
 * Hitachi Confidential
 * All Rights Reserved. Copyright (C) 2023, Hitachi, Ltd.
 */

package org.github.denakh.testsystem.mock.stub;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import lombok.*;
import org.github.denakh.testsystem.mock.BaseStub;
import org.github.denakh.testsystem.model.api.TestObject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTestObjectStub extends BaseStub {

    private final static String URL = "/v1/devices/block-storages/%s/cloud-endpoint%s";
    private int STATUS_CODE = 201;

    private int priority = 1;

    private TestObject requestBody;
    private TestObject responseBody;
    private RequestMethod requestMethod;
    private String plusProxy = ".*";

    @Override
    @SneakyThrows
    public void register(WireMockServer wireMockServer) {
        MappingBuilder mappingBuilder = post(URL).atPriority(priority);
        if (requestBody != null) {
            mappingBuilder.withRequestBody(equalToJson(OBJECT_MAPPER.writeValueAsString(requestBody)));
        }
        mappingBuilder.willReturn(generateResponseDefinitionBuilder());
        wireMockServer.stubFor(mappingBuilder);
    }

    private ResponseDefinitionBuilder generateResponseDefinitionBuilder() {
        ResponseDefinitionBuilder responseDefinitionBuilder = aResponse()
                .withHeader(CONTENT_TYPE_HEADER.getKey(), CONTENT_TYPE_HEADER.getValue());
        responseDefinitionBuilder.withStatus(STATUS_CODE);
        if (responseBody != null) {
            responseDefinitionBuilder.withJsonBody(OBJECT_MAPPER.valueToTree(responseBody));
        }
        return responseDefinitionBuilder;
    }

}
