package com.lkunk.websocket_springboot.entity;

import lombok.Data;

/**
 * @author lkunk
 * @date 2023年07月02日 21:01
 * @description 消息记录实体类
 */
@Data
public class Record {
    private Integer id;
    private Integer from;
    private Integer to;
    private String content;
}
