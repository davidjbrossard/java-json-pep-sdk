package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * An authorization decision result
 * <p>
 * It MAY include a set of obligations that MUST be fulfilled by the PEP.  If the PEP does not understand or cannot fulfill an obligation,
 * then the action of the PEP is determined by its bias.
 * It MAY include a set of advice with supplemental information which MAY be safely ignored by the PEP.
 */
@Data
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
}
