package com.lkunk.websocket_springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 服务器发送给浏览器的websocket数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessage {

    /**
     * 是否为系统消息
     */
    private Boolean isSystem;

    /**
     * 发送方id
     */
    private Integer from;

    /**
     * 如果是系统消息是<K,V>
     */
    private Object  message;

}
