package com.example.car.service;

import com.example.car.dto.CarModelDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarModel;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;

public interface CarModelService {

    public DataGridResult findByPage(QueryDTO queryDTO);

    public R insert(CarModelDTO carModelDTO);

    public R typeall();

    public R deleCarModelList(List<Long> ids);

    public R findCarModelById(Integer id);

    public R updateCarModel(CarModel carModel);
}
