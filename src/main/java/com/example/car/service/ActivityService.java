package com.example.car.service;

import com.example.car.dto.ActivityDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.ActivityWithBLOBs;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;

public interface ActivityService {

    public DataGridResult findByPage(QueryDTO queryDTO);

    public R insert(ActivityDTO activityDTO);

    public R deleActivityList(List<Long> ids);

    public R findActivityById(Long id);

    public R updateActivity(ActivityWithBLOBs activityWithBLOBs);
}
