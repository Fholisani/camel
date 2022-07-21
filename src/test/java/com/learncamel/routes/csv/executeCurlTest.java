package com.learncamel.routes.csv;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class executeCurlTest extends CamelTestSupport {
    @Override
    public RoutesBuilder createRouteBuilder() throws Exception {
        return new ExecuteCurl();
    }

    @Test
    public void curlRouteTest() throws Exception {
        template.sendBody("direct:exec", "hello");


    }
}
