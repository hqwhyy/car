package com.example.car.action;

import com.example.car.dto.MessageDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.Message;
import com.example.car.service.MessageService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MessageAction {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/sys/message/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDTO queryDTO){
        return messageService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/message/save")
    @ResponseBody
    public R insertTag(@RequestBody MessageDTO messageDTO){
        return messageService.insert(messageDTO);
    }

    @RequestMapping("/sys/message/del")
    @ResponseBody
    public R deleTagList(@RequestBody List<Long> ids){
        return messageService.deleMessageList(ids);
    }

    @RequestMapping("/sys/message/info/{id}")
    @ResponseBody
    public R tagInfo(@PathVariable("id") Long id){
        return messageService.findMessageById(id);
    }

    @RequestMapping("/sys/message/update")
    @ResponseBody
    public R updateTag(@RequestBody Message message){
        return messageService.updateMessage(message);
    }
}
