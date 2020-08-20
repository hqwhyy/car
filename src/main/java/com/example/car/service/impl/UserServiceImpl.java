package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.SysUserMapper;
import com.example.car.dto.QueryDTO;
import com.example.car.dto.UserDTO;
import com.example.car.pojo.SysUser;
import com.example.car.pojo.SysUserExample;
import com.example.car.service.UserService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> findAll() {
        List<SysUser> sysUsers = sysUserMapper.selectByExample(null);
        return sysUsers;
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(queryDTO.getSearch())){
            criteria.andUsernameLike("%"+queryDTO.getSearch()+"%");
        }
        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause("user_id"+queryDTO.getOrder());
        }

        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        DataGridResult result = new DataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(sysUsers);
        return result;
    }

    @Override
    public Workbook exportUser() {
        //创建一个新的空的excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //填充数据 创建sheet
        Sheet sheet = workbook.createSheet("某某某公司的员工信息");

        //标题数组
        String titles[] ={"用户id","用户名","邮箱","电话","创建时间"};
        String colums[] ={"userId", "username", "email", "mobile", "createTime"};

        //3,填充行和列中的数据
        //excel表格中的一行记录对应数据库表中的一行记录
        //查询数据库中的记录做填充
        List<Map<String,Object>> maps = sysUserMapper.exportUser();
        Row rowTile = sheet.createRow(0);
        for (int i = 0; i < titles.length ; i++) {
            Cell cell = rowTile.createCell(i);
            cell.setCellValue(titles[i]);
        }
        for (int i = 0; i < maps.size() ; i++) {
            //一条记录应该创建一个对象
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < titles.length ; j++) {
                Cell cell = row.createCell(j);
                Map<String, Object> rowValue = maps.get(i);
                Object o = rowValue.get(colums[j]);
                cell.setCellValue(o+"");
            }
        }
        return workbook;
    }

    @Override
    public SysUser findByUsername(String username) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if(sysUsers!=null){
            return sysUsers.get(0);
        }
        return null;
    }

    @Override
    public R insert(UserDTO userDTO) {
        SysUser record = new SysUser();
        record.setUsername(userDTO.getUsername());
        record.setPassword(userDTO.getPassword());
        record.setEmail(userDTO.getEmail());
        record.setMobile(userDTO.getMobile());
        record.setCreateTime(new Date());
        int i = sysUserMapper.insert(record);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("添加失败");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public R deleUserList(List<Long> ids) {

        int i = sysUserMapper.deleUserList(ids);
        if(i>0){
            return  R.ok();
        }else{
            return R.error(-90,"删除失败");
        }
    }

    @Override
    public R findUserById(Long userId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        return R.ok().put("user",sysUser);
    }

    @Override
    public R updateUser(SysUser sysUser) {
        int i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return i>0?R.ok():R.error("修改失败");
    }

}
