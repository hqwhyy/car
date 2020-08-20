package com.example.car.action;

import com.example.car.dto.CarMakeDTO;
import com.example.car.dto.CarParamTypeDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarMake;
import com.example.car.pojo.CarParamType;
import com.example.car.service.CarParamTypeService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CarParamTypeAction {

    @Autowired
    private CarParamTypeService carParamTypeService;

    @RequestMapping("/sys/paramtype/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDTO queryDTO){
        return carParamTypeService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/paramtype/save")
    @ResponseBody
    public R insert(@RequestBody CarParamTypeDTO carParamTypeDTO){
        return carParamTypeService.insert(carParamTypeDTO);
    }

    @RequestMapping("/sys/paramtype/del")
    @ResponseBody
    public R deleCarParamTypeList(@RequestBody List<Long> ids){
        return carParamTypeService.deleCarParamTypeList(ids);
    }

    @RequestMapping("/sys/paramtype/info/{id}")
    @ResponseBody
    public R CarParamTypeInfo(@PathVariable("id") Integer id){
        return carParamTypeService.findCarParamTypeById(id);
    }

    @RequestMapping("/sys/paramtype/update")
    @ResponseBody
    public R updateCarParamType(@RequestBody CarParamType carParamType){
        return carParamTypeService.updateCarParamType(carParamType);
    }
}
