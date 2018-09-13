package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Julio Cesar Villalta III <jvillalta@nvisia.com>
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonRootName(value = "AuthorizeRequest")
@ApiModel("The Request Body for PDP Authorize Requests")
public class AuthorizeRequest {

    /**
     * Collection of Subject-related attributes
     */
    @ApiModelProperty(value = "Collection of subject-related attributes")
    @JsonProperty("AccessSubject")
    AttributeCollection accessSubjectAttributes;

    /**
     * Collection of Action-related attributes
     */
    @ApiModelProperty(value = "Collection of action-related attributes")
    @JsonProperty("Action")
    AttributeCollection actionAttributes;

    /**
     * Collection of Resource-related attributes
     */
    @ApiModelProperty(value = "Collection of resource-related attributes")
    @JsonProperty("Resource")
    AttributeCollection resourceAttributes;

    /**
     * Collection of Environment-related attributes
     */
    @ApiModelProperty(value = "Collection of environment-related attributes")
    @JsonProperty("Environment")
    AttributeCollection environmentAttributes;
}
