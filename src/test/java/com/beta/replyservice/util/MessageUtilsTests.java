package com.beta.replyservice.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MessageUtilsTests {
    @Test
    public void testMd5Hash() {
        assertEquals("fc5e038d38a57032085441e7fe7010b0", MessageUtils.md5Hash("helloworld"));
    }

    @Test
    public void testReverse() {
        assertEquals("dlrowolleh", MessageUtils.reverse("helloworld"));
    }
}
