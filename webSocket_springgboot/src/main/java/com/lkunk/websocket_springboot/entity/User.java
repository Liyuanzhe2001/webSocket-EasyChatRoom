package com.lkunk.websocket_springboot.entity;

import lombok.Data;

/**
 * @author lkunk
 * @date 2023年06月29日 23:17
 * @description User实体类
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
}
