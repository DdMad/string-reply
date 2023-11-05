package com.beta.replyservice.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReplyServiceImplTests {

    public static final String TEXT_EMPTY_INPUT = "Message is empty";
    public static final String TEXT_INVALID_INPUT = "Invalid input";
    private final ReplyServiceImpl service = new ReplyServiceImpl();

    @Test
    public void testProcessMessageV1() {
        // Empty input
        assertEquals(TEXT_EMPTY_INPUT, service.processMessageV1("").getData());

        //Invalid input
        assertEquals(TEXT_INVALID_INPUT, service.processMessageV2("helloworld+").getData());

        // Valid input
        assertEquals("helloworld", service.processMessageV1("helloworld").getData());
    }

    @Test
    public void testProcessMessageV2() {
        // Empty input
        assertEquals(TEXT_EMPTY_INPUT, service.processMessageV2("").getData());

        // Invalid input
        assertEquals(TEXT_INVALID_INPUT, service.processMessageV2("helloworld").getData());
        assertEquals(TEXT_INVALID_INPUT, service.processMessageV2("13-helloworld").getData());
        assertEquals(TEXT_INVALID_INPUT, service.processMessageV2("111-helloworld").getData());
        assertEquals(TEXT_INVALID_INPUT, service.processMessageV2("11-helloworld+").getData());

        // Valid input
        assertEquals("helloworld", service.processMessageV2("11-helloworld").getData());
        assertEquals("49afed3c7cf18693ac7f319cd01ffae4", service.processMessageV2("12-helloworld").getData());
        assertEquals("0b0107ef7e14458023075a83d830e5cf", service.processMessageV2("21-helloworld").getData());
        assertEquals("a11ee4c2150caf49670ad114b7fdc735", service.processMessageV2("22-helloworld").getData());
    }
}
