package com.beta.replyservice.util;

import org.apache.commons.codec.digest.DigestUtils;


public class MessageUtils {
    public static String md5Hash(String text) {
        return DigestUtils.md5Hex(text);
    }

    public static String reverse(String text) {
        return new StringBuffer(text).reverse().toString();
    }
}