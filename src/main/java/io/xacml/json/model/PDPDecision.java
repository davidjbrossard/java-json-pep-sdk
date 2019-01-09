package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

/**
 * The result of policy evaluation
 */
@ApiModel("The result of policy evaluation")
public enum PDPDecision {

    /**
     * The requested access is permitted.
     */
    @JsonProperty("Permit")
    PERMIT(0),

    /**
     * The requested access is denied.
     */
    @JsonProperty("Deny")
    DENY(1),

    /**
     * The PDP is unable to evaluate the requested access.
     * <p>
     * Reasons for such inability include:
     * missing attributes, network errors while retrieving policies, division by zero during policy evaluation, syntax errors in the decision request or in the policy, etc.
     */
    @JsonProperty("Indeterminate")
    INDETERMINATE(2),

    /**
     * The PDP does not have any policy that applies to this decision request.
     */
    @JsonProperty("NotApplicable")
    NOT_APPLICABLE(3);

    int ordinal;

    PDPDecision(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return ordinal;
    }

}
