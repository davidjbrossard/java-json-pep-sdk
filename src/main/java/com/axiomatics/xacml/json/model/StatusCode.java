package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Contains a major status code value and an optional sequence of minor status code
 */
@ApiModel("Contains a major status code value and an optional sequence of minor status code")
@Data
public class StatusCode {

    /**
     * Major status code value
     */
    @ApiModelProperty(
        value = "Major status code value"
    )
    @JsonProperty("Value")
    String value;

    /**
     * Minor status code.  This status code qualifies its parent status code.
     */
    @ApiModelProperty(
        value = "Minor status code.  This status code qualifies its parent status code."
    )
    @JsonProperty("StatusCode")
    StatusCode statusCode;

    public StatusCode() {
    }

    public StatusCode(String value) {
        this.value = value;
    }

    public StatusCode(String value, StatusCode statusCode) {
        this.value = value;
        this.statusCode = statusCode;
    }
}
