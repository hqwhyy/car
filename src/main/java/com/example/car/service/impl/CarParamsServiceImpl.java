package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.CarParamTypeMapper;
import com.example.car.dao.CarParamsMapper;
import com.example.car.dto.CarParamTypeDTO;
import com.example.car.dto.CarParamsDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarParamType;
import com.example.car.pojo.CarParams;
import com.example.car.pojo.CarParamsExample;
import com.example.car.service.CarParamsService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarParamsServiceImpl implements CarParamsService {

    @Autowired
    private CarParamsMapper carParamsMapper;

    @Autowired
    private CarParamTypeMapper carParamTypeMapper;

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        CarParamsExample example = new CarParamsExample();
        CarParamsExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(queryDTO.getSearch())){
            criteria.andParamNameLike("%"+queryDTO.getSearch()+"%");
        }
        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause("id"+queryDTO.getOrder());
        }
        List<CarParams> carParams = carParamsMapper.selectByExample(example);
        PageInfo<CarParams> carParamsPageInfo = new PageInfo<>(carParams);
        DataGridResult result = new DataGridResult();
        result.setTotal(carParamsPageInfo.getTotal());
        result.setRows(carParams);
        return result;
    }

    @Override
    public R insert(CarParamsDTO carParamsDTO) {
        CarParams carParams = new CarParams();
        carParams.setParamName(carParamsDTO.getParamName());
        carParams.setState(carParamsDTO.getState());
        carParams.setTypeId(carParamsDTO.getTypeId());
        int i = carParamsMapper.insert(carParams);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R typeall() {
        List<CarParamType> carParamTypes = carParamTypeMapper.selectByExample(null);
        return R.ok().put("sites",carParamTypes);
    }

    @Override
    public R deleCarParamsList(List<Long> ids) {
        int i = carParamsMapper.deleCarParamsList(ids);
        if(i>0){
            return  R.ok();
        }else{
            return R.error(-90,"删除失败");
        }
    }

    @Override
    public R findCarParamsById(Integer id) {
        CarParams carParams = carParamsMapper.selectByPrimaryKey(id);
        return R.ok().put("params",carParams);
    }

    @Override
    public R updateCarParams(CarParams carParams) {
        int i = carParamsMapper.updateByPrimaryKeySelective(carParams);
        return i>0?R.ok():R.error("修改失败");
    }

}
