package io.xacml.json.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JsonSerializationTest {
    private final ClassLoader classLoader = getClass().getClassLoader();
    private final ObjectMapper mapper =
            new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

    @Test
    public void serialize_attribute_integer() throws IOException {
        Category category = new Category();
        category.addAttribute("document-id", 123, "integer");

        JsonNode serialized = serialize(category);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/attribute-integer.json")));
    }

    @Test
    public void serialize_attribute_integer_inferred() throws IOException {
        Category category = new Category();
        category.addAttribute("document-id", 123);

        JsonNode serialized = serialize(category);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/attribute-integer-inferred.json")));
    }

    @Test
    public void serialize_attribute_list_string() throws IOException {
        Category category = new Category();
        category.addAttribute("urn:oasis:names:tc:xacml:2.0:subject:role", new String[]{"manager", "administrator"});

        JsonNode serialized = serialize(category);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/attribute-list-string.json")));
    }

    @Test
    public void serialize_attribute_xpath() throws IOException {

        XPathExpression xPathExpression = new XPathExpression("urn:oasis:names:tc:xacml:3.0:attribute-category:resource", "md:record/md:patient/md:patientDoB");
        xPathExpression.addNamespaceDeclaration("urn:oasis:names:tc:xacml:3.0:core:schema:wd-17");
        xPathExpression.addNamespaceDeclaration("urn:example:med:schemas:record", "md");

        Category category = new Category();
        category.addAttribute("urn:oasis:names:tc:xacml:3.0:content-selector", xPathExpression, "xpathExpression");

        JsonNode serialized = serialize(category);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/attribute-xpath.json")));
    }

    @Test
    public void serialize_multirequests_object() throws IOException {
        List<String> requestReferenceIds0 = new ArrayList<>();
        requestReferenceIds0.add("foo1");
        requestReferenceIds0.add("bar1");

        List<String> requestReferenceIds1 = new ArrayList<>();
        requestReferenceIds1.add("foo2");
        requestReferenceIds1.add("bar1");

        List<String> requestReferenceIds2 = new ArrayList<>();
        requestReferenceIds2.add("foo3");
        requestReferenceIds2.add("bar1");

        MultiRequests multiRequests = new MultiRequests();
        multiRequests.addRequestReferenceWithIds(requestReferenceIds0);
        multiRequests.addRequestReferenceWithIds(requestReferenceIds1);
        multiRequests.addRequestReferenceWithIds(requestReferenceIds2);

        JsonNode serialized = serialize(multiRequests);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/multirequests.json")));
    }

    @Test
    public void serialize_request() throws IOException {
        Request request = new Request();

        Category accessSubjectCategory = new Category();
        accessSubjectCategory.addAttribute("subject-id", "Andreas");
        accessSubjectCategory.addAttribute("location", "Gamla Stan");

        Category actionCategory = new Category();
        actionCategory.addAttribute("action-id", "http://example.com/buy", "anyURI");

        Category resourceCategory = new Category();
        resourceCategory.addAttribute("book-title", "Learn German in 90 days");
        resourceCategory.addAttribute("currency", "SEK");
        resourceCategory.addAttribute("price", 123.34);

        request.addAccessSubjectCategory(accessSubjectCategory);
        request.addActionCategory(actionCategory);
        request.addResourceCategory(resourceCategory);

        JsonNode serialized = serialize(request);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/request.json")));
    }

    @Test
    public void serialize_request_with_base64_encoded_content() throws IOException {
        Request request = new Request();

        Category accessSubjectCategory = new Category();
        accessSubjectCategory.setContent("PD94bWwgdmVyc2lvbj0iMS4wIj8+DQo8Y2F0YWxvZz48Ym9vayBpZD0iYmsxMDEiPjxhdXRob3I+R2FtYmFyZGVsbGEsIE1hdHRoZXc8L2F1dGhvcj48dGl0bGU+WE1MIERldmVsb3BlcidzIEd1aWRlPC90aXRsZT48Z2VucmU+Q29tcHV0ZXI8L2dlbnJlPjxwcmljZT40NC45NTwvcHJpY2U+PHB1Ymxpc2hfZGF0ZT4yMDAwLTEwLTAxPC9wdWJsaXNoX2RhdGU+PGRlc2NyaXB0aW9uPkFuIGluLWRlcHRoIGxvb2sgYXQgY3JlYXRpbmcgYXBwbGljYXRpb25zIHdpdGggWE1MLjwvZGVzY3JpcHRpb24+PC9ib29rPjwvY2F0YWxvZz4=");
        request.addAccessSubjectCategory(accessSubjectCategory);

        JsonNode serialized = serialize(request);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/request-with-base64-encoded-content.json")));
    }

    @Test
    public void serialize_request_with_category() throws IOException {
        Request request = new Request();

        Category customCategory0 = new Category();
        customCategory0.setCategoryId("custom-category");
        customCategory0.addAttribute("some-custom-id", "value");

        Category customCategory1 = new Category();
        customCategory1.setCategoryId("another-custom-cat");
        customCategory1.addAttribute("some-custom-id-2", "value2");

        Category accessSubjectCategory = new Category();
        accessSubjectCategory.addAttribute("subject-id", "Andreas");
        accessSubjectCategory.addAttribute("location", "Gamla Stan");

        Category actionCategory0 = new Category();
        actionCategory0.addAttribute("action-id", "http://example.com/buy", "anyURI");

        Category actionCategory1 = new Category();
        actionCategory1.addAttribute("action-id-2", "http://example.com/sell", "anyURI");

        request.addCustomCategory(customCategory0);
        request.addCustomCategory(customCategory1);
        request.addAccessSubjectCategory(accessSubjectCategory);
        request.addActionCategory(actionCategory0);
        request.addActionCategory(actionCategory1);

        JsonNode serialized = serialize(request);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/request-with-category.json")));
    }

    @Test
    public void serialize_request_with_escaped_xml_content() throws IOException {
        Request request = new Request();

        Category accessSubjectCategory = new Category();
        accessSubjectCategory.setContent("<?xml version=\"1.0\"?><catalog><book id=\"bk101\"><author>Gambardella, Matthew</author><title>XML Developer's Guide</title><genre>Computer</genre><price>44.95</price><publish_date>2000-10-01</publish_date><description>An in-depth look at creating applications with XML.</description></book></catalog>");
        request.addAccessSubjectCategory(accessSubjectCategory);

        JsonNode serialized = serialize(request);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/request-with-escaped-xml-content.json")));
    }

    @Test
    public void serialize_request_with_multi_decisions() throws IOException {
        Request request = new Request();

        Category accessSubjectCategory = new Category("s1");
        accessSubjectCategory.addAttribute("com.acme.user.employeeId", "Alice");

        Category resourceCategory0 = new Category("r1");
        resourceCategory0.addAttribute("com.acme.object.objectType", "record");
        resourceCategory0.addAttribute("com.acme.record.recordId", "126", true);

        Category resourceCategory1 = new Category("r2");
        resourceCategory1.addAttribute("com.acme.object.objectType", "record");
        resourceCategory1.addAttribute("com.acme.record.recordId", "125", true);

        Category actionCategory0 = new Category("a1");
        actionCategory0.addAttribute("com.acme.action.actionId", "view", true);

        Category actionCategory1 = new Category("a2");
        actionCategory1.addAttribute("com.acme.action.actionId", "edit", true);

        Category actionCategory2 = new Category("a3");
        actionCategory2.addAttribute("com.acme.action.actionId", "delete", true);

        List<String> requestReferenceIds0 = new ArrayList<>();
        requestReferenceIds0.add("s1");
        requestReferenceIds0.add("a1");
        requestReferenceIds0.add("r1");

        List<String> requestReferenceIds1 = new ArrayList<>();
        requestReferenceIds1.add("s1");
        requestReferenceIds1.add("a2");
        requestReferenceIds1.add("r1");

        MultiRequests multiRequests = new MultiRequests();
        multiRequests.addRequestReferenceWithIds(requestReferenceIds0);
        multiRequests.addRequestReferenceWithIds(requestReferenceIds1);

        request.addAccessSubjectCategory(accessSubjectCategory);
        request.addResourceCategory(resourceCategory0);
        request.addResourceCategory(resourceCategory1);
        request.addActionCategory(actionCategory0);
        request.addActionCategory(actionCategory1);
        request.addActionCategory(actionCategory2);
        request.setMultiRequests(multiRequests);

        JsonNode serialized = serialize(request);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/request-with-multi-decisions.json")));
    }

    @Test
    public void serialize_request_xpath() throws IOException {
        Request request = new Request();
        request.setXpathVersion("http://www.w3.org/TR/1999/REC-xpath-19991116");
        Category customCategory0 = new Category();
        customCategory0.setCategoryId("custom-category");
        customCategory0.addAttribute("some-custom-id", "value");
        request.addCustomCategory(customCategory0);

        JsonNode serialized = serialize(request);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/request-xpath.json")));
    }

    @Test
    public void serialize_response() throws IOException {
        List<Result> results = new ArrayList<>();

        Result result = new Result();
        result.setDecision(PDPDecision.PERMIT);
        results.add(result);

        Response response = new Response();
        response.setResults(results);

        JsonNode serialized = serialize(response);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/response.json")));
    }

    @Test
    public void serialize_response_with_multi_decisions() throws IOException {

        Status result0Status = new Status();
        result0Status.setStatusCode(new StatusCode("urn:oasis:names:tc:xacml:1.0:status:ok", new StatusCode("urn:oasis:names:tc:xacml:1.0:status:ok")));

        List<Category> result0Categories = new ArrayList<>();

        Category result0Category0 = new Category();
        result0Category0.setCategoryId("urn:oasis:names:tc:xacml:3.0:attribute-category:resource");
        result0Category0.addAttribute("com.acme.record.recordId", "126", "http://www.w3.org/2001/XMLSchema#string");
        result0Categories.add(result0Category0);

        Category result0Category1 = new Category();
        result0Category1.setCategoryId("urn:oasis:names:tc:xacml:3.0:attribute-category:action");
        result0Category1.addAttribute("com.acme.action.actionId", "view", "http://www.w3.org/2001/XMLSchema#string");
        result0Categories.add(result0Category1);

        Result result0 = new Result();
        result0.setDecision(PDPDecision.DENY);
        result0.setStatus(result0Status);
        result0.setCategories(result0Categories);

        Status result1Status = new Status();
        result1Status.setStatusCode(new StatusCode("urn:oasis:names:tc:xacml:1.0:status:ok", new StatusCode("urn:oasis:names:tc:xacml:1.0:status:ok")));

        List<Category> result1Categories = new ArrayList<>();

        Category result1Category0 = new Category();
        result1Category0.setCategoryId("urn:oasis:names:tc:xacml:3.0:attribute-category:resource");
        result1Category0.addAttribute("com.acme.record.recordId", "126", "http://www.w3.org/2001/XMLSchema#string");
        result1Categories.add(result1Category0);

        Category result1Category1 = new Category();
        result1Category1.setCategoryId("urn:oasis:names:tc:xacml:3.0:attribute-category:action");
        result1Category1.addAttribute("com.acme.action.actionId", "edit", "http://www.w3.org/2001/XMLSchema#string");
        result1Categories.add(result1Category1);

        Result result1 = new Result();
        result1.setDecision(PDPDecision.DENY);
        result1.setStatus(result1Status);
        result1.setCategories(result1Categories);

        List<Result> results = new ArrayList<>();
        results.add(result0);
        results.add(result1);

        Response response = new Response();
        response.setResults(results);

        JsonNode serialized = serialize(response);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/response-with-multi-decisions.json")));
    }

    @Test
    public void serialize_response_with_status_code() throws IOException {
        List<Result> results = new ArrayList<>();

        Status status = new Status();
        status.setStatusCode(new StatusCode("http://example.com"));

        Result result = new Result();
        result.setDecision(PDPDecision.PERMIT);
        result.setStatus(status);
        results.add(result);

        Response response = new Response();
        response.setResults(results);

        JsonNode serialized = serialize(response);

        assertThat(serialized, equalTo(expectedJsonValue("json-examples/response-with-status-code.json")));
    }

    private JsonNode serialize(Object value) {
        return mapper.convertValue(value, JsonNode.class);
    }

    private JsonNode expectedJsonValue(String source) throws IOException {
        return mapper.readValue(classLoader.getResourceAsStream(source), JsonNode.class);
    }
}
