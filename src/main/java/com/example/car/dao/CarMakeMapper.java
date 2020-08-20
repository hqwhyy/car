package com.example.car.dao;

import com.example.car.pojo.CarMake;
import com.example.car.pojo.CarMakeExample;
import java.util.List;

import com.example.car.utils.R;
import org.apache.ibatis.annotations.Param;

public interface CarMakeMapper {
    int countByExample(CarMakeExample example);

    int deleteByExample(CarMakeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarMake record);

    int insertSelective(CarMake record);

    List<CarMake> selectByExample(CarMakeExample example);

    CarMake selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarMake record, @Param("example") CarMakeExample example);

    int updateByExample(@Param("record") CarMake record, @Param("example") CarMakeExample example);

    int updateByPrimaryKeySelective(CarMake record);

    int updateByPrimaryKey(CarMake record);

    int deleCarMakeList(List<Long> ids);

    R typeall();
}