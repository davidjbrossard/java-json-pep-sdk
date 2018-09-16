package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Container of lists of policy and policy set {@link IdReference}s which have been applicable to a request
 */
@ApiModel("Container of lists of policy and policy set IdReferences which have been applicable to a request")
@Data
public class PolicyIdentifierList {

    /**
     * List of policy id references of policies which have been applicable to the request
     */
    @ApiModelProperty(value = "List of policy id references of policies which have been applicable to the request")
    @JsonProperty("PolicyIdReference")
    List<IdReference> policyIdReferences;

    /**
     * List of policy set id references of policies which have been applicable to the request
     */
    @ApiModelProperty(value = "List of policy set id references of policies which have been applicable to the request")
    @JsonProperty("PolicySetIdReference")
    List<IdReference> policySetIdReferences;

}
