package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Defines an instance of a {@link Request} in terms of {@link Category} members.
 * <p>
 * Each of the {@link #referenceIds} matches a {@link Category#id} in a {@link Request}.
 */
public class RequestReference {

    public RequestReference() {
        this.referenceIds = new ArrayList<>();
    }

    public RequestReference(List<String> referenceIds) {
        this.referenceIds = referenceIds;
    }

    /**
     * An array of one or more strings, where each string MUST be the value of a {@link Category#id}, that is a member of a {@link Request}.
     */
    @ApiModelProperty(
        value = "An array of one or more strings, where each string MUST be the value of a {@link Category#id}, that is a member of a {@link Request}",
        example = "[\"foo2\",\"bar1\"]",
        required = true
    )
    @JsonProperty("ReferenceId")
    final List<String> referenceIds;

    public boolean addReferenceId(String referenceId) {
        return referenceIds.add(referenceId);
    }

    public List<String> getReferenceIds() {
        return this.referenceIds;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RequestReference)) return false;
        final RequestReference other = (RequestReference) o;
        return Objects.equals(this.getReferenceIds(), other.getReferenceIds());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $referenceIds = this.getReferenceIds();
        result = result * PRIME + ($referenceIds == null ? 43 : $referenceIds.hashCode());
        return result;
    }

    public String toString() {
        return "RequestReference(referenceIds=" + this.getReferenceIds() + ")";
    }
}
