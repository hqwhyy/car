package com.example.car.service;

import com.example.car.dto.CarParamTypeDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarParamType;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;

public interface CarParamTypeService {

    public DataGridResult findByPage(QueryDTO queryDTO);

    public R insert(CarParamTypeDTO carParamTypeDTO);

    public R deleCarParamTypeList(List<Long> ids);

    public R findCarParamTypeById(Integer id);

    public R updateCarParamType(CarParamType carParamType);
}
