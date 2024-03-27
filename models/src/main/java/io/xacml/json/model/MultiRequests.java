package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contains a list of requests with references to a {@link Request}'s {@link Category}(s)
 */
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class MultiRequests {

    /**
     * References to @{link Category} members for a {@link Request}
     */
    @ApiModelProperty(
        value = "References to Category members for the request",
        example = "[{\"ReferenceId\":[\"foo1\",\"bar1\"]}]",
        required = true
    )
    @JsonProperty("RequestReference")
    final List<RequestReference> requestReferences = new ArrayList<>();

    public MultiRequests() {
    }

    public boolean addRequestReferenceWithIds(List<String> referenceIds) {
        return requestReferences.add(new RequestReference(referenceIds));
    }

    public boolean addRequestReference(RequestReference requestReference) {
        return requestReferences.add(requestReference);
    }

    public List<RequestReference> getRequestReferences() {
        return this.requestReferences;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MultiRequests)) return false;
        final MultiRequests other = (MultiRequests) o;
        return Objects.equals(this.getRequestReferences(), other.getRequestReferences());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $requestReferences = this.getRequestReferences();
        result = result * PRIME + ($requestReferences == null ? 43 : $requestReferences.hashCode());
        return result;
    }

    public String toString() {
        return "MultiRequests(requestReferences=" + this.getRequestReferences() + ")";
    }
}
