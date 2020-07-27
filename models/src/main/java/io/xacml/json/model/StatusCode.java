package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Contains a major status code value and an optional sequence of minor status code
 */
@ApiModel("Contains a major status code value and an optional sequence of minor status code")
public class StatusCode {

    /**
     * Major status code value
     */
    @ApiModelProperty(
        value = "Major status code value"
    )
    @JsonProperty("Value")
    String value;

    /**
     * Minor status code.  This status code qualifies its parent status code.
     */
    @ApiModelProperty(
        value = "Minor status code.  This status code qualifies its parent status code."
    )
    @JsonProperty("StatusCode")
    StatusCode statusCode;

    public StatusCode() {
    }

    public StatusCode(String value) {
        this.value = value;
    }

    public StatusCode(String value, StatusCode statusCode) {
        this.value = value;
        this.statusCode = statusCode;
    }

    public String getValue() {
        return this.value;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof StatusCode)) return false;
        final StatusCode other = (StatusCode) o;
        if (!Objects.equals(this.getValue(), other.getValue())) return false;
        return Objects.equals(this.getStatusCode(), other.getStatusCode());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $statusCode = this.getStatusCode();
        result = result * PRIME + ($statusCode == null ? 43 : $statusCode.hashCode());
        return result;
    }

    public String toString() {
        return "StatusCode(value=" + this.getValue() + ", statusCode=" + this.getStatusCode() + ")";
    }
}
