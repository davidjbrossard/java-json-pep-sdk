package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

public class ObligationOrAdvice {

    /**
     * Obligation or Advice identifier.
     */
    @ApiModelProperty(value = "Obligation or Advice identifier.")
    @JsonProperty("Id")
    String id;

    /**
     * A {@link AttributeAssignment} is used for including arguments in obligation and advice expressions.  It SHALL contain a
     * {@link AttributeAssignment#attributeId} and the corresponding {@link AttributeAssignment#value}.
     */
    @ApiModelProperty(value = "Used for including arguments in obligation and advice expressions")
    @JsonProperty("AttributeAssignment")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<AttributeAssignment> attributeAssignments;

    public ObligationOrAdvice() {
    }

    public String getId() {
        return this.id;
    }

    public List<AttributeAssignment> getAttributeAssignments() {
        return this.attributeAssignments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAttributeAssignments(List<AttributeAssignment> attributeAssignments) {
        this.attributeAssignments = attributeAssignments;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ObligationOrAdvice)) return false;
        final ObligationOrAdvice other = (ObligationOrAdvice) o;
        if (!Objects.equals(this.getId(), other.getId())) return false;
        return Objects.equals(this.getAttributeAssignments(), other.getAttributeAssignments());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $attributeAssignments = this.getAttributeAssignments();
        result = result * PRIME + ($attributeAssignments == null ? 43 : $attributeAssignments.hashCode());
        return result;
    }

    public String toString() {
        return "ObligationOrAdvice(id=" + this.getId() + ", attributeAssignments=" + this.getAttributeAssignments() + ")";
    }
}
