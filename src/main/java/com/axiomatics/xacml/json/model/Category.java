package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Category {

    @JsonProperty("CategoryId")
    String categoryId;

    @JsonProperty("Id")
    String id;

    @JsonProperty("Content")
    String content;
}
