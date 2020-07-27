package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Values of the XACML xPathExpression data-type are represented as JSON objects
 */
@ApiModel("Values of the XACML xPathExpression data-type are represented as JSON objects")
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

    public XPathExpression() {
    }

    public boolean addNamespaceDeclaration(String namespace) {
        return namespaceDeclarations.add(new NamespaceDeclaration(namespace));
    }

    public boolean addNamespaceDeclaration(String namespace, String prefix) {
        return namespaceDeclarations.add(new NamespaceDeclaration(namespace, prefix));
    }

    public String getXpathCategory() {
        return this.xpathCategory;
    }

    public List<NamespaceDeclaration> getNamespaceDeclarations() {
        return this.namespaceDeclarations;
    }

    public String getXpath() {
        return this.xpath;
    }

    public void setXpathCategory(String xpathCategory) {
        this.xpathCategory = xpathCategory;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof XPathExpression)) return false;
        final XPathExpression other = (XPathExpression) o;
        if (!Objects.equals(this.getXpathCategory(), other.getXpathCategory())) return false;
        if (!Objects.equals(this.getNamespaceDeclarations(), other.getNamespaceDeclarations())) return false;
        return Objects.equals(this.getXpath(), other.getXpath());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $xpathCategory = this.getXpathCategory();
        result = result * PRIME + ($xpathCategory == null ? 43 : $xpathCategory.hashCode());
        final Object $namespaceDeclarations = this.getNamespaceDeclarations();
        result = result * PRIME + ($namespaceDeclarations == null ? 43 : $namespaceDeclarations.hashCode());
        final Object $xpath = this.getXpath();
        result = result * PRIME + ($xpath == null ? 43 : $xpath.hashCode());
        return result;
    }

    public String toString() {
        return "XPathExpression(xpathCategory=" + this.getXpathCategory() + ", namespaceDeclarations=" + this.getNamespaceDeclarations() + ", xpath=" + this.getXpath() + ")";
    }
}
