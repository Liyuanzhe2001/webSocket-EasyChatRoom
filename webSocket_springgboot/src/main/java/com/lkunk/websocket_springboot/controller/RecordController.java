package com.lkunk.websocket_springboot.controller;

import com.lkunk.websocket_springboot.entity.Record;
import com.lkunk.websocket_springboot.mapper.RecordMapper;
import com.lkunk.websocket_springboot.pojo.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lkunk
 * @date 2023年07月03日 13:36
 * @description 消息记录实体类
 */
@RestController
public class RecordController {

    @Autowired
    private RecordMapper recordMapper;

    @GetMapping("/record/{chatUserId}")
    public ResultJson<List<Record>> getRecord(@PathVariable("chatUserId") Integer chatUserId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        List<Record> record = recordMapper.getRecord(userId, chatUserId);
        return new ResultJson<>(200, record, "");
    }

}
