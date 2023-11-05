package com.beta.replyservice.service;

import com.beta.replyservice.model.ErrorMessage;
import com.beta.replyservice.model.Message;
import com.beta.replyservice.model.ReplyMessage;
import com.beta.replyservice.util.MessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class ReplyServiceImpl implements ReplyService {

    private static final String DELIMITER = "-";
    private static final String V1_FORMAT = "^[a-z0-9]*$";
    private static final String V2_FORMAT = "^[0-9][0-9]-[a-z0-9]*$";

    @Override
    public Message processMessageV1(String message) {
        if (message.isEmpty()) {
            return createEmptyMessage();
        }
        if (!message.matches(V1_FORMAT)) {
            return createInvalidMessage();
        }
        return new ReplyMessage(message);
    }

    @Override
    public Message processMessageV2(String message) {
        if (message.isEmpty()) {
            return createEmptyMessage();
        }
        if (!message.matches(V2_FORMAT)) {
            return createInvalidMessage();
        }

        String[] result = message.split(DELIMITER, 2);
        if (result.length < 2) {
            return createInvalidMessage();
        }
        String rule = result[0];
        if (rule.length() != 2) {
            return createInvalidMessage();
        }
        String body = result[1];
        for (char op : rule.toCharArray()) {
            switch(op) {
                case '1':
                    body = MessageUtils.reverse(body);
                    break;
                case '2':
                    body = MessageUtils.md5Hash(body);
                    break;
                default:
                    return createInvalidMessage();
            }
        }
        return new ReplyMessage(body);
    }

    private Message createEmptyMessage() {
        return new ReplyMessage("Message is empty");
    }

    private Message createInvalidMessage() {
        return new ErrorMessage("Invalid input", HttpStatus.BAD_REQUEST);
    }
}