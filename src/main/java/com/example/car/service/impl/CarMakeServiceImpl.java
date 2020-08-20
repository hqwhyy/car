package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.CarMakeMapper;
import com.example.car.dto.CarMakeDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarMake;
import com.example.car.pojo.CarMakeExample;
import com.example.car.service.CarMakeService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarMakeServiceImpl implements CarMakeService {

    @Autowired
    private CarMakeMapper carMakeMapper;

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        CarMakeExample example = new CarMakeExample();
        CarMakeExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(queryDTO.getSearch())) {
            criteria.andNameLike("%" + queryDTO.getSearch() + "%");
        }
        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause("id"+queryDTO.getOrder());
        }

        List<CarMake> carMakes = carMakeMapper.selectByExample(example);
        PageInfo<CarMake> carMakePageInfo = new PageInfo<>(carMakes);
        DataGridResult result = new DataGridResult();
        result.setTotal(carMakePageInfo.getTotal());
        result.setRows(carMakes);
        return result;
    }

    @Override
    public R insert(CarMakeDTO carMakeDTO) {
        CarMake carMake = new CarMake();
        carMake.setBrandName(carMakeDTO.getBrandName());
        carMake.setLogoUrl(carMakeDTO.getLogoUrl());
        int i = carMakeMapper.insert(carMake);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R deleCarMakeList(List<Long> ids) {
        int i = carMakeMapper.deleCarMakeList(ids);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R findCarMakeById(Integer id) {
        CarMake carMake = carMakeMapper.selectByPrimaryKey(id);
        return R.ok().put("make",carMake);
    }

    @Override
    public R updateCarMake(CarMake carMake) {
        int i = carMakeMapper.updateByPrimaryKeySelective(carMake);
        return i>0?R.ok():R.error("修改失败");
    }

}
