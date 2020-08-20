package com.example.car.action;

import com.example.car.dto.QueryDTO;
import com.example.car.pojo.SysMenu;
import com.example.car.service.MenuSerivce;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.example.car.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuAction {

    @Autowired
    private MenuSerivce menuSerivce;

    @RequestMapping("/sys/menu/list")
    @ResponseBody
    @RequiresPermissions("sys:menu:list")
    public DataGridResult findMenu(QueryDTO queryDTO){
        return menuSerivce.findMenu(queryDTO);
    }

    @RequestMapping("/sys/menu/del")
    @ResponseBody
    public R delMenu(@RequestBody List<Long> ids){
        R r = menuSerivce.deleMenuList(ids);
        return r;
    }

    @RequestMapping("/sys/menu/select")
    @ResponseBody
    public R findMenu(){
        return menuSerivce.findMenu();
    }

    @RequestMapping("/sys/menu/save")
    @ResponseBody
    public R saveMenu(@RequestBody SysMenu pojo){
        return menuSerivce.saveMenu(pojo);
    }

    @RequestMapping("/sys/menu/info/{menuId}")
    @ResponseBody
    public R findById(@PathVariable("menuId") Long menuId){
        return menuSerivce.findMenuById(menuId);
    }

    @RequestMapping("/sys/menu/update")
    @ResponseBody
    public R update(@RequestBody SysMenu sysMenu){
        return menuSerivce.updateMenu(sysMenu);
    }

    @RequestMapping("/sys/menu/user")
    @ResponseBody
    public R showMenu(){
        Long userId = ShiroUtils.getUserId();
        return menuSerivce.findUserMenu(userId);
    }
}
