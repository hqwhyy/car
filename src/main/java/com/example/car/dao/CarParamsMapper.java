package com.example.car.dao;

import com.example.car.pojo.CarParams;
import com.example.car.pojo.CarParamsExample;
import java.util.List;

import com.example.car.utils.R;
import org.apache.ibatis.annotations.Param;

public interface CarParamsMapper {
    int countByExample(CarParamsExample example);

    int deleteByExample(CarParamsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarParams record);

    int insertSelective(CarParams record);

    List<CarParams> selectByExample(CarParamsExample example);

    CarParams selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarParams record, @Param("example") CarParamsExample example);

    int updateByExample(@Param("record") CarParams record, @Param("example") CarParamsExample example);

    int updateByPrimaryKeySelective(CarParams record);

    int updateByPrimaryKey(CarParams record);

    int deleCarParamsList(List<Long> ids);
}