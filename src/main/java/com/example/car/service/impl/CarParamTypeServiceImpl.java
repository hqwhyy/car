package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.CarParamTypeMapper;
import com.example.car.dto.CarParamTypeDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarParamType;
import com.example.car.pojo.CarParamTypeExample;
import com.example.car.service.CarParamTypeService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarParamTypeServiceImpl implements CarParamTypeService {

    @Autowired
    private CarParamTypeMapper carParamTypeMapper;

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        CarParamTypeExample example = new CarParamTypeExample();
        CarParamTypeExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(queryDTO.getSearch())){
            criteria.andTypeNameLike("%"+queryDTO.getSearch()+"%");
        }
        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause("id"+queryDTO.getOrder());
        }

        List<CarParamType> carParamTypes = carParamTypeMapper.selectByExample(example);
        PageInfo<CarParamType> carParamTypePageInfo = new PageInfo<>(carParamTypes);
        DataGridResult result = new DataGridResult();
        result.setTotal(carParamTypePageInfo.getTotal());
        result.setRows(carParamTypes);
        return result;
    }

    @Override
    public R insert(CarParamTypeDTO carParamTypeDTO) {
        CarParamType carParamType = new CarParamType();
        carParamType.setTypeName(carParamTypeDTO.getTypeName());
        carParamType.setState(carParamTypeDTO.getState());
        int i = carParamTypeMapper.insert(carParamType);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R deleCarParamTypeList(List<Long> ids) {
        int i = carParamTypeMapper.deleCarParamTypeList(ids);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R findCarParamTypeById(Integer id) {
        CarParamType carParamType = carParamTypeMapper.selectByPrimaryKey(id);
        return R.ok().put("paramtype",carParamType);
    }

    @Override
    public R updateCarParamType(CarParamType carParamType) {
        int i = carParamTypeMapper.updateByPrimaryKeySelective(carParamType);
        return i>0?R.ok():R.error("修改失败");
    }
}
