package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * Encapsulates the authorization decision produced by the PDP
 */
@ApiModel("The Response Body Wrapper")
public class Response {

    /**
     * A sequence of one or more results, with one {@link Result} member per requested resource
     */
    @ApiModelProperty(value = "A sequence of one or more results, with one Result member per requested resource.")
    @JsonProperty("Response")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<Result> results;

    public Response() {
    }

    public List<Result> getResults() {
        return this.results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Response)) return false;
        final Response other = (Response) o;
        return Objects.equals(this.getResults(), other.getResults());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $results = this.getResults();
        result = result * PRIME + ($results == null ? 43 : $results.hashCode());
        return result;
    }

    public String toString() {
        return "Response(results=" + this.getResults() + ")";
    }
}
