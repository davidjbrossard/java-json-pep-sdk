package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Encapsulates the authorization decision produced by the PDP
 */
@Data
@ApiModel("The Response Body Wrapper")
public class Response {

    /**
     * A sequence of one or more results, with one {@link Result} member per requested resource
     */
    @ApiModelProperty(value = "A sequence of one or more results, with one Result member per requested resource.")
    @JsonProperty("Response")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<Result> results;

}
