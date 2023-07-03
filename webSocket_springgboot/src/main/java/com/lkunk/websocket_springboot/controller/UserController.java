package com.lkunk.websocket_springboot.controller;

import com.lkunk.websocket_springboot.mapper.UserMapper;
import com.lkunk.websocket_springboot.entity.User;
import com.lkunk.websocket_springboot.pojo.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResultJson<User> login(@RequestBody User user, HttpSession session) {
        ResultJson<User> result = new ResultJson<>();
        String password = userMapper.getPassword(user.getUsername());
        if (password.equals(user.getPassword())) {
            Integer userId = userMapper.getUserId(user.getUsername());
            user.setId(userId);
            user.setPassword("");

            //将用户名存储到session对象中
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getId());

            result.setCode(200);
            result.setData(user);
        } else {
            result.setCode(500);
            result.setMessage("登陆失败");
        }

        return result;
    }

    @GetMapping("/getUsername")
    public String getUsername(HttpSession session) {
        return (String) session.getAttribute("user");
    }

    @GetMapping("/getUserId")
    public Integer getUserId(HttpSession session) {
        return (Integer) session.getAttribute("userId");
    }


}
