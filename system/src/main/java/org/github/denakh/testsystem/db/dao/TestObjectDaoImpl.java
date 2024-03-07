package org.github.denakh.testsystem.db.dao;


import org.github.denakh.testsystem.db.EntityManagerUtil;
import org.github.denakh.testsystem.model.db.TestObject;

public class TestObjectDaoImpl extends EntityDaoImpl<TestObject> {

    public TestObjectDaoImpl(EntityManagerUtil entityManagerUtil) {
        super(entityManagerUtil, TestObject.class);
    }

}
