package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.TagMapper;
import com.example.car.dto.QueryDTO;
import com.example.car.dto.TagDTO;
import com.example.car.pojo.Tag;
import com.example.car.pojo.TagExample;
import com.example.car.service.TagService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> findAll() {
        List<Tag> tags = tagMapper.selectByExample(null);
        return tags;
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        TagExample example = new TagExample();
        TagExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(queryDTO.getSearch())){
            criteria.andNameLike("%"+queryDTO.getSearch()+"%");
        }
        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause("id"+queryDTO.getOrder());
        }

        List<Tag> tags = tagMapper.selectByExample(example);
        PageInfo<Tag> tagPageInfo = new PageInfo<>(tags);
        DataGridResult result = new DataGridResult();
        result.setTotal(tagPageInfo.getTotal());
        result.setRows(tags);
        return result;
    }

    @Override
    public R insert(TagDTO tagDTO) {
        Tag record = new Tag();
        record.setName(tagDTO.getName());
        record.setClickCount(tagDTO.getClickCount());
        int i = tagMapper.insert(record);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R deleTagList(List<Long> ids) {
        int i = tagMapper.deleTagList(ids);
        if(i>0){
            return  R.ok();
        }else{
            return R.error(-90,"删除失败");
        }
    }

    @Override
    public R findTagById(Integer id) {
        Tag tag = tagMapper.selectByPrimaryKey(id);
        return R.ok().put("tag",tag);
    }

    @Override
    public R updateTag(Tag tag) {
        int i = tagMapper.updateByPrimaryKeySelective(tag);
        return i>0?R.ok():R.error("修改失败");
    }
}
