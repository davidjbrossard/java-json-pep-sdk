package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class NamespaceDeclaration {

    @ApiModelProperty(value = "", example = "md")
    @JsonProperty("Prefix")
    String prefix;

    @ApiModelProperty(
        value = "",
        example = "urn:example:med:schemas:record",
        required = true
    )
    @JsonProperty("Namespace")
    String namespace;

    public NamespaceDeclaration(String namespace) {
        this.namespace = namespace;
    }

    public NamespaceDeclaration(String namespace, String prefix) {
        this.namespace = namespace;
        this.prefix = prefix;
    }

    public NamespaceDeclaration() {
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NamespaceDeclaration)) return false;
        final NamespaceDeclaration other = (NamespaceDeclaration) o;
        if (!Objects.equals(this.getPrefix(), other.getPrefix())) return false;
        return Objects.equals(this.getNamespace(), other.getNamespace());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $prefix = this.getPrefix();
        result = result * PRIME + ($prefix == null ? 43 : $prefix.hashCode());
        final Object $namespace = this.getNamespace();
        result = result * PRIME + ($namespace == null ? 43 : $namespace.hashCode());
        return result;
    }

    public String toString() {
        return "NamespaceDeclaration(prefix=" + this.getPrefix() + ", namespace=" + this.getNamespace() + ")";
    }
}
