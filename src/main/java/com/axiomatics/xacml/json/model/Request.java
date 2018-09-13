package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * The Request Body for PDP Authorize Requests
 */
@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ApiModel("The Request Body for PDP Authorize Requests")
public class Request {
    private boolean returnPolicyIdList = false;
    private boolean combinedDecision = false;
    private String xPathVersion = "http://www.w3.org/TR/1999/REC-xpath-19991116";

    private Category[] categories;
    private MultiRequests multiRequests;

    public Request() {

    }

    @JsonProperty
    public boolean isReturnPolicyIdList() {
        return this.returnPolicyIdList;
    }

    public boolean isCombinedDecision() {
        return this.combinedDecision;
    }
}
