package com.example.car.action;

import com.example.car.dto.CarDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.Car;
import com.example.car.service.CarService;
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
 * 车款管理
 *
 * @author qwhe
 * @date 2020/09/29
 */
@Controller
public class CarAction {

    @Autowired
    private CarService carService;

    @RequestMapping("/sys/car/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDTO queryDTO){
        return carService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/car/typeall")
    @ResponseBody
    public R typeAll(){
        return carService.typeall();
    }


    @RequestMapping("/sys/car/save")
    @ResponseBody
    public R insert(@RequestBody CarDTO carDTO){
        return carService.insert(carDTO);
    }

    @RequestMapping("/sys/car/del")
    @ResponseBody
    public R deleCarList(@RequestBody List<Long> ids){
        return carService.deleCarList(ids);
    }

    @RequestMapping("/sys/car/info/{id}")
    @ResponseBody
    public R CarInfo(@PathVariable("id") Integer id){
        return carService.findCarById(id);
    }

    @RequestMapping("/sys/car/update")
    @ResponseBody
    public R updateCar(@RequestBody Car car){
        return carService.updateCar(car);
    }
}
