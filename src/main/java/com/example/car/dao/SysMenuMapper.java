package com.example.car.dao;

import com.example.car.dto.QueryDTO;
import com.example.car.pojo.SysMenu;
import com.example.car.pojo.SysMenuExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {
    int countByExample(SysMenuExample example);

    int deleteByExample(SysMenuExample example);

    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> findByMenuPage(QueryDTO queryDTO);

    int deleMenuList(List<Long> ids);

    List<String> findMenuByUid(@Param("userId") Long userId);

    List<Map<String,Object>> findDirMenuByUid(@Param("userId") Long userId);

    List<Map<String,Object>> findSubMenuByUid(@Param("parentId") Long parentId, @Param("userId") Long userId);
}