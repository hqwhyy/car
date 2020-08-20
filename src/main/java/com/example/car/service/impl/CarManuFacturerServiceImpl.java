package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.CarMakeMapper;
import com.example.car.dao.CarManufacturerMapper;
import com.example.car.dto.CarManuFacturerDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarMake;
import com.example.car.pojo.CarManufacturer;
import com.example.car.pojo.CarManufacturerExample;
import com.example.car.service.CarManuFacturerService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarManuFacturerServiceImpl implements CarManuFacturerService {

    @Autowired
    private CarManufacturerMapper carManufacturerMapper;

    @Autowired
    private CarMakeMapper carMakeMapper;

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        CarManufacturerExample example = new CarManufacturerExample();
        CarManufacturerExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(queryDTO.getSearch())){
            criteria.andManufacturerNameLike(queryDTO.getSearch());
        }
        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause(queryDTO.getOrder());
        }
        List<CarManufacturer> carManufacturers = carManufacturerMapper.selectByExample(example);
        PageInfo<CarManufacturer> carManufacturerPageInfo = new PageInfo<>(carManufacturers);
        DataGridResult result = new DataGridResult();
        result.setTotal(carManufacturerPageInfo.getTotal());
        result.setRows(carManufacturers);
        return result;
    }

    @Override
    public R insert(CarManuFacturerDTO carManuFacturerDTO) {
        CarManufacturer carManufacturer = new CarManufacturer();
        carManufacturer.setManufacturerName(carManuFacturerDTO.getManufacturerName());
        carManufacturer.setMakeId(carManuFacturerDTO.getMakeId());
        int i = carManufacturerMapper.insert(carManufacturer);
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
    public R deleCarManuFacturerList(List<Long> ids) {
        int i = carManufacturerMapper.deleCarManuFacturerList(ids);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R findCarManuFacturerById(Integer id) {
        CarManufacturer carManufacturer = carManufacturerMapper.selectByPrimaryKey(id);
        return R.ok().put("manufacturer",carManufacturer);
    }

    @Override
    public R updateCarManuFacturer(CarManufacturer carManufacturer) {
        int i = carManufacturerMapper.updateByPrimaryKeySelective(carManufacturer);
        return i>0?R.ok():R.error("修改失败");
    }
}
