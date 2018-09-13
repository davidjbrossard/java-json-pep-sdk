package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
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
    @NonNull
    @JsonProperty("Namespace")
    String namespace;

    public NamespaceDeclaration(String namespace) {
        this.namespace = namespace;
    }

    public NamespaceDeclaration(String prefix, String namespace) {
        this.prefix = prefix;
        this.namespace = namespace;
    }

}
