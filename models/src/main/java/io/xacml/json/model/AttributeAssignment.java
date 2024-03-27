package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Used for including arguments in obligations and advice.
 * <p>
 * Its attribute Id and value shall be evaluated into the corresponding attribute value. The value specified SHALL be understood by the PEP, but it
 * is not further specified by XACML
 */
@ApiModel("Used for including arguments in obligations and advice. Its attribute Id and value shall be evaluated into the corresponding attribute " +
    "value. The value specified SHALL be understood by the PEP, but it is not further specified by XACML")
public class AttributeAssignment {

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
     * The {@link Attribute} value
     */
    @ApiModelProperty(
        value = "The Attribute value",
        required = true
    )
    @JsonProperty("Value")
    Object value;
    
    /**
     * The {@link Category} identifier
     */
    @ApiModelProperty(
    		value = "The Attribute assignment category (optional)",
    		example = "resource",
    		required = false
    		)
    @JsonProperty("Category")
    String category;
    
    /**
     * The {@code DataType} identifier
     */
    @ApiModelProperty(
    		value = "The Attribute assignment's datatype (optional)",
    		example = "string",
    		required = false
    )
    @JsonProperty("DataType")
    String dataType;

    /**
     * The {@link Attribute} issuer
     */
    @ApiModelProperty(value = "The Attribute issuer (optional)")
    @JsonProperty("Issuer")
    String issuer;

    public AttributeAssignment() {
    }

    public String getAttributeId() {
        return this.attributeId;
    }

    public Object getValue() {
        return this.value;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDataType() {
        return this.dataType;
    }

    public String getIssuer() {
        return this.issuer;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AttributeAssignment)) return false;
        final AttributeAssignment other = (AttributeAssignment) o;
        if (!Objects.equals(this.getAttributeId(), other.getAttributeId())) return false;
        if (!Objects.equals(this.getValue(), other.getValue())) return false;
        if (!Objects.equals(this.getCategory(), other.getCategory())) return false;
        if (!Objects.equals(this.getDataType(), other.getDataType())) return false;
        return Objects.equals(this.getIssuer(), other.getIssuer());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $attributeId = this.getAttributeId();
        result = result * PRIME + ($attributeId == null ? 43 : $attributeId.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final Object $dataType = this.getDataType();
        result = result * PRIME + ($dataType == null ? 43 : $dataType.hashCode());
        final Object $issuer = this.getIssuer();
        result = result * PRIME + ($issuer == null ? 43 : $issuer.hashCode());
        return result;
    }

    public String toString() {
        return "AttributeAssignment(attributeId=" + this.getAttributeId() + ", value=" + this.getValue() + ", category=" + this.getCategory() + ", dataType=" + this.getDataType() + ", issuer=" + this.getIssuer() + ")";
    }
}
