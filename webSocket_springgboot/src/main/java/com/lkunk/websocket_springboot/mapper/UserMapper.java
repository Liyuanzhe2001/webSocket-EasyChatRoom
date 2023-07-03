package com.lkunk.websocket_springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public String getPassword(String username);

    public Integer getUserId(String username);

    public String getUserName(Integer userId);

}
