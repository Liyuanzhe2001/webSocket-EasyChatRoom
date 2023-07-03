package com.lkunk.websocket_springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lkunk
 * @date 2023年07月03日 13:58
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultJson<T> {
    private Integer code;
    private T data;
    private String message;
}
