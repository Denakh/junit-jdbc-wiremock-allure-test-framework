package org.github.denakh;

import org.assertj.core.api.Assertions;
import org.github.denakh.testsystem.db.RepositoryService;
import org.github.denakh.testsystem.db.dao.TestObjectDaoImpl;
import org.github.denakh.testsystem.mock.BaseStub;
import org.github.denakh.testsystem.mock.MockService;
import org.github.denakh.testsystem.mock.stub.PostTestObjectStub;
import org.github.denakh.testsystem.model.api.TestObject;
import org.github.denakh.testsystem.requester.TestObjectApiRequester;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestObjectTests {

    protected static final RepositoryService REPOSITORY_SERVICE = new RepositoryService();
    protected static final TestObjectDaoImpl TEST_OBJECT_DAO = REPOSITORY_SERVICE.getTestObjectDao();
    protected static final MockService MOCK_SERVICE = new MockService(7777);

    @BeforeAll
    public static void setupAll() {
        MOCK_SERVICE.start();
    }

    @Test
    public void postObjectFlowIsSuccessful() {

        TestObject requestBody = TestObject.builder().intParam(111).build();
        BaseStub stub = PostTestObjectStub.builder()
                .requestBody(requestBody)
                .build();
        MOCK_SERVICE.registerStub(stub);

        TestObjectApiRequester requester = new TestObjectApiRequester();
        TestObject responseBody = requester.postTestObject(requestBody);
        Assertions.assertThat(responseBody.getStringParam()).isNull();
        Assertions.assertThat(responseBody.getIntParam()).isLessThanOrEqualTo(Integer.MAX_VALUE);

    }

    @AfterAll
    public static void clearAll() {
        MOCK_SERVICE.stop();
    }

}
