package com.example.car.service;

import com.example.car.dto.CarDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.Car;
import com.example.car.pojo.CarParams;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;

public interface CarService {

    public DataGridResult findByPage(QueryDTO queryDTO);

    public R insert(CarDTO carDTO);

    public R typeall();

    public R deleCarList(List<Long> ids);

    public R findCarById(Integer id);

    public R updateCar(Car car);
}
