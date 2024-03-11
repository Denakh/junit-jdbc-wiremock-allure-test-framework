package org.github.denakh.testsystem.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestObject {

    private int intParam;

    @JsonProperty("strParam")
    private String stringParam;

    public org.github.denakh.testsystem.model.db.TestObject getDbTestObject() {
        return org.github.denakh.testsystem.model.db.TestObject.builder()
                .intParam(this.intParam)
                .stringParam(this.stringParam)
                .build();
    }

}
