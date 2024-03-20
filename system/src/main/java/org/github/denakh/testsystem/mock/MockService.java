package org.github.denakh.testsystem.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class MockService {

    private final WireMockServer wireMockServer;

    MockService() {
        wireMockServer = new WireMockServer();
    }

    MockService(int port) {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().httpsPort(port));
    }

    public void start() {
        wireMockServer.start();
        stubFor(
                WireMock.get(WireMock.urlEqualTo("/some/thing"))
                        .willReturn(
                                aResponse().withHeader("Content-Type", "text/plain").withBody("Hello world!")));
    }

    public void stop() {
        wireMockServer.stop();
    }

    public void registerStub(BaseStub baseStub) {
        baseStub.register(wireMockServer);
    }

}
