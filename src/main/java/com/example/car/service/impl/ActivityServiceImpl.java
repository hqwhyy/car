package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.ActivityMapper;
import com.example.car.dto.ActivityDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.ActivityExample;
import com.example.car.pojo.ActivityWithBLOBs;
import com.example.car.service.ActivityService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.DateUtils;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        List<ActivityDTO> list = new ArrayList<>();
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(queryDTO.getSearch())){
            criteria.andAuthorLike("%"+queryDTO.getSearch()+"%");
        }

        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause("id"+queryDTO.getOrder());
        }
        List<ActivityWithBLOBs> activityWithBLOBs = activityMapper.selectByExampleWithBLOBs(example);
        //遍历使用DTO封装
        for (ActivityWithBLOBs activityWithBLOBs1:activityWithBLOBs) {
            ActivityDTO activityDTO = new ActivityDTO();
            activityDTO.setTitle(activityWithBLOBs1.getTitle());
            activityDTO.setDescription(activityWithBLOBs1.getDescription());
            activityDTO.setCoverPic(activityWithBLOBs1.getCoverPic());
            activityDTO.setAuthor(activityWithBLOBs1.getAuthor());
            String beginTime =  DateUtils.longToStr(activityWithBLOBs1.getBeginTime());
            activityDTO.setBeginTime(beginTime);
            String time2 = DateUtils.longToStr(activityWithBLOBs1.getEndTime());
            activityDTO.setEndTime(time2);
            activityDTO.setLink(activityWithBLOBs1.getLink());
            activityDTO.setSeoKeywords(activityWithBLOBs1.getSeoKeywords());
            list.add(activityDTO);
        }
        PageInfo<ActivityWithBLOBs> activityWithBLOBsPageInfo = new PageInfo<>(activityWithBLOBs);
        DataGridResult result = new DataGridResult();
        result.setTotal(activityWithBLOBsPageInfo.getTotal());

        result.setRows(list);
        return result;
    }

    @Override
    public R insert(ActivityDTO activityDTO) {
        ActivityWithBLOBs activityWithBLOBs = new ActivityWithBLOBs();
        activityWithBLOBs.setTitle(activityDTO.getTitle());
        activityWithBLOBs.setDescription(activityDTO.getDescription());
        activityWithBLOBs.setCoverPic(activityDTO.getCoverPic());
        activityWithBLOBs.setAuthor(activityDTO.getAuthor());
        Long time1 = null;
        try {
            time1 = DateUtils.strToLong(activityDTO.getBeginTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        activityWithBLOBs.setBeginTime(time1);

        Long time2 = null;
        try {
            time2 = DateUtils.strToLong(activityDTO.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        activityWithBLOBs.setEndTime(time2);

        
        activityWithBLOBs.setLink(activityDTO.getLink());
        activityWithBLOBs.setSeoDescription(activityDTO.getSeoKeywords());
        int i = activityMapper.insert(activityWithBLOBs);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("添加失败");
        }
    }
    @Override
    public R deleActivityList(List<Long> ids) {
        int i = activityMapper.deleActivityList(ids);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R findActivityById(Long id) {
        ActivityWithBLOBs activityWithBLOBs = activityMapper.selectByPrimaryKey(id);
        return R.ok().put("activity",activityWithBLOBs);
    }

    @Override
    public R updateActivity(ActivityWithBLOBs activityWithBLOBs) {
        int i = activityMapper.updateByPrimaryKeyWithBLOBs(activityWithBLOBs);
        return i>0?R.ok():R.error("修改失败");
    }
}
