package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 */
@Data
public class ObligationOrAdvice {

    @JsonProperty("Id")
    String id;

    @JsonProperty("AttributeAssignment")
    List<AttributeAssignment> attributeAssignments;
}
