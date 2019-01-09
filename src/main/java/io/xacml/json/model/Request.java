package io.xacml.json.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * The Request Body for PDP Authorize Requests
 */
@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ApiModel("The Request Body for PDP XACML Authorize Requests")
public class Request {

    /**
     * This attribute is used to request that the PDP return a list of all fully applicable policies and policy sets which were used in the decision
     * as a part of the decision response.
     * <p>
     * Defaults to false if not specified
     */
    @ApiModelProperty(example = "false", notes = "This attribute is used to request that the PDP return a list of all fully applicable policies and " +
        "policy sets which were used in the decision as a part of the decision response. Defaults to false if not specified")
    @JsonProperty("ReturnPolicyIdList")
    boolean returnPolicyIdList = false;

    /**
     * This attribute is used to request that the PDP combines multiple decisions into a single decision.
     * <p>
     * Defaults to false if not specified
     */
    @ApiModelProperty(example = "false", notes = "This attribute is used to request that the PDP combines multiple decisions into a single decision." +
        "Defaults to false if not specified")
    @JsonProperty("CombinedDecision")
    boolean combinedDecision = false;

    /**
     * The XPath version
     */
    @ApiModelProperty(notes = "The xpath version. Mandatory if the XACML request contains XPath expressions; otherwise, optional.")
    @JsonProperty("XPathVersion")
    String xpathVersion;

    /**
     * Collection of custom-defined attribute categories. Optional
     */
    @ApiModelProperty(notes = "Collection of custom-defined attribute categories. Optional")
    @JsonProperty("Category")
    List<Category> customCategories = new ArrayList<>();

    /**
     * Collection of resource-related attribute categories
     */
    @ApiModelProperty(value = "Collection of resource-related attribute categories. Optional")
    @JsonProperty("Resource")
    List<Category> resourceCategories = new ArrayList<>();

    /**
     * Collection of action-related attribute categories. Optional
     */
    @ApiModelProperty(value = "Collection of action-related attribute categories. Optional")
    @JsonProperty("Action")
    List<Category> actionCategories = new ArrayList<>();

    /**
     * Collection of environment-related attribute categories. Optional
     */
    @ApiModelProperty(value = "Collection of environment-related attribute categories. Optional")
    @JsonProperty("Environment")
    List<Category> environmentCategories = new ArrayList<>();

    /**
     * Collection of subject-related attribute categories. Optional
     *
     * The subject is the system entity that initiated the access request. That is, the initial entity in a request chain.
     */
    @ApiModelProperty(value = "Collection of subject-related attribute categories. The subject is the system entity that initiated the access request. " +
        "That is, the initial entity in a request chain. Optional")
    @JsonProperty("AccessSubject")
    List<Category> accessSubjectCategories = new ArrayList<>();

    /**
     * Collection of recipient-related attribute categories. Optional
     *
     * The recipient subject is the system entity that will receive the results of the request (used when it is distinct from the access-subject).
     */
    @ApiModelProperty(notes = "Collection of recipient-related attributes. The recipient subject is the system entity that will receive the results " +
        "of the request (used when it is distinct from the access-subject). Optional")
    @JsonProperty("RecipientSubject")
    List<Category> recipientSubjectCategories = new ArrayList<>();

    /**
     * Collection of intermediary-subject-related attribute categories. Optional
     *
     * The intermediary subject is the system entity through which the access request was passed.
     */
    @ApiModelProperty(notes = "Collection of intermediary-subject-related attributes. The intermediary subject is the system entity through which " +
        "the access request was passed. Optional")
    @JsonProperty("IntermediarySubject")
    List<Category> intermediarySubjectCategories = new ArrayList<>();

    /**
     * Collection of codebase-related attributes.  Optional
     *
     * The codebase a system entity associated with a local or remote codebase that generated the request. Corresponding subject attributes might
     * include the URL from which it was loaded and/or the identity of the code-signer.
     */
    @ApiModelProperty(notes = "Collection of codebase-related attributes. The codebase a system entity associated with a local or remote codebase " +
        "that generated the request. Corresponding subject attributes might include the URL from which it was loaded and/or the identity of the " +
        "code-signer. Optional")
    @JsonProperty("CodeBase")
    List<Category> codeBaseCategories = new ArrayList<>();

    /**
     * Collection of requesting-machine-related attributes. Optional
     *
     * The intermediary subject This identifier is a system entity associated with the computer that initiated the access request. An example
     * would be an IPsec identity.
     */
    @ApiModelProperty(notes = "Collection of requesting-machine-related attributes. The intermediary subject This identifier is a system entity " +
        "associated with the computer that initiated the access request. An example would be an IPsec identity. Optional")
    @JsonProperty("RequestingMachine")
    List<Category> requestingMachineCategories = new ArrayList<>();

    /**
     * Lists multiple request contexts by references to the various lists of {@link Category} that are members of a {@link Request}. Optional
     */
    @ApiModelProperty(notes = "Lists multiple request contexts by references to the Category members. Optional")
    @JsonProperty("MultiRequests")
    MultiRequests multiRequests;

    public boolean addCustomCategory(Category category) {
        return customCategories.add(category);
    }

    public boolean addResourceCategory(Category category) {
        return resourceCategories.add(category);
    }

    public boolean addActionCategory(Category category) {
        return actionCategories.add(category);
    }

    public boolean addEnvironmentCategory(Category category) {
        return environmentCategories.add(category);
    }

    public boolean addAccessSubjectCategory(Category category) {
        return accessSubjectCategories.add(category);
    }

    public boolean addRecipientSubjectCategories(Category category) {
        return recipientSubjectCategories.add(category);
    }

    public boolean addIntermediarySubjectCategories(Category category) {
        return intermediarySubjectCategories.add(category);
    }

    public boolean addCodeBaseCategories(Category category) {
        return codeBaseCategories.add(category);
    }

    public boolean addRequestingMachineCategories(Category category) {
        return requestingMachineCategories.add(category);
    }
    
}
