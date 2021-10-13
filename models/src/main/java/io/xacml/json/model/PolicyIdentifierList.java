package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * Container of lists of policy and policy set {@link IdReference}s which have been applicable to a request
 */
@ApiModel("Container of lists of policy and policy set IdReferences which have been applicable to a request")
public class PolicyIdentifierList {

    /**
     * List of policy id references of policies which have been applicable to the request
     */
    @ApiModelProperty(value = "List of policy id references of policies which have been applicable to the request")
    @JsonProperty("PolicyIdReference")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<IdReference> policyIdReferences;

    /**
     * List of policy set id references of policies which have been applicable to the request
     */
    @ApiModelProperty(value = "List of policy set id references of policies which have been applicable to the request")
    @JsonProperty("PolicySetIdReference")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<IdReference> policySetIdReferences;

    public PolicyIdentifierList() {
    }

    public List<IdReference> getPolicyIdReferences() {
        return this.policyIdReferences;
    }

    public List<IdReference> getPolicySetIdReferences() {
        return this.policySetIdReferences;
    }

    public void setPolicyIdReferences(List<IdReference> policyIdReferences) {
        this.policyIdReferences = policyIdReferences;
    }

    public void setPolicySetIdReferences(List<IdReference> policySetIdReferences) {
        this.policySetIdReferences = policySetIdReferences;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PolicyIdentifierList)) return false;
        final PolicyIdentifierList other = (PolicyIdentifierList) o;
        if (!Objects.equals(this.getPolicyIdReferences(), other.getPolicyIdReferences())) return false;
        return Objects.equals(this.getPolicySetIdReferences(), other.getPolicySetIdReferences());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $policyIdReferences = this.getPolicyIdReferences();
        result = result * PRIME + ($policyIdReferences == null ? 43 : $policyIdReferences.hashCode());
        final Object $policySetIdReferences = this.getPolicySetIdReferences();
        result = result * PRIME + ($policySetIdReferences == null ? 43 : $policySetIdReferences.hashCode());
        return result;
    }

    public String toString() {
        return "PolicyIdentifierList(policyIdReferences=" + this.getPolicyIdReferences() + ", policySetIdReferences=" + this.getPolicySetIdReferences() + ")";
    }
}
