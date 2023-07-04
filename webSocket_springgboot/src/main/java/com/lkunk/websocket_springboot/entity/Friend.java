package com.lkunk.websocket_springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lkunk
 * @date 2023年07月03日 14:05
 * @description 好友实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    private Integer id;
    private Integer userId;
    private Integer friendId;
}
