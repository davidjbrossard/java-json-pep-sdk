package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * The status of the authorization decision result.
 * <p>
 * Indicates whether errors occurred during evaluation of the decision request, and optionally, information about those errors.  If the
 * {@link Response} element contains {@link Result} elements whose {@link Status} members are all identical, and the {@link Response} object is
 * contained in a protocol wrapper that can convey status information, then the common status information MAY be placed in the protocol wrapper and
 * this {@link Status} element MAY be omitted from all {@link Result} elements.
 */
@ApiModel("The status of the authorization decision result")
@Data
public class Status {

    /**
     * The status code
     */
    @ApiModelProperty(
        value = "The status code"
    )
    @JsonProperty("StatusCode")
    StatusCode statusCode;

    /**
     * A free-form description of the status code
     */
    @ApiModelProperty(
        value = "A free-form description of the status code"
    )
    @JsonProperty("StatusMessage")
    String statusMessage;

    /**
     * Additional status information
     */
    @ApiModelProperty(
        value = "Additional status information"
    )
    @JsonProperty("StatusDetail")
    List<JSONPObject> statusDetail;

}
