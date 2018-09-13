package com.axiomatics.xacml.json.model;

import org.json.JSONObject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
@SuppressWarnings("static-method") 
public class RequestTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RequestTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RequestTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        Request request = new Request();
        JSONObject jo = new JSONObject(request);
        System.out.println(jo.toString());
        assertTrue(true);
    }
}
