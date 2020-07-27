package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Conveys information about attributes required for policy evaluation that were missing from the request context.
 */
@ApiModel("Conveys information about attributes required for policy evaluation that were missing from the request context")
public class MissingAttributeDetail {

    /**
     * The identifier of the missing attribute
     */
    @ApiModelProperty(
        value = "The identifier of the missing attribute"
    )
    @JsonProperty("AttributeId")
    String attributeId;

    /**
     *
     */
    @ApiModelProperty(
        value = ""
    )
    @JsonProperty("Value")
    String value;

    /**
     * This attribute, if supplied, SHALL specify the required Issuer of the missing attribute
     */
    @ApiModelProperty(
        value = "This attribute, if supplied, SHALL specify the required Issuer of the missing attribute"
    )
    @JsonProperty("Issuer")
    String issuer;

    /**
     * The data-type of the missing attribute
     */
    @ApiModelProperty(
        value = "The data-type of the missing attribute"
    )
    @JsonProperty("DataType")
    String dataType;

    /**
     * The category identifier of the missing attribute
     */
    @ApiModelProperty(
        value = "The category identifier of the missing attribute"
    )
    @JsonProperty("Category")
    String category;

    public MissingAttributeDetail() {
    }

    public String getAttributeId() {
        return this.attributeId;
    }

    public String getValue() {
        return this.value;
    }

    public String getIssuer() {
        return this.issuer;
    }

    public String getDataType() {
        return this.dataType;
    }

    public String getCategory() {
        return this.category;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MissingAttributeDetail)) return false;
        final MissingAttributeDetail other = (MissingAttributeDetail) o;
        if (!Objects.equals(this.getAttributeId(), other.getAttributeId())) return false;
        if (!Objects.equals(this.getValue(), other.getValue())) return false;
        if (!Objects.equals(this.getIssuer(), other.getIssuer())) return false;
        if (!Objects.equals(this.getDataType(), other.getDataType())) return false;
        return Objects.equals(this.getCategory(), other.getCategory());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $attributeId = this.getAttributeId();
        result = result * PRIME + ($attributeId == null ? 43 : $attributeId.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $issuer = this.getIssuer();
        result = result * PRIME + ($issuer == null ? 43 : $issuer.hashCode());
        final Object $dataType = this.getDataType();
        result = result * PRIME + ($dataType == null ? 43 : $dataType.hashCode());
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        return result;
    }

    public String toString() {
        return "MissingAttributeDetail(attributeId=" + this.getAttributeId() + ", value=" + this.getValue() + ", issuer=" + this.getIssuer() + ", dataType=" + this.getDataType() + ", category=" + this.getCategory() + ")";
    }
}
