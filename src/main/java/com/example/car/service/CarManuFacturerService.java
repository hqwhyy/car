package com.example.car.service;

import com.example.car.dto.CarManuFacturerDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarManufacturer;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;

public interface CarManuFacturerService {

    public DataGridResult findByPage(QueryDTO queryDTO);

    public R insert(CarManuFacturerDTO carManuFacturerDTO);

    public R typeall();

    public R deleCarManuFacturerList(List<Long> ids);

    public R findCarManuFacturerById(Integer id);

    public R updateCarManuFacturer(CarManufacturer carManufacturer);
}
