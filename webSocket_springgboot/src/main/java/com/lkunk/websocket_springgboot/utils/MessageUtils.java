package com.lkunk.websocket_springgboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkunk.websocket_springgboot.pojo.ResultMessage;

/**
 * @description 用来封装消息的工具类
 */
public class MessageUtils {

    public static String getMessage(boolean isSystemMessage, String fromName, Object message) {
        try {
            ResultMessage result = new ResultMessage();
            result.setIsSystem(isSystemMessage);
            result.setMessage(message);
            if (fromName != null) {
                result.setFromName(fromName);
            }
            ObjectMapper mapper = new ObjectMapper();

            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
