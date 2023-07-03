package com.lkunk.websocket_springboot.mapper;

import com.lkunk.websocket_springboot.entity.Friend;
import com.lkunk.websocket_springboot.entity.Record;
import com.lkunk.websocket_springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lkunk
 * @date 2023年07月02日 21:05
 * @description 好友mapper
 */
@Mapper
public interface FriendMapper {

    public List<User> getFriends(Integer userId);

}
