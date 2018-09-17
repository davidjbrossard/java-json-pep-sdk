package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 */
@Data
public class ObligationOrAdvice {

    /**
     * Obligation or Advice identifier.
     */
    @ApiModelProperty(value = "Obligation or Advice identifier.")
    @JsonProperty("Id")
    String id;

    /**
     * A {@link AttributeAssignment} is used for including arguments in obligation and advice expressions.  It SHALL contain a
     * {@link AttributeAssignment#attributeId} and the corresponding {@link AttributeAssignment#value}.
     */
    @ApiModelProperty(value = "Used for including arguments in obligation and advice expressions")
    @JsonProperty("AttributeAssignment")
    List<AttributeAssignment> attributeAssignments;
}
