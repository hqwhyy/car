package com.example.car.service;

import com.example.car.dto.CarParamsDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarParams;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;


public interface CarParamsService {

    public DataGridResult findByPage(QueryDTO queryDTO);

    public R insert(CarParamsDTO carParamsDTO);

    public R typeall();

    public R deleCarParamsList(List<Long> ids);

    public R findCarParamsById(Integer id);

    public R updateCarParams(CarParams carParams);

}
