package com.example.car.service.impl;

import com.example.car.dao.SysMenuMapper;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.SysMenu;
import com.example.car.pojo.SysMenuExample;
import com.example.car.service.MenuSerivce;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class MenuSerivceImpl implements MenuSerivce {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public DataGridResult findMenu(QueryDTO queryDTO) {
        DataGridResult dataGridResult = new DataGridResult();
        //分页查询
//        PageHelper.startPage()//这个方法的第二页传的就是 2
        //因为这里前端传递的是offset，不是page
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit()); //这个方法的第二页传的就是 5\
        //对排序做优化
        if(!StringUtils.isEmpty(queryDTO.getSort())){
            queryDTO.setSort("menu_id");//这样才和数据库中的字段名对应。
        }
        List<SysMenu> byMenuPage = sysMenuMapper.findByMenuPage(queryDTO);
        PageInfo<SysMenu> pageInfo = new PageInfo<>(byMenuPage);
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setRows(byMenuPage);
        return dataGridResult;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public R deleMenuList(List<Long> ids) {
        for (Long id : ids) {
            if(id<51){
                return  R.error(-100,"系统菜单不能删除");
            }
        }
        int i = sysMenuMapper.deleMenuList(ids);
        if(i>0){
            return  R.ok();
        }
        return R.error(-90,"删除失败");
    }

    @Override
    public R findMenu() {
        SysMenuExample example = new SysMenuExample();
        SysMenuExample.Criteria criteria = example.createCriteria();
        criteria.andTypeNotEqualTo(2);
//        criteria.andTypeIn()
        List<SysMenu> sysMenus = sysMenuMapper.selectByExample(example);
        //添加一个跟目录
        SysMenu root = new SysMenu();
        root.setMenuId(0L);
        root.setParentId(-1L);
        root.setType(0);
        root.setName("一级菜单");
        sysMenus.add(root);
        return R.ok().put("menuList",sysMenus);
    }

    @Override
    public R saveMenu(SysMenu pojo) {
        int i = sysMenuMapper.insertSelective(pojo);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R findMenuById(Long menuId) {
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuId);

        return R.ok().put("menu",sysMenu);
    }

    @Override
    public R updateMenu(SysMenu pojo) {
        int i = sysMenuMapper.updateByPrimaryKeySelective(pojo);
        return i>0?R.ok():R.error("修改失败");
    }

    @Override
    public List<String> findMenuByUid(Long userId) {
        List<String> menuByUid = sysMenuMapper.findMenuByUid(userId);
        Set<String> set = new HashSet<>();
        for (String s : menuByUid) {
            if(!StringUtils.isEmpty(s)){
                String[] split = s.split(",");
                for(String s1 : split){
                    set.add(s1);
                }
            }
        }
        List<String> prems = new ArrayList<>();
        prems.addAll(set);
        return prems;
    }

    @Override
    public R findUserMenu(Long userId) {
        //查询一级菜单
        List<Map<String, Object>> dirMenuByUid = sysMenuMapper.findDirMenuByUid(userId);
        for (Map<String, Object> stringObjectMap : dirMenuByUid) {
            //获取一级菜单的主键id就是子菜单的父菜单
            Long subParentId = Long.parseLong(stringObjectMap.get("menuId")+"");
            //查询子菜单
            List<Map<String, Object>> subMenuByUid = sysMenuMapper.findSubMenuByUid(subParentId, userId);
            //给一级菜单多添加一个集合
            stringObjectMap.put("list",subMenuByUid);
        }
        //查询用户对应的权限
        List<String> perms = findMenuByUid(userId);

        return R.ok().put("menuList",dirMenuByUid).put("permissions",perms);
    }
}
