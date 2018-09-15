package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of requests with references to a {@link Request}'s {@link Category}(s)
 */
@Data
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class MultiRequests {

    @ApiModelProperty(
        value = "References to Category members for the request",
        example = "[{\"ReferenceId\":[\"foo1\",\"bar1\"]}]",
        required = true
    )
    @JsonProperty("RequestReference")
    final List<RequestReference> requestReferences = new ArrayList<>();

    public boolean addRequestReferenceWithIds(List<String> referenceIds) {
        return requestReferences.add(new RequestReference(referenceIds));
    }

    public boolean addRequestReference(RequestReference requestReference) {
        return requestReferences.add(requestReference);
    }

}
