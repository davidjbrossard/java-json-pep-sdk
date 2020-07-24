package io.xacml.json.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CategoryDeserializationTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserialize_empty() throws IOException {
        Category category = deserializeString("{ }");
        assertThat(category.getCategoryId(), nullValue());
        assertThat(category.getId(), nullValue());
        assertThat(category.getContent(), nullValue());
        assertThat(category.getAttributes(), empty());
    }

    @Test
    public void deserialize_with_category_identifier() throws IOException {
        Category category = deserializeString("{ \"CategoryId\": \"category\" }");
        assertThat(category.getCategoryId(), equalTo("category"));
    }

    @Test
    public void deserialize_with_identifier() throws IOException {
        Category category = deserializeString("{ \"Id\": \"id\" }");
        assertThat(category.getId(), equalTo("id"));
    }

    @Test
    public void deserialize_with_escaped_xml_content() throws IOException {
        Category category = deserializeString("{ \"Content\": \"<?xml version=\\\"1.0\\\"?><catalog><book id=\\\"bk101\\\"></book></catalog>\" }");
        assertThat(category.getContent(), equalTo("<?xml version=\"1.0\"?><catalog><book id=\"bk101\"></book></catalog>"));
    }

    @Test
    public void deserialize_with_empty_attributes() throws IOException {
        Category category = deserializeString("{ \"Attribute\": [] }");
        assertThat(category.getAttributes(), hasSize(0));
    }

    @Test
    public void deserialize_with_profile_10_single_attribute_object() throws IOException {
        Category category = deserializeString("{ \"Attribute\": {\"AttributeId\": \"id\", \"Value\": true} }");
        assertThat(category.getAttributes(), hasSize(1));
    }

    @Test
    public void deserialize_with_profile_11_single_attribute_array() throws IOException {
        Category category = deserializeString("{ \"Attribute\": [ {\"AttributeId\": \"id\", \"Value\": true} ] }");
        assertThat(category.getAttributes(), hasSize(1));
    }

    @Test
    public void deserialize_with_multiple_attributes() throws IOException {
        Category category = deserializeString("{ \"Attribute\": [{\"AttributeId\": \"id\", \"Value\": true}, {\"AttributeId\": \"id2\", \"Value\": 5}] }");
        assertThat(category.getAttributes(), hasSize(2));
    }

    private Category deserializeString(String source) throws IOException {
        return mapper.readValue(source, Category.class);
    }
}
