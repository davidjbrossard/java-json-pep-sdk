package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Used for including arguments in obligations and advice.
 * <p>
 * Its attribute Id and value shall be evaluated into the corresponding attribute value. The value specified SHALL be understood by the PEP, but it
 * is not further specified by XACML
 */
@ApiModel("Used for including arguments in obligations and advice. Its attribute Id and value shall be evaluated into the corresponding attribute " +
    "value. The value specified SHALL be understood by the PEP, but it is not further specified by XACML")
@Data
public class AttributeAssignment {

    /**
     * The {@link Attribute} identifier
     */
    @ApiModelProperty(
        value = "The Attribute identifier",
        example = "com.acme.record.recordId",
        required = true
    )
    @JsonProperty("AttributeId")
    String attributeId;

    /**
     * The {@link Attribute} value
     */
    @ApiModelProperty(
        value = "The Attribute value",
        required = true
    )
    @JsonProperty("Value")
    String value;
}
