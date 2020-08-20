package com.example.car.service;

import com.example.car.dto.QueryDTO;
import com.example.car.dto.UserDTO;
import com.example.car.pojo.SysUser;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface UserService {

    public List<SysUser> findAll();

    public DataGridResult findByPage(QueryDTO queryDTO);

    public Workbook exportUser();

    public SysUser findByUsername(String username);

    public R insert(UserDTO userDTO);

    public R deleUserList(List<Long> ids);

    public R findUserById(Long userId);

    public R updateUser(SysUser sysUser);

}
