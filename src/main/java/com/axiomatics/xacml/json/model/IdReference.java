package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Holder of the identifier and version of a policy or policy set which was applicable to the request
 */
@ApiModel("Holder of the identifier and version of a policy or policy set which was applicable to the request")
@Data
public class IdReference {

    /**
     * The identifier of a policy or policy set, depending on which list of {@link PolicyIdentifierList} the {@link IdReference} belongs to
     */
    @ApiModelProperty(value = "The identifier of a policy or policy set")
    @JsonProperty("Id")
    String id;

    /**
     * The version of a policy or policy set, depending on which list of {@link PolicyIdentifierList} the {@link IdReference} belongs to
     */
    @ApiModelProperty(value = "The version of a policy or policy set")
    @JsonProperty("Version")
    String version;
}
