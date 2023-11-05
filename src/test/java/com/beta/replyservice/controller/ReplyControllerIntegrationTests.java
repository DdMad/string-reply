package com.beta.replyservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ReplyControllerIntegrationTests {

    public static final String MESSAGE_INVALID_INPUT = "{\"data\":\"Invalid input\",\"status\":\"BAD_REQUEST\"}";
    public static final String MESSAGE_EMPTY_INPUT = "{\"data\":\"Message is empty\"}";
    public static final String ENDPOINT_REPLY_V1_TEMPLATE = "/reply/{message}";
    public static final String ENDPOINT_REPLY_V2_TEMPLATE = "/v2/reply/{message}";

    @Autowired
    private MockMvc mvc;

    @Test
    public void testReplyingEmpty() throws Exception {
        mvc.perform(get("/reply").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(MESSAGE_EMPTY_INPUT));
    }

    @Test
    public void testReplying() throws Exception {
        mvc.perform(get(ENDPOINT_REPLY_V1_TEMPLATE, "helloworld").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"data\":\"helloworld\"}"));
    }

    @Test
    public void testReplyingV2Empty() throws Exception {
        mvc.perform(get("/v2/reply").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(MESSAGE_EMPTY_INPUT));
    }

    @Test
    public void testReplyingV2Invalid() throws Exception {
        mvc.perform(get(ENDPOINT_REPLY_V2_TEMPLATE, "13-helloworld").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(MESSAGE_INVALID_INPUT));
    }

    @Test
    public void testReplyingV2() throws Exception {
        mvc.perform(get(ENDPOINT_REPLY_V2_TEMPLATE, "12-helloworld").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"data\":\"49afed3c7cf18693ac7f319cd01ffae4\"}"));
    }
}
