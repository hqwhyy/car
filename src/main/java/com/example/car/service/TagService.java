package com.example.car.service;

import com.example.car.dto.QueryDTO;
import com.example.car.dto.TagDTO;
import com.example.car.pojo.Tag;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;

public interface TagService {

    public List<Tag> findAll();

    public DataGridResult findByPage(QueryDTO queryDTO);

    public R insert(TagDTO tagDTO);

    public R deleTagList(List<Long> ids);

    public R findTagById(Integer id);

    public R updateTag(Tag tag);
}
