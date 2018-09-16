package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Specifies attributes of a Category (like subject, resource, action, environment or another category) by listing a sequence of {@link Attribute}s
 */
@ApiModel("Specifies attributes of a Category (like subject, resource, action, environment or another category) by listing a sequence of Attributes")
@Data
public class Category {

    public Category() {

    }

    public Category(String id) {
        this.id = id;
    }

    /**
     * A string containing a XACML category URI or the shorthand notation defined
     * <p>
     * Mandatory for a {@link Category} object in the {@link Request#customCategories} member array; otherwise, optional.
     */
    @ApiModelProperty(
        value = "A string containing a XACML category URI or the shorthand notation defined in section 4.2.2.1. " +
            "Mandatory for a Category object in the \"Category\" member array; otherwise, optional. ",
        example = "urn:oasis:names:tc:xacml:2.0:subject:role"
    )
    @JsonProperty("CategoryId")
    String categoryId;

    /**
     * A unique identifier for this Category. It is primarily intended to be referenced in multiple requests.
     */
    @ApiModelProperty(
        value = "A unique identifier for this Category. It is primarily intended to be referenced in multiple requests.",
        example = "A1"
    )
    @JsonProperty("Id")
    String id;

    /**
     * Specifies additional sources of attributes in free form XML document format
     * <p>
     * There are two possible ways to represent the XML content of a XACML request in the JSON representation: XML escaping or Base64
     * encoding. The request parser must determine whether XML escaping or Base 64 encoding is used. There are no members in the JSON request
     * to indicate which is used.
     * <p>
     * In both cases, any XML content sent in a JSON request MUST include all namespace definitions needed to parse that content.
     * <p>
     * The value of the "Content" member is a string which MUST contain an XML payload per the XACML specification. XML content must be escaped
     * before being inserted into the JSON request. JSON dictates double quotes (") be escaped using a backslash (\). This profile therefore follows
     * this behavior.
     * <p>
     * In addition, since the XML content could itself contain backslashes and possibly the sequence \", it is important to also
     * escape backslashes.
     * <p>
     * In the case of Base64 encoding, the XML content shall be converted to its Base64 representation as per [BASE64
     */
    @ApiModelProperty(
        value = "Specifies additional sources of attributes in free form XML document format",
        example = "<?xml version=\"1.0\"?><catalog><book id=\"bk101\"><author>Gambardella, Matthew</author><title>XML Developer's Guide</title><genre>" +
            "Computer</genre><price>44.95</price><publish_date>2000-10-01</publish_date><description>An in-depth look at creating applications with " +
            "XML.</description></book></catalog>"
    )
    @JsonProperty("Content")
    String content;

    /**
     * A sequence of {@link Attribute}s that apply to the category of the request
     */
    @ApiModelProperty(
        value = "A sequence of attributes that apply to the category of the request"
    )
    @JsonProperty("Attribute")
    final List<Attribute> attributes = new ArrayList<>();


    public boolean addAttribute(Attribute attribute) {
        return attributes.add(attribute);
    }

    public boolean addAttribute(String attributeId, Object value) {
        return attributes.add(new Attribute(attributeId, value));
    }

    public boolean addAttribute(String attributeId, Object value, String dataType) {
        return attributes.add(new Attribute(attributeId, value, dataType));
    }

    public boolean addAttribute(String attributeId, Object value, Boolean includeInResult) {
        return attributes.add(new Attribute(attributeId, value, includeInResult));
    }

    public boolean addAttribute(String attributeId, Object value, Boolean includeInResult, String dataType) {
        return attributes.add(new Attribute(attributeId, value, includeInResult, dataType));
    }

}
