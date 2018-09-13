package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Values of the XACML xPathExpression data-type are represented as JSON objects
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class XPathExpression {

    @ApiModelProperty(
        example = "urn:oasis:names:tc:xacml:3.0:attribute-category:resource",
        required = true
    )
    @JsonProperty("XPathCategory")
    String xPathCategory;

    @ApiModelProperty(
        example = "[{\"Namespace\": \"urn:oasis:names:tc:xacml:3.0:core:schema:wd-17\"}]"
    )
    @JsonProperty("Namespaces")
    List<NamespaceDeclaration> namespaces;

    @ApiModelProperty(
        example = "md:record/md:patient/md:patientDoB",
        required = true
    )
    @JsonProperty("XPath")
    String xPath;
}
