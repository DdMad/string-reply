package com.beta.replyservice.service;

import com.beta.replyservice.model.Message;


public interface ReplyService {

    Message processMessageV1(String message);

    Message processMessageV2(String message);
}
