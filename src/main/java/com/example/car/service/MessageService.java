package com.example.car.service;

import com.example.car.dto.MessageDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.Message;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;

public interface MessageService {
    public DataGridResult findByPage(QueryDTO queryDTO);

    public R insert(MessageDTO messageDTO);

    public R deleMessageList(List<Long> ids);

    public R findMessageById(Long id);

    public R updateMessage(Message message);
}
