package com.lkunk.websocket_springboot.entity;

import lombok.Data;

/**
 * @author lkunk
 * @date 2023年07月03日 14:05
 * @description 好友实体类
 */
@Data
public class Friend {
    private Integer id;
    private Integer userId;
    private Integer friendId;
}
