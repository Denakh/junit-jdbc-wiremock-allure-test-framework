package org.github.denakh.testsystem.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestObject {

    public static final int STRING_PARAM_MAX_LENGTH = 32;

    private int intParam;

    @JsonProperty("strParam")
    private JsonNullable<String> stringParam;

//    public org.github.denakh.testsystem.model.db.TestObject getDbTestObject() {
//        return org.github.denakh.testsystem.model.db.TestObject.builder()
//                .intParam(this.intParam)
//                .stringParam(this.stringParam == null ? null : this.stringParam.get())
//                .build();
//    }

}
