package com.example.car.dao;

import com.example.car.pojo.MemberFav;
import com.example.car.pojo.MemberFavExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberFavMapper {
    int countByExample(MemberFavExample example);

    int deleteByExample(MemberFavExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberFav record);

    int insertSelective(MemberFav record);

    List<MemberFav> selectByExample(MemberFavExample example);

    MemberFav selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberFav record, @Param("example") MemberFavExample example);

    int updateByExample(@Param("record") MemberFav record, @Param("example") MemberFavExample example);

    int updateByPrimaryKeySelective(MemberFav record);

    int updateByPrimaryKey(MemberFav record);
}