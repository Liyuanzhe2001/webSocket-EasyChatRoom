package com.lkunk.websocket_springboot.mapper;

import com.lkunk.websocket_springboot.entity.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lkunk
 * @date 2023年07月02日 21:05
 * @description 消息记录mapper
 */
@Mapper
public interface RecordMapper {
    public List<Record> getRecord(Integer userId, Integer chatUserId);

    public int addRecord(Record record);
}
