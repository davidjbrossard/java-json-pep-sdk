package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Conveys information about attributes required for policy evaluation that were missing from the request context.
 */
@ApiModel("Conveys information about attributes required for policy evaluation that were missing from the request context")
@Data
public class MissingAttributeDetail {

    /**
     * The identifier of the missing attribute
     */
    @ApiModelProperty(
        value = "The identifier of the missing attribute"
    )
    @JsonProperty("AttributeId")
    String attributeId;

    /**
     *
     */
    @ApiModelProperty(
        value = ""
    )
    @JsonProperty("Value")
    String value;

    /**
     * This attribute, if supplied, SHALL specify the required Issuer of the missing attribute
     */
    @ApiModelProperty(
        value = "This attribute, if supplied, SHALL specify the required Issuer of the missing attribute"
    )
    @JsonProperty("Issuer")
    String issuer;

    /**
     * The data-type of the missing attribute
     */
    @ApiModelProperty(
        value = "The data-type of the missing attribute"
    )
    @JsonProperty("DataType")
    String dataType;

    /**
     * The category identifier of the missing attribute
     */
    @ApiModelProperty(
        value = "The category identifier of the missing attribute"
    )
    @JsonProperty("Category")
    String category;

}
