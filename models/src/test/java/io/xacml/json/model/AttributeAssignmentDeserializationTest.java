package io.xacml.json.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class AttributeAssignmentDeserializationTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserialize_value_json_boolean() throws IOException {
        AttributeAssignment assignment = deserializeString("{ \"AttributeId\": \"id\", \"Value\": true, \"DataType\": \"boolean\" }");
        assertThat(assignment.getAttributeId(), equalTo("id"));
        assertThat(assignment.getValue(), equalTo(true));
        assertThat(assignment.getDataType(), equalTo("boolean"));
    }

    @Test
    public void deserialize_value_json_integer() throws IOException {
        AttributeAssignment assignment = deserializeString("{ \"AttributeId\": \"id\", \"Value\": 123, \"DataType\": \"integer\" }");
        assertThat(assignment.getAttributeId(), equalTo("id"));
        assertThat(assignment.getValue(), equalTo(123));
        assertThat(assignment.getDataType(), equalTo("integer"));
    }

    @Test
    public void deserialize_value_json_decimal() throws IOException {
        AttributeAssignment assignment = deserializeString("{ \"AttributeId\": \"id\", \"Value\": 1.23, \"DataType\": \"double\" }");
        assertThat(assignment.getAttributeId(), equalTo("id"));
        assertThat(assignment.getValue(), equalTo(1.23));
        assertThat(assignment.getDataType(), equalTo("double"));
    }

    @Test
    public void deserialize_value_json_string() throws IOException {
        AttributeAssignment assignment = deserializeString("{ \"AttributeId\": \"id\", \"Value\": \"doc123\", \"DataType\": \"string\" }");
        assertThat(assignment.getAttributeId(), equalTo("id"));
        assertThat(assignment.getValue(), equalTo("doc123"));
        assertThat(assignment.getDataType(), equalTo("string"));
        assertThat(assignment.getCategory(), nullValue());
        assertThat(assignment.getIssuer(), nullValue());
    }

    @Test
    public void deserialize_issuer() throws IOException {
        AttributeAssignment assignment = deserializeString("{ \"AttributeId\": \"id\", \"Value\": \"doc123\", \"DataType\": \"string\", \"Issuer\": \"issuer\" }");
        assertThat(assignment.getAttributeId(), equalTo("id"));
        assertThat(assignment.getValue(), equalTo("doc123"));
        assertThat(assignment.getDataType(), equalTo("string"));
        assertThat(assignment.getCategory(), nullValue());
        assertThat(assignment.getIssuer(), equalTo("issuer"));
    }

    private AttributeAssignment deserializeString(String source) throws IOException {
        return mapper.readValue(source, AttributeAssignment.class);
    }
}
