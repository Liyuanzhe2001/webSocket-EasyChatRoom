package com.lkunk.websocket_springgboot.controller;

import com.lkunk.websocket_springgboot.pojo.Result;
import com.lkunk.websocket_springgboot.pojo.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session) {
        Result result = new Result();
        if (user != null && "123".equals(user.getPassword())) {
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
