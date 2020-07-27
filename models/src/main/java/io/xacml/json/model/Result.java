package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * An authorization decision result
 * <p>
 * It MAY include a set of obligations that MUST be fulfilled by the PEP.  If the PEP does not understand or cannot fulfill an obligation,
 * then the action of the PEP is determined by its bias.
 * It MAY include a set of advice with supplemental information which MAY be safely ignored by the PEP.
 */
@ApiModel("An authorization decision result")
public class Result {

    /**
     * The authorization decision
     */
    @ApiModelProperty(value = "The authorization decision", allowableValues = "Permit, Deny, Indeterminate, NotApplicable")
    @JsonProperty("Decision")
    PDPDecision decision;

    /**
     * Indicates whether errors occurred during evaluation of the decision request, and optionally, information about those errors
     */
    @ApiModelProperty(value = "Indicates whether errors occurred during evaluation of the decision request, and optionally, information about those " +
        "errors.")
    @JsonProperty("Status")
    Status status;

    /**
     * A list of advice that provide supplemental information to the PEP.
     * If the PEP does not understand an advice, the PEP may safely ignore the advice.
     */
    @ApiModelProperty(value = "A list of advice that provide supplemental information to the PEP.\n" +
        "If the PEP does not understand an advice, the PEP may safely ignore the advice. ")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty("AssociatedAdvice")
    List<ObligationOrAdvice> associatedAdvice;

    /**
     * A list of obligations that MUST be fulfilled by the PEP.
     * If the PEP does not understand or cannot fulfill an obligation, then the action of the PEP is determined by its bias.
     */
    @ApiModelProperty(value = "A list of obligations that MUST be fulfilled by the PEP.\n" +
        "If the PEP does not understand or cannot fulfill an obligation, then the action of the PEP is determined by its bias.")
    @JsonProperty("Obligations")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<ObligationOrAdvice> obligations;

    /**
     * A list of attributes that were part of the request. The choice of which attributes are included here is made with the
     * {@link Attribute#includeInResult} for associated with a {@link Request}
     */
    @ApiModelProperty(value = "A list of attributes that were part of the request. The choice of which attributes are included here is made with the " +
        "IncludeInResult attribute of the Attribute elements of the request")
    @JsonProperty("Category")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<Category> categories;

    /**
     * Object containing lists of policy and policy set {@link IdReference}s which have been applicable to a request.
     * <p>
     * If {@link Request#returnPolicyIdList} is true, a PDP that implements this optional feature MUST return a list of all policies which were
     * found to be fully applicable.
     */
    @ApiModelProperty(value = "Object containing lists of policy and policy set IdReferences which have been applicable to a request.\n" +
        "If the ReturnPolicyIdList attribute in the Request is true , a PDP that implements this optional feature MUST return a " +
        "list of all policies which were found to be fully applicable.")
    @JsonProperty("PolicyIdentifierList")
    PolicyIdentifierList policyIdentifierList;

    public Result() {
    }

    public PDPDecision getDecision() {
        return this.decision;
    }

    public Status getStatus() {
        return this.status;
    }

    public List<ObligationOrAdvice> getAssociatedAdvice() {
        return this.associatedAdvice;
    }

    public List<ObligationOrAdvice> getObligations() {
        return this.obligations;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public PolicyIdentifierList getPolicyIdentifierList() {
        return this.policyIdentifierList;
    }

    public void setDecision(PDPDecision decision) {
        this.decision = decision;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssociatedAdvice(List<ObligationOrAdvice> associatedAdvice) {
        this.associatedAdvice = associatedAdvice;
    }

    public void setObligations(List<ObligationOrAdvice> obligations) {
        this.obligations = obligations;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setPolicyIdentifierList(PolicyIdentifierList policyIdentifierList) {
        this.policyIdentifierList = policyIdentifierList;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Result)) return false;
        final Result other = (Result) o;
        if (!Objects.equals(this.getDecision(), other.getDecision())) return false;
        if (!Objects.equals(this.getStatus(), other.getStatus())) return false;
        if (!Objects.equals(this.getAssociatedAdvice(), other.getAssociatedAdvice())) return false;
        if (!Objects.equals(this.getObligations(), other.getObligations())) return false;
        if (!Objects.equals(this.getCategories(), other.getCategories())) return false;
        return Objects.equals(this.getPolicyIdentifierList(), other.getPolicyIdentifierList());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $decision = this.getDecision();
        result = result * PRIME + ($decision == null ? 43 : $decision.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $associatedAdvice = this.getAssociatedAdvice();
        result = result * PRIME + ($associatedAdvice == null ? 43 : $associatedAdvice.hashCode());
        final Object $obligations = this.getObligations();
        result = result * PRIME + ($obligations == null ? 43 : $obligations.hashCode());
        final Object $categories = this.getCategories();
        result = result * PRIME + ($categories == null ? 43 : $categories.hashCode());
        final Object $policyIdentifierList = this.getPolicyIdentifierList();
        result = result * PRIME + ($policyIdentifierList == null ? 43 : $policyIdentifierList.hashCode());
        return result;
    }

    public String toString() {
        return "Result(decision=" + this.getDecision() + ", status=" + this.getStatus() + ", associatedAdvice=" + this.getAssociatedAdvice() + ", obligations=" + this.getObligations() + ", categories=" + this.getCategories() + ", policyIdentifierList=" + this.getPolicyIdentifierList() + ")";
    }
}
