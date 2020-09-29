package com.example.car.action;

import com.example.car.dto.CarManuFacturerDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarManufacturer;
import com.example.car.service.CarManuFacturerService;
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
 * 厂商管理
 *
 * @author qwhe
 * @date 2020/09/29
 */
@Controller
public class CarManuFacturerAction {

    @Autowired
    private CarManuFacturerService carManuFacturerService;

    @RequestMapping("/sys/manufacturer/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDTO queryDTO){
        return carManuFacturerService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/manufacturer/typeall")
    @ResponseBody
    public R typeAll(){
        return carManuFacturerService.typeall();
    }


    @RequestMapping("/sys/manufacturer/save")
    @ResponseBody
    public R insert(@RequestBody CarManuFacturerDTO carManuFacturerDTO){
        return carManuFacturerService.insert(carManuFacturerDTO);
    }

    @RequestMapping("/sys/manufacturer/del")
    @ResponseBody
    public R deleCarManuFacturerList(@RequestBody List<Long> ids){
        return carManuFacturerService.deleCarManuFacturerList(ids);
    }

    @RequestMapping("/sys/manufacturer/info/{id}")
    @ResponseBody
    public R CarManuFacturerInfo(@PathVariable("id") Integer id){
        return carManuFacturerService.findCarManuFacturerById(id);
    }

    @RequestMapping("/sys/manufacturer/update")
    @ResponseBody
    public R updateCarManuFacturer(@RequestBody CarManufacturer carManufacturer){
        return carManuFacturerService.updateCarManuFacturer(carManufacturer);
    }
}
