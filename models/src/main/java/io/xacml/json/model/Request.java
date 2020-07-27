package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Request Body for PDP Authorize Requests
 */
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
     * <p>
     * The subject is the system entity that initiated the access request. That is, the initial entity in a request chain.
     */
    @ApiModelProperty(value = "Collection of subject-related attribute categories. The subject is the system entity that initiated the access request. " +
            "That is, the initial entity in a request chain. Optional")
    @JsonProperty("AccessSubject")
    List<Category> accessSubjectCategories = new ArrayList<>();

    /**
     * Collection of recipient-related attribute categories. Optional
     * <p>
     * The recipient subject is the system entity that will receive the results of the request (used when it is distinct from the access-subject).
     */
    @ApiModelProperty(notes = "Collection of recipient-related attributes. The recipient subject is the system entity that will receive the results " +
            "of the request (used when it is distinct from the access-subject). Optional")
    @JsonProperty("RecipientSubject")
    List<Category> recipientSubjectCategories = new ArrayList<>();

    /**
     * Collection of intermediary-subject-related attribute categories. Optional
     * <p>
     * The intermediary subject is the system entity through which the access request was passed.
     */
    @ApiModelProperty(notes = "Collection of intermediary-subject-related attributes. The intermediary subject is the system entity through which " +
            "the access request was passed. Optional")
    @JsonProperty("IntermediarySubject")
    List<Category> intermediarySubjectCategories = new ArrayList<>();

    /**
     * Collection of codebase-related attributes.  Optional
     * <p>
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
     * <p>
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

    public Request() {
    }

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

    @Transient
    public boolean isMultiDecisionProfileRequest() {
        return null != multiRequests;
    }

    public boolean isReturnPolicyIdList() {
        return this.returnPolicyIdList;
    }

    public boolean isCombinedDecision() {
        return this.combinedDecision;
    }

    public String getXpathVersion() {
        return this.xpathVersion;
    }

    public List<Category> getCustomCategories() {
        return this.customCategories;
    }

    public List<Category> getResourceCategories() {
        return this.resourceCategories;
    }

    public List<Category> getActionCategories() {
        return this.actionCategories;
    }

    public List<Category> getEnvironmentCategories() {
        return this.environmentCategories;
    }

    public List<Category> getAccessSubjectCategories() {
        return this.accessSubjectCategories;
    }

    public List<Category> getRecipientSubjectCategories() {
        return this.recipientSubjectCategories;
    }

    public List<Category> getIntermediarySubjectCategories() {
        return this.intermediarySubjectCategories;
    }

    public List<Category> getCodeBaseCategories() {
        return this.codeBaseCategories;
    }

    public List<Category> getRequestingMachineCategories() {
        return this.requestingMachineCategories;
    }

    public MultiRequests getMultiRequests() {
        return this.multiRequests;
    }

    public void setReturnPolicyIdList(boolean returnPolicyIdList) {
        this.returnPolicyIdList = returnPolicyIdList;
    }

    public void setCombinedDecision(boolean combinedDecision) {
        this.combinedDecision = combinedDecision;
    }

    public void setXpathVersion(String xpathVersion) {
        this.xpathVersion = xpathVersion;
    }

    public void setCustomCategories(List<Category> customCategories) {
        this.customCategories = customCategories;
    }

    public void setResourceCategories(List<Category> resourceCategories) {
        this.resourceCategories = resourceCategories;
    }

    public void setActionCategories(List<Category> actionCategories) {
        this.actionCategories = actionCategories;
    }

    public void setEnvironmentCategories(List<Category> environmentCategories) {
        this.environmentCategories = environmentCategories;
    }

    public void setAccessSubjectCategories(List<Category> accessSubjectCategories) {
        this.accessSubjectCategories = accessSubjectCategories;
    }

    public void setRecipientSubjectCategories(List<Category> recipientSubjectCategories) {
        this.recipientSubjectCategories = recipientSubjectCategories;
    }

    public void setIntermediarySubjectCategories(List<Category> intermediarySubjectCategories) {
        this.intermediarySubjectCategories = intermediarySubjectCategories;
    }

    public void setCodeBaseCategories(List<Category> codeBaseCategories) {
        this.codeBaseCategories = codeBaseCategories;
    }

    public void setRequestingMachineCategories(List<Category> requestingMachineCategories) {
        this.requestingMachineCategories = requestingMachineCategories;
    }

    public void setMultiRequests(MultiRequests multiRequests) {
        this.multiRequests = multiRequests;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Request)) return false;
        final Request other = (Request) o;
        if (this.isReturnPolicyIdList() != other.isReturnPolicyIdList()) return false;
        if (this.isCombinedDecision() != other.isCombinedDecision()) return false;
        if (!Objects.equals(this.getXpathVersion(), other.getXpathVersion())) return false;
        if (!Objects.equals(this.getCustomCategories(), other.getCustomCategories())) return false;
        if (!Objects.equals(this.getResourceCategories(), other.getResourceCategories())) return false;
        if (!Objects.equals(this.getActionCategories(), other.getActionCategories())) return false;
        if (!Objects.equals(this.getEnvironmentCategories(), other.getEnvironmentCategories())) return false;
        if (!Objects.equals(this.getAccessSubjectCategories(), other.getAccessSubjectCategories())) return false;
        if (!Objects.equals(this.getRecipientSubjectCategories(), other.getRecipientSubjectCategories())) return false;
        if (!Objects.equals(this.getIntermediarySubjectCategories(), other.getIntermediarySubjectCategories()))
            return false;
        if (!Objects.equals(this.getCodeBaseCategories(), other.getCodeBaseCategories())) return false;
        if (!Objects.equals(this.getRequestingMachineCategories(), other.getRequestingMachineCategories()))
            return false;
        return Objects.equals(this.getMultiRequests(), other.getMultiRequests());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + (this.isReturnPolicyIdList() ? 79 : 97);
        result = result * PRIME + (this.isCombinedDecision() ? 79 : 97);
        final Object $xpathVersion = this.getXpathVersion();
        result = result * PRIME + ($xpathVersion == null ? 43 : $xpathVersion.hashCode());
        final Object $customCategories = this.getCustomCategories();
        result = result * PRIME + ($customCategories == null ? 43 : $customCategories.hashCode());
        final Object $resourceCategories = this.getResourceCategories();
        result = result * PRIME + ($resourceCategories == null ? 43 : $resourceCategories.hashCode());
        final Object $actionCategories = this.getActionCategories();
        result = result * PRIME + ($actionCategories == null ? 43 : $actionCategories.hashCode());
        final Object $environmentCategories = this.getEnvironmentCategories();
        result = result * PRIME + ($environmentCategories == null ? 43 : $environmentCategories.hashCode());
        final Object $accessSubjectCategories = this.getAccessSubjectCategories();
        result = result * PRIME + ($accessSubjectCategories == null ? 43 : $accessSubjectCategories.hashCode());
        final Object $recipientSubjectCategories = this.getRecipientSubjectCategories();
        result = result * PRIME + ($recipientSubjectCategories == null ? 43 : $recipientSubjectCategories.hashCode());
        final Object $intermediarySubjectCategories = this.getIntermediarySubjectCategories();
        result = result * PRIME + ($intermediarySubjectCategories == null ? 43 : $intermediarySubjectCategories.hashCode());
        final Object $codeBaseCategories = this.getCodeBaseCategories();
        result = result * PRIME + ($codeBaseCategories == null ? 43 : $codeBaseCategories.hashCode());
        final Object $requestingMachineCategories = this.getRequestingMachineCategories();
        result = result * PRIME + ($requestingMachineCategories == null ? 43 : $requestingMachineCategories.hashCode());
        final Object $multiRequests = this.getMultiRequests();
        result = result * PRIME + ($multiRequests == null ? 43 : $multiRequests.hashCode());
        return result;
    }

    public String toString() {
        return "Request(returnPolicyIdList=" + this.isReturnPolicyIdList() + ", combinedDecision=" + this.isCombinedDecision() + ", xpathVersion=" + this.getXpathVersion() + ", customCategories=" + this.getCustomCategories() + ", resourceCategories=" + this.getResourceCategories() + ", actionCategories=" + this.getActionCategories() + ", environmentCategories=" + this.getEnvironmentCategories() + ", accessSubjectCategories=" + this.getAccessSubjectCategories() + ", recipientSubjectCategories=" + this.getRecipientSubjectCategories() + ", intermediarySubjectCategories=" + this.getIntermediarySubjectCategories() + ", codeBaseCategories=" + this.getCodeBaseCategories() + ", requestingMachineCategories=" + this.getRequestingMachineCategories() + ", multiRequests=" + this.getMultiRequests() + ")";
    }
}
