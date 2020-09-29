package com.example.car.action;

import com.example.car.dto.CarModelDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarModel;
import com.example.car.service.CarModelService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * 车系管理
 *
 * @author qwhe
 * @date 2020/09/29
 */
@Controller
public class CarModelAction {

    @Autowired
    private CarModelService carModelService;

    @RequestMapping("/sys/model/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDTO queryDTO){
        return carModelService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/model/typeall")
    @ResponseBody
    public R typeAll(){
        return carModelService.typeall();
    }


    @RequestMapping("/sys/model/save")
    @ResponseBody
    public R insert(@RequestBody CarModelDTO carModelDTO){
        return carModelService.insert(carModelDTO);
    }

    @RequestMapping("/sys/model/del")
    @ResponseBody
    public R deleCarModelList(@RequestBody List<Long> ids){
        return carModelService.deleCarModelList(ids);
    }

    @RequestMapping("/sys/model/info/{id}")
    @ResponseBody
    public R CarModelInfo(@PathVariable("id") Integer id){
        return carModelService.findCarModelById(id);
    }

    @RequestMapping("/sys/model/update")
    @ResponseBody
    public R updateCarModel(@RequestBody CarModel carModel){
        return carModelService.updateCarModel(carModel);
    }
}
