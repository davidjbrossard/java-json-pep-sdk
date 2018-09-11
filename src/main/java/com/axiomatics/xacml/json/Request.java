package com.axiomatics.xacml.json;

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

	public void setReturnPolicyIdList(boolean returnPolicyIdList) {
		this.returnPolicyIdList = returnPolicyIdList;
	}

	public boolean isCombinedDecision() {
		return this.combinedDecision;
	}

	public void setCombinedDecision(boolean combinedDecision) {
		this.combinedDecision = combinedDecision;
	}

	public String getXPathVersion() {
		return this.xPathVersion;
	}

	public void setXPathVersion(String xPathVersion) {
		this.xPathVersion = xPathVersion;
	}

	public Category[] getCategory() {
		return this.categories;
	}

	public void setCategory(Category[] category) {
		this.categories = category;
	}

	public MultiRequests getMultiRequests() {
		return this.multiRequests;
	}

	public void setMultiRequests(MultiRequests multiRequests) {
		this.multiRequests = multiRequests;
	}
}
