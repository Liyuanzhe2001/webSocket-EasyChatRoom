package com.lkunk.websocket_springboot.controller;

import com.lkunk.websocket_springboot.entity.User;
import com.lkunk.websocket_springboot.mapper.FriendMapper;
import com.lkunk.websocket_springboot.pojo.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lkunk
 * @date 2023年07月03日 14:07
 * @description 好友控制层
 */
@RestController
public class FriendController {

    @Autowired
    private FriendMapper friendMapper;

    @GetMapping("/friends")
    public ResultJson<List<User>> getFriends(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        List<User> friends = friendMapper.getFriends(userId);
        return new ResultJson<>(200, friends, "");
    }

}
