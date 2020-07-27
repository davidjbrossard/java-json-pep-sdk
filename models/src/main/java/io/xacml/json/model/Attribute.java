package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * The central abstraction of the request context.  It contains attribute metadata and one or more attribute values.
 */
@ApiModel("The central abstraction of the request context.  It contains attribute meta-data and one or more attribute values")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Attribute {

    public Attribute() {
    }

    public Attribute(String attributeId, Object value) {
        this.attributeId = attributeId;
        this.value = value;
    }

    public Attribute(String attributeId, Object value, boolean includeInResult) {
        this.attributeId = attributeId;
        this.value = value;
        this.includeInResult = includeInResult;
    }

    public Attribute(String attributeId, Object value, String dataType) {
        this.attributeId = attributeId;
        this.value = value;
        this.dataType = dataType;
    }

    public Attribute(String attributeId, Object value, boolean includeInResult, String dataType) {
        this(attributeId, value, includeInResult);
        this.dataType = dataType;
    }

    /**
     * The {@link Attribute} identifier
     */
    @ApiModelProperty(
        value = "The Attribute identifier",
        example = "com.acme.record.recordId",
        required = true
    )
    @JsonProperty("AttributeId")
    String attributeId;

    /**
     * One or more {@link Attribute} values.
     * <p>
     * Can be an array of elements of the same type; either string, boolean, number (which maps to either a XACML integer or double as defined in
     * Supported Data Types) or object.
     */
    @ApiModelProperty(
        value = "One or more attribute values",
        example = "some-attribute-value",
        required = true
    )
    @JsonProperty("Value")
    Object value;

    /**
     * The data-type of the {@link Attribute}
     * <p>
     * Can be omitted in the JSON representation, thus null in object
     * <p>
     * Its default value will be http://www.w3.org/2001/XMLSchema#string unless it can be safely assumed according to rules to the rules set in
     * 3.3.1 Supported Data Types. In the case of an array of values, inference works as described in section 3.3.2.
     */
    @ApiModelProperty(
        value = "A string containing a XACML data type URI or the shorthand notation defined.\n" +
            "The default value will be http://www.w3.org/2001/XMLSchema#string unless the data type can be safely assumed to be otherwise",
        example = "http://www.w3.org/2001/XMLSchema#string"
    )
    @JsonProperty("DataType")
    String dataType;

    /**
     * The {@link Attribute} issuer
     */
    @ApiModelProperty(value = "The Attribute issuer")
    @JsonProperty("Issuer")
    String issuer;

    /**
     * Whether to include this {@link Attribute} in the result.
     * This is useful to correlate requests with their responses in case of multiple requests.
     */
    @ApiModelProperty(value = "Whether to include this attribute in the result")
    @JsonProperty("IncludeInResult")
    boolean includeInResult = false;

    public String getAttributeId() {
        return this.attributeId;
    }

    public Object getValue() {
        return this.value;
    }

    public String getDataType() {
        return this.dataType;
    }

    public String getIssuer() {
        return this.issuer;
    }

    public boolean isIncludeInResult() {
        return this.includeInResult;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setIncludeInResult(boolean includeInResult) {
        this.includeInResult = includeInResult;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Attribute)) return false;
        final Attribute other = (Attribute) o;
        if (!Objects.equals(this.getAttributeId(), other.getAttributeId())) return false;
        if (!Objects.equals(this.getValue(), other.getValue())) return false;
        if (!Objects.equals(this.getDataType(), other.getDataType())) return false;
        if (!Objects.equals(this.getIssuer(), other.getIssuer())) return false;
        return this.isIncludeInResult() == other.isIncludeInResult();
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $attributeId = this.getAttributeId();
        result = result * PRIME + ($attributeId == null ? 43 : $attributeId.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $dataType = this.getDataType();
        result = result * PRIME + ($dataType == null ? 43 : $dataType.hashCode());
        final Object $issuer = this.getIssuer();
        result = result * PRIME + ($issuer == null ? 43 : $issuer.hashCode());
        result = result * PRIME + (this.isIncludeInResult() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "Attribute(attributeId=" + this.getAttributeId() + ", value=" + this.getValue() + ", dataType=" + this.getDataType() + ", issuer=" + this.getIssuer() + ", includeInResult=" + this.isIncludeInResult() + ")";
    }
}
