package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

/**
 */
@Data
public class NamespaceDeclaration {

    @ApiModelProperty(
        example = "md"
    )
    @JsonProperty("Prefix")
    String prefix;

    @ApiModelProperty(
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

}
