package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Defines an instance of a request in terms of {@link Category} members.
 * <p>
 * Each of the {@link #referenceIds} matches a {@link Category#id} in a {@link Request}.
 */
@Data
public class RequestReference {

    public RequestReference() {
        this.referenceIds = new ArrayList<>();
    }

    public RequestReference(List<String> referenceIds) {
        this.referenceIds = referenceIds;
    }

    @ApiModelProperty(
        value = "An array of one or more strings. Each string MUST be the value of a Category object’s \"Id\" member.",
        example = "[\"foo2\",\"bar1\"]",
        required = true
    )
    @JsonProperty("ReferenceId")
    final List<String> referenceIds;

    public boolean addReferenceId(String referenceId) {
        return referenceIds.add(referenceId);
    }
}
