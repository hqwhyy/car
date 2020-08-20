package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.CarMakeMapper;
import com.example.car.dao.CarMapper;
import com.example.car.dto.CarDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.Car;
import com.example.car.pojo.CarExample;
import com.example.car.pojo.CarMake;
import com.example.car.service.CarService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CarMakeMapper carMakeMapper;

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        CarExample example = new CarExample();
        CarExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(queryDTO.getSearch())){
            criteria.andNameLike("%"+queryDTO.getSearch()+"%");
        }
        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause(queryDTO.getOrder());
        }
        List<Car> cars = carMapper.selectByExample(example);
        PageInfo<Car> carPageInfo = new PageInfo<>(cars);
        DataGridResult result = new DataGridResult();
        result.setTotal(carPageInfo.getTotal());
        result.setRows(cars);
        return result;
    }

    @Override
    public R insert(CarDTO carDTO) {
        Car car = new Car();
        car.setName(carDTO.getName());
        car.setMakeId(carDTO.getMakeId());
        int i = carMapper.insert(car);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R typeall() {
        List<CarMake> carMakes = carMakeMapper.selectByExample(null);
        return R.ok().put("sites",carMakes);
    }

    @Override
    public R deleCarList(List<Long> ids) {
        int i = carMapper.deleCarList(ids);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R findCarById(Integer id) {
        Car car = carMapper.selectByPrimaryKey(id);
        return R.ok().put("car",car);
    }

    @Override
    public R updateCar(Car car) {
        int i = carMapper.updateByPrimaryKeySelective(car);
        return i>0?R.ok():R.error("修改失败");
    }
}
