package com.example.car.service;

import com.example.car.dto.CarMakeDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarMake;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;

public interface CarMakeService {

    public DataGridResult findByPage(QueryDTO queryDTO);

    public R insert(CarMakeDTO carMakeDTO);

    public R deleCarMakeList(List<Long> ids);

    public R findCarMakeById(Integer id);

    public R updateCarMake(CarMake carMake);
}
