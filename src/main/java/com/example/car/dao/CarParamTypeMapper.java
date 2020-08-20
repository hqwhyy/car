package com.example.car.dao;

import com.example.car.pojo.CarParamType;
import com.example.car.pojo.CarParamTypeExample;
import java.util.List;

import com.example.car.utils.R;
import org.apache.ibatis.annotations.Param;

public interface CarParamTypeMapper {
    int countByExample(CarParamTypeExample example);

    int deleteByExample(CarParamTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarParamType record);

    int insertSelective(CarParamType record);

    List<CarParamType> selectByExample(CarParamTypeExample example);

    CarParamType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarParamType record, @Param("example") CarParamTypeExample example);

    int updateByExample(@Param("record") CarParamType record, @Param("example") CarParamTypeExample example);

    int updateByPrimaryKeySelective(CarParamType record);

    int updateByPrimaryKey(CarParamType record);

    int deleCarParamTypeList(List<Long> ids);

    R typeall();
}