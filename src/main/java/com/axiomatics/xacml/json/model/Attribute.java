package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The central abstraction of the request context.  It contains attribute metadata and one or more attribute values.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@ApiModel("A XACML attribute")
public class Attribute {

    /**
     * The unique identifier of the {@link Attribute)
     */
    @ApiModelProperty(
        value = "The unique identifier of the attribute (may include namespace)",
        example = "urn:oasis:names:tc:xacml:2.0:subject:role",
        required = true
    )
    @JsonProperty("AttributeId")
    private String attributeId;

    /**
     * The value of the {@link Attribute)
     */
    @ApiModelProperty(
        value = "The value to provide for the attribute",
        example = "acmeId-001",
        required = true
    )
    @JsonProperty("Value")
    private Object value;

    /**
     * The data-type of the {@link Attribute}
     *
     * Can be omitted in the JSON representation, thus null in object
     *
     * Its default value will be http://www.w3.org/2001/XMLSchema#string unless it can be safely assumed according to rules to the rules set in
     * 3.3.1 Supported Data Types. In the case of an array of values, inference works as described in section 3.3.2.
     */
    @ApiModelProperty(
        value = "The DataType value can be omitted in the JSON representation. " +
            "Its default value will be http://www.w3.org/2001/XMLSchema#string unless it can be safely assumed according " +
            "to the rules set in 3.3.1 Supported Data Types.\n In the case of an array of values, inference works as described in section 3.3.2.",
        example = "http://www.w3.org/2001/XMLSchema#string"
    )
    @JsonProperty("DataType")
    private String dataType;

    public Attribute(String attributeId, Object value) {
        this.attributeId = attributeId;
        this.value = value;
    }

    public Attribute(String attributeId, Object value, String dataType) {
        this.attributeId = attributeId;
        this.value = value;
        this.dataType = dataType;
    }
}
