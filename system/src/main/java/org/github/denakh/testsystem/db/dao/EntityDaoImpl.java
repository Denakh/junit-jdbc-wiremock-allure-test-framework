package org.github.denakh.testsystem.db.dao;

import java.util.List;
import org.github.denakh.testsystem.db.EntityManagerUtil;

public abstract class EntityDaoImpl<ENTITY> {

    private final EntityManagerUtil emUtil;
    private final Class<ENTITY> entityClass;
    private final String entityClassName;

    public EntityDaoImpl(EntityManagerUtil entityManagerUtil, Class<ENTITY> entityClass) {
        this.emUtil = entityManagerUtil;
        this.entityClass = entityClass;
        this.entityClassName = entityClass.getSimpleName();
    }

    // Updates existing DB object by id (primary key)
    public ENTITY update(ENTITY entity) {
        return emUtil.performTransactionAndGetResult(entityManager -> entityManager.merge(entity));
    }

    // Saves the entity to the DB and makes it managed
    public ENTITY save(ENTITY entity) {
        emUtil.performTransaction(entityManager -> entityManager.persist(entity));
        return entity;
    }

    public ENTITY findById(long id) {
        return emUtil.performTransactionAndGetResult(entityManager -> entityManager.find(entityClass, id));
    }

    public List<ENTITY> findAll() {
        return emUtil.performTransactionAndGetResult(entityManager -> entityManager
                .createQuery("SELECT e FROM " + entityClassName + " e").getResultList());
    }

    public void remove(ENTITY entity) {
        emUtil.performTransaction(entityManager -> {
                                      ENTITY managedEntity = entityManager.merge(entity);
                                      entityManager.remove(managedEntity);
                                  }
        );
    }

    public void removeAll() {
        emUtil.performTransaction(entityManager ->
                                          entityManager.createQuery("DELETE FROM " + entityClassName).executeUpdate());
    }
}
