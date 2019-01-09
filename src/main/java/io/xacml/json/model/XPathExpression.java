package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Values of the XACML xPathExpression data-type are represented as JSON objects
 */
@ApiModel("Values of the XACML xPathExpression data-type are represented as JSON objects")
@Data
public class XPathExpression {

    /**
     * Provides the category of the {@link Category#content} where the expression applies
     */
    @ApiModelProperty(
        value = "Provides the category of the Content in Category where the expression applies.",
        example = "urn:oasis:names:tc:xacml:3.0:attribute-category:resource",
        required = true
    )
    @JsonProperty("XPathCategory")
    String xpathCategory;

    /**
     *
     */
    @ApiModelProperty(
        example = "[{\"Namespace\": \"urn:oasis:names:tc:xacml:3.0:core:schema:wd-17\"}]"
    )
    @JsonProperty("Namespaces")
    final List<NamespaceDeclaration> namespaceDeclarations = new ArrayList<>();

    /**
     *
     */
    @ApiModelProperty(
        example = "md:record/md:patient/md:patientDoB",
        required = true
    )
    @JsonProperty("XPath")
    String xpath;

    public XPathExpression(String xpathCategory, String xpath) {
        this.xpathCategory = xpathCategory;
        this.xpath = xpath;
    }

    public boolean addNamespaceDeclaration(String namespace) {
        return namespaceDeclarations.add(new NamespaceDeclaration(namespace));
    }

    public boolean addNamespaceDeclaration(String namespace, String prefix) {
        return namespaceDeclarations.add(new NamespaceDeclaration(namespace, prefix));
    }
}
