package com.example.car.service;


import com.example.car.dto.QueryDTO;
import com.example.car.pojo.SysMenu;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;

public interface MenuSerivce {

    public DataGridResult findMenu(QueryDTO queryDTO);

    public R deleMenuList(List<Long> ids);

    public R findMenu();

    public R saveMenu(SysMenu pojo);

    public R findMenuById(Long menuId);

    public R updateMenu(SysMenu pojo);

    List<String> findMenuByUid(Long userId);

    public R findUserMenu(Long userId);
}
