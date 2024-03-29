package org.github.denakh;

import org.github.denakh.testsystem.db.RepositoryService;
import org.github.denakh.testsystem.db.dao.TestObjectDaoImpl;
import org.junit.jupiter.api.Test;

public class TestObjectTests {

    protected static final RepositoryService REPOSITORY_SERVICE = new RepositoryService();
    protected static final TestObjectDaoImpl TEST_OBJECT_DAO = REPOSITORY_SERVICE.getTestObjectDao();

    @Test
    public void postObjectFlowIsSuccessful() {

    }


}
