package com.example.car.action;

import com.example.car.dto.CarParamsDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarParams;
import com.example.car.pojo.Tag;
import com.example.car.service.CarParamTypeService;
import com.example.car.service.CarParamsService;
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
public class CarParamsAction {

    @Autowired
    private CarParamsService carParamsService;

    @Autowired
    private CarParamTypeService carParamTypeService;

    @RequestMapping("/sys/params/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDTO queryDTO){
        return carParamsService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/params/typeall")
    @ResponseBody
    public R typeAll(){
       return carParamsService.typeall();
    }


    @RequestMapping("/sys/params/save")
    @ResponseBody
    public R insert(@RequestBody CarParamsDTO carParamsDTO){
       return carParamsService.insert(carParamsDTO);
    }

    @RequestMapping("/sys/params/del")
    @ResponseBody
    public R deleCarParamsList(@RequestBody List<Long> ids){
        return carParamsService.deleCarParamsList(ids);
    }

    @RequestMapping("/sys/params/info/{id}")
    @ResponseBody
    public R CarParamsInfo(@PathVariable("id") Integer id){
        return carParamsService.findCarParamsById(id);
    }

    @RequestMapping("/sys/params/update")
    @ResponseBody
    public R updateCarParams(@RequestBody CarParams carParams){
        return carParamsService.updateCarParams(carParams);
    }

}
