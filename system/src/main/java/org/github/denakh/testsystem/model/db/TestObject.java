package org.github.denakh.testsystem.model.db;

import lombok.Builder;
import lombok.Data;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "test_object")
public class TestObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_object_id", nullable = false)
    private long id;

    @Column(name = "int_param", nullable = false)
    private int intParam;

    @Column(name = "string_param")
    private String stringParam;

    public org.github.denakh.testsystem.model.api.TestObject getApiTestObject() {
        return org.github.denakh.testsystem.model.api.TestObject.builder()
                .intParam(this.intParam)
                .stringParam(JsonNullable.of(this.stringParam))
                .build();
    }

}
