package io.xacml.json.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class AttributeDeserializationTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserialize_value_json_boolean() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"id\", \"Value\": true, \"DataType\": \"boolean\" }");
        assertThat(attribute.getAttributeId(), equalTo("id"));
        assertThat(attribute.getValue(), equalTo(true));
        assertThat(attribute.getDataType(), equalTo("boolean"));
    }

    @Test
    public void deserialize_value_json_integer() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"id\", \"Value\": 123, \"DataType\": \"integer\" }");
        assertThat(attribute.getAttributeId(), equalTo("id"));
        assertThat(attribute.getValue(), equalTo(123));
        assertThat(attribute.getDataType(), equalTo("integer"));
    }

    @Test
    public void deserialize_value_json_decimal() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"id\", \"Value\": 1.23, \"DataType\": \"double\" }");
        assertThat(attribute.getAttributeId(), equalTo("id"));
        assertThat(attribute.getValue(), equalTo(1.23));
        assertThat(attribute.getDataType(), equalTo("double"));
    }

    @Test
    public void deserialize_value_json_string() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"id\", \"Value\": \"doc123\", \"DataType\": \"string\" }");
        assertThat(attribute.getAttributeId(), equalTo("id"));
        assertThat(attribute.getValue(), equalTo("doc123"));
        assertThat(attribute.getDataType(), equalTo("string"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deserialize_value_array_single_json_boolean() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"active\", \"Value\": [true], \"DataType\": \"boolean\" }");
        assertThat(attribute.getAttributeId(), equalTo("active"));
        assertThat((List<Object>) attribute.getValue(), contains(true));
        assertThat(attribute.getDataType(), equalTo("boolean"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deserialize_value_array_single_json_integer() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"id\", \"Value\": [123], \"DataType\": \"integer\" }");
        assertThat(attribute.getAttributeId(), equalTo("id"));
        assertThat((List<Object>) attribute.getValue(), contains(123));
        assertThat(attribute.getDataType(), equalTo("integer"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deserialize_value_array_single_json_decimal() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"id\", \"Value\": [1.23], \"DataType\": \"double\" }");
        assertThat(attribute.getAttributeId(), equalTo("id"));
        assertThat((List<Object>) attribute.getValue(), contains(1.23));
        assertThat(attribute.getDataType(), equalTo("double"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deserialize_value_array_single_json_string() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"id\", \"Value\": [\"doc123\"], \"DataType\": \"string\" }");
        assertThat(attribute.getAttributeId(), equalTo("id"));
        assertThat((List<Object>) attribute.getValue(), contains("doc123"));
        assertThat(attribute.getDataType(), equalTo("string"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deserialize_value_array_many_json_boolean() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"active\", \"Value\": [true, false], \"DataType\": \"boolean\" }");
        assertThat(attribute.getAttributeId(), equalTo("active"));
        assertThat((List<Object>) attribute.getValue(), contains(true, false));
        assertThat(attribute.getDataType(), equalTo("boolean"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deserialize_value_array_many_json_integer() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"id\", \"Value\": [123, 456], \"DataType\": \"integer\" }");
        assertThat(attribute.getAttributeId(), equalTo("id"));
        assertThat((List<Object>) attribute.getValue(), contains(123, 456));
        assertThat(attribute.getDataType(), equalTo("integer"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deserialize_value_array_many_json_decimal() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"id\", \"Value\": [1.23, 4.56], \"DataType\": \"double\" }");
        assertThat(attribute.getAttributeId(), equalTo("id"));
        assertThat((List<Object>) attribute.getValue(), contains(1.23, 4.56));
        assertThat(attribute.getDataType(), equalTo("double"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deserialize_value_array_many_json_string() throws IOException {
        Attribute attribute = deserializeString("{ \"AttributeId\": \"id\", \"Value\": [\"doc123\",\"doc456\"], \"DataType\": \"string\" }");
        assertThat(attribute.getAttributeId(), equalTo("id"));
        assertThat((List<Object>) attribute.getValue(), contains("doc123", "doc456"));
        assertThat(attribute.getDataType(), equalTo("string"));
    }

    private Attribute deserializeString(String source) throws IOException {
        return mapper.readValue(source, Attribute.class);
    }
}
