package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.CarMakeMapper;
import com.example.car.dao.CarModelMapper;
import com.example.car.dto.CarModelDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarMake;
import com.example.car.pojo.CarModel;
import com.example.car.pojo.CarModelExample;
import com.example.car.service.CarModelService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelMapper carModelMapper;

    @Autowired
    private CarMakeMapper carMakeMapper;

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        CarModelExample example = new CarModelExample();
        CarModelExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(queryDTO.getSearch())){
            criteria.andNameLike("%"+queryDTO.getSearch()+"%");
        }
        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause("id"+queryDTO.getOrder());
        }

        List<CarModel> carModels = carModelMapper.selectByExample(example);
        PageInfo<CarModel> carModelPageInfo = new PageInfo<>(carModels);
        DataGridResult result = new DataGridResult();
        result.setTotal(carModelPageInfo.getTotal());
        result.setRows(carModels);
        return result;
    }

    @Override
    public R insert(CarModelDTO carModelDTO) {
        CarModel carModel = new CarModel();
        carModel.setName(carModelDTO.getName());
        carModel.setMakeId(carModelDTO.getMakeId());
        int i = carModelMapper.insert(carModel);
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
    public R deleCarModelList(List<Long> ids) {
        int i = carModelMapper.deleCarModelList(ids);
        if(i>0){
            return  R.ok();
        }else{
            return R.error(-90,"删除失败");
        }
    }

    @Override
    public R findCarModelById(Integer id) {
        CarModel carModel = carModelMapper.selectByPrimaryKey(id);
        return R.ok().put("model",carModel);
    }

    @Override
    public R updateCarModel(CarModel carModel) {
        int i = carModelMapper.updateByPrimaryKeySelective(carModel);
        return i>0?R.ok():R.error("修改失败");
    }
}
