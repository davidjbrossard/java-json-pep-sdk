package com.axiomatics.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 */
public class JsonTest {

    private ObjectMapper mapper;

    @Before
    public void before() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /*
        {
            "Request": {
                "AccessSubject": {
                    "Attribute": [
                        {
                            "AttributeId": "subject-id",
                            "Value": "Andreas"
                        },
                        {
                            "AttributeId": "location",
                            "Value": "Gamla Stan"
                        }
                    ]
                },
                "Action": {
                    "Attribute": [
                        {
                            "AttributeId": "action-id",
                            "DataType": "anyURI",
                            "Value": "http://example.com/buy"
                        }
                    ]
                },
                "Resource": {
                    "Attribute": [
                        {
                            "AttributeId": "book-title",
                            "Value": "Learn German in 90 days"
                        },
                        {
                            "AttributeId": "currency",
                            "Value": "SEK"
                        },
                        {
                            "AttributeId": "price",
                            "Value": 123.34
                        }
                    ]
                }
            }
        }
     */
    @Test
    public void testRequestJsonFormat() throws JsonProcessingException {
        AuthorizeRequest request = new AuthorizeRequest();

        AttributeCollection accessSubjectAttributes = new AttributeCollection();
        List<String> nickNames = new ArrayList<>();
        nickNames.add("Andy");
        nickNames.add("Dre");

        accessSubjectAttributes.add(new Attribute("subject-id", "Andreas"));
        accessSubjectAttributes.add(new Attribute("location", "Gamla Stan"));
        accessSubjectAttributes.add(new Attribute("nicknames", nickNames));

        AttributeCollection actionAttributes = new AttributeCollection();
        actionAttributes.add(new Attribute("action-id", "http://example.com/buy", "anyURI"));

        AttributeCollection resourceAttributes = new AttributeCollection();
        resourceAttributes.add(new Attribute("book-title", "Learn German in 90 days"));
        resourceAttributes.add(new Attribute("currency", "SEK"));
        resourceAttributes.add(new Attribute("price", 123.34));

        request.accessSubjectAttributes = accessSubjectAttributes;
        request.actionAttributes = actionAttributes;
        request.resourceAttributes = resourceAttributes;

        String jsonString = mapper.writeValueAsString(request);
        System.out.println(jsonString);
        assertTrue(true);
    }

    /**
     * From {@link http://docs.oasis-open.org/xacml/xacml-json-http/v1.0/xacml-json-http-v1.0.html} - Table 4
     * <p>
     * Tests that Attribute json matches the following:
     * <p>
     * {
     * "Attribute": {
     * "AttributeId"   :   "document-id",
     * "DataType"      :   "integer",
     * "Value"         :   123
     * }
     * }
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testAttributeJsonFormat() throws JsonProcessingException {
        Attribute attribute = new Attribute("document-id", 123, "integer");

        String jsonString = mapper.writeValueAsString(attribute);
        System.out.println(jsonString);

        assertTrue(true);
    }
}
