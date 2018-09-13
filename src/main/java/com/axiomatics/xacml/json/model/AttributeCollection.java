package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Wrapper class composed of a collection of unique attributes
 */
@Getter
@EqualsAndHashCode
@ToString
@ApiModel("Wrapper class composed of a collection of unique attributes")
public class AttributeCollection {

    /**
     * A set of attributes that are provided in a {@link AuthorizeRequest}
     */
    @ApiModelProperty(value = "Set of attributes to be provided in request")
    @JsonProperty("Attribute")
    final Set<Attribute> attributes = new LinkedHashSet<>();

    public boolean add(Attribute attribute) {
        return attributes.add(attribute);
    }

    public boolean remove(Attribute attribute) {
        return attributes.remove(attribute);
    }

    public void clear() {
        attributes.clear();
    }

    public int size() {
        return attributes.size();
    }

//    public boolean isEmpty() {
//        return attributes.isEmpty();
//    }

    public boolean contains(Attribute attribute) {
        return attributes.contains(attribute);
    }

    public boolean containsAll(Collection<? extends Attribute> a) {
        return attributes.containsAll(a);
    }

    public boolean addAll(Collection<? extends Attribute> a) {
        return attributes.addAll(a);
    }

    public boolean retainAll(Collection<? extends Attribute> a) {
        return attributes.retainAll(a);
    }

    public boolean removeAll(Collection<? extends Attribute> a) {
        return attributes.removeAll(a);
    }

}
