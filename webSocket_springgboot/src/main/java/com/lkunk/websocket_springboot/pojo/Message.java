package com.lkunk.websocket_springboot.pojo;

import lombok.Data;

/**
 * @description 浏览器发送给服务器的websocket数据
 */
@Data
public class Message {
    private Integer toId;
    private String message;
}
