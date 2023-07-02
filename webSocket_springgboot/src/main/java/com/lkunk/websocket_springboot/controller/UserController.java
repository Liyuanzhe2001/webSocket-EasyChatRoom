package com.lkunk.websocket_springboot.controller;

import com.lkunk.websocket_springboot.mapper.UserMapper;
import com.lkunk.websocket_springboot.pojo.Result;
import com.lkunk.websocket_springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session) {
        Result result = new Result();
        String password = userMapper.getPassword(user.getUsername());
        if (password.equals(user.getPassword())) {
            result.setFlag(true);
            //将用户名存储到session对象中
            session.setAttribute("user", user.getUsername());
        } else {
            result.setFlag(false);
            result.setMessage("登陆失败");
        }

        return result;
    }

    @GetMapping("/getUsername")
    public String getUsername(HttpSession session) {
        return (String) session.getAttribute("user");
    }


}
