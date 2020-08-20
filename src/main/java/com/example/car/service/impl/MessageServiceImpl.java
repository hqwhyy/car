package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.MessageMapper;
import com.example.car.dto.MessageDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.*;
import com.example.car.service.MessageService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        MessageExample example = new MessageExample();
        MessageExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(queryDTO.getSearch())){
            criteria.andLinkLike("%"+queryDTO.getSearch()+"%");
        }

        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause("id"+queryDTO.getOrder());
        }
        List<Message> messages = messageMapper.selectByExampleWithBLOBs(example);
        PageInfo<Message> messagePageInfo = new PageInfo<>(messages);
        DataGridResult result = new DataGridResult();
        result.setTotal(messagePageInfo.getTotal());
        result.setRows(messages);
        return result;
    }

    @Override
    public R insert(MessageDTO messageDTO) {
        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setLink(messageDTO.getLink());
        int i = messageMapper.insert(message);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R deleMessageList(List<Long> ids) {
        int i = messageMapper.deleMessageList(ids);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R findMessageById(Long id) {
        Message message = messageMapper.selectByPrimaryKey(id);
        return R.ok().put("message",message);
    }

    @Override
    public R updateMessage(Message message) {
        int i = messageMapper.updateByPrimaryKeySelective(message);
        return i>0?R.ok():R.error("修改失败");
    }
}
