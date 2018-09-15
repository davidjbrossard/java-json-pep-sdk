package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

/**
 */
@Data
public class AttributeAssignment {

    @Getter(onMethod_ = {@JsonProperty("AttributeId")})
    String attributeId;

    @Getter(onMethod_ = {@JsonProperty("Value")})
    String value;
}
