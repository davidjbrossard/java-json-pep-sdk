package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Encapsulates the authorization decision produced by the PDP
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@ApiModel("The Response Body")
public class Response {

    @JsonProperty("Decision")
    PDPDecision decision;

    @JsonProperty("Status")
    PDPResponseStatus status;

    public static class PDPResponseStatus {
        @JsonProperty("StatusCode")
        PDPStatusCodeWrapper statusCode;
    }

    public static class PDPStatusCodeWrapper {
        @JsonProperty("Value")
        String value;
    }
}
