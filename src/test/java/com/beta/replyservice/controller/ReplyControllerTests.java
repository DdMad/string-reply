package com.beta.replyservice.controller;

import com.beta.replyservice.model.ErrorMessage;
import com.beta.replyservice.model.Message;
import com.beta.replyservice.model.ReplyMessage;
import com.beta.replyservice.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class ReplyControllerTests {
    
    @Mock
    private ReplyService service;

    @InjectMocks
    private ReplyController controller;
    
    @Test
    public void testReplyingEmpty() {
        String input = "";
        String expectedData = "Message is empty";
        Mockito.when(service.processMessageV1(input)).thenReturn(new ReplyMessage(expectedData));
        ResponseEntity<Message> result = controller.replying();
        assertEquals(expectedData, result.getBody().getData());
        Mockito.verify(service).processMessageV1(input);
    }

    @Test
    public void testReplying() {
        String input = "helloworld";
        String expectedData = "helloworld";
        Mockito.when(service.processMessageV1(input)).thenReturn(new ReplyMessage(expectedData));
        ResponseEntity<Message> result = controller.replying(input);
        assertEquals(expectedData, result.getBody().getData());
        Mockito.verify(service).processMessageV1(input);
    }

    @Test
    public void testReplyingV2Empty() {
        String input = "";
        String expectedData = "Message is empty";
        Mockito.when(service.processMessageV2(input)).thenReturn(new ReplyMessage(expectedData));
        ResponseEntity<Message> result = controller.replyingV2();
        assertEquals(expectedData, result.getBody().getData());
        Mockito.verify(service).processMessageV2(input);
    }

    @Test
    public void testReplyingV2Invalid() {
        String input = "13-helloworld";
        String expectedData = "Invalid input";
        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
        Mockito.when(service.processMessageV2(input)).thenReturn(new ErrorMessage(expectedData, expectedStatus));
        ResponseEntity<Message> result = controller.replyingV2(input);
        assertEquals(expectedData, result.getBody().getData());
        assertEquals(expectedStatus, ((ErrorMessage)result.getBody()).getStatus());
        Mockito.verify(service).processMessageV2(input);
    }

    @Test
    public void testReplyingV2() {
        String input = "12-helloworld";
        String expectedData = "49afed3c7cf18693ac7f319cd01ffae4";
        Mockito.when(service.processMessageV2(input)).thenReturn(new ReplyMessage(expectedData));
        ResponseEntity<Message> result = controller.replyingV2(input);
        assertEquals(expectedData, result.getBody().getData());
        Mockito.verify(service).processMessageV2(input);
    }
}
