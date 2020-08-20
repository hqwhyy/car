package com.example.car.service.impl;

import com.example.car.dao.SysRoleMapper;
import com.example.car.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private SysRoleMapper sysRoleMapper;
    
    @Override
    public List<String> findRoleNamesByUid(Long uid) {
        List<String> roleNameByUid = sysRoleMapper.findRoleNameByUid(uid);
        return roleNameByUid;
    }
}
