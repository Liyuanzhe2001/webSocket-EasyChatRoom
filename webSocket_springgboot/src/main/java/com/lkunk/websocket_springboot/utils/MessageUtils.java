package com.lkunk.websocket_springboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkunk.websocket_springboot.pojo.ResultMessage;

/**
 * @description 用来封装消息的工具类
 */
public class MessageUtils {

    public static String getMessage(boolean isSystemMessage, Integer to, Object message) {
        try {
            ResultMessage result = new ResultMessage(isSystemMessage, to, message);

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
